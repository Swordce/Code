package com.cxmedia.goods.widgets.camera.encoder;

import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Environment;
import android.util.Log;

import com.cxmedia.goods.widgets.camera.utils.YuvUtil;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;

import static android.media.MediaCodec.BUFFER_FLAG_CODEC_CONFIG;
import static android.media.MediaCodec.BUFFER_FLAG_KEY_FRAME;

/**
 * use {@link H264EncoderConsumer}
 */
@Deprecated
public class H264Encoder {
    private int m_width, m_height, m_framerate;
    private MediaCodec mediaCodec;
    private boolean isRuning;
    private static int yuvqueuesize = 10;
    private ArrayBlockingQueue<byte[]> YUVQueue = new ArrayBlockingQueue<>(yuvqueuesize);
    private byte[] configbyte;
    private static final int TIMEOUT_USEC = 12000;
    private BufferedOutputStream outputStream;
    //private final MediaUtil mediaUtil;
    private static final String TAG = H264Encoder.class.getSimpleName();
    private MediaCodec.BufferInfo mbBufferInfo;

    private void createfile(String path){
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int bit_rate = 3; //可以设置为 1 3 5
    public H264Encoder(int width, int height, int framerate, int bitrate,EncoderParams params) {
        m_width = width;
        m_height = height;
        m_framerate = framerate;

        //mediaUtil = MediaUtil.getDefault();
        //mediaUtil.setVideoPath(params.getVideoPath());
        MediaFormat mediaFormat = MediaFormat.createVideoFormat("video/avc", width, height);
        //颜色空间设置为yuv420sp
        mediaFormat.setInteger(MediaFormat.KEY_COLOR_FORMAT, MediaCodecInfo.CodecCapabilities.COLOR_FormatYUV420SemiPlanar);
        //比特率，也就是码率 ，值越高视频画面更清晰画质更高
        mediaFormat.setInteger(MediaFormat.KEY_BIT_RATE, width * height * bit_rate);
        //帧率，一般设置为30帧就够了
        mediaFormat.setInteger(MediaFormat.KEY_FRAME_RATE, framerate);
        //关键帧间隔  6.0以上无法控制，需要使用opengles渲染控制
        mediaFormat.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, 1);
        try {
            //初始化mediacodec
            mediaCodec = MediaCodec.createEncoderByType("video/avc");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //设置为编码模式和编码格式
        mediaCodec.configure(mediaFormat, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);
        mediaCodec.start();
        mbBufferInfo = new MediaCodec.BufferInfo();
        createfile(params.getVideoPath());
    }

    private void NV21ToNV12(byte[] nv21, byte[] nv12, int width, int height) {
        if (nv21 == null || nv12 == null) return;
        int framesize = width * height;
        int i = 0, j = 0;
        System.arraycopy(nv21, 0, nv12, 0, framesize);
        for (i = 0; i < framesize; i++) {
            nv12[i] = nv21[i];
        }
        for (j = 0; j < framesize / 2; j += 2) {
            nv12[framesize + j - 1] = nv21[j + framesize];
        }
        for (j = 0; j < framesize / 2; j += 2) {
            nv12[framesize + j] = nv21[j + framesize - 1];
        }
    }

    public void putYUVData(byte[] buffer) {
        if (YUVQueue.size() >= yuvqueuesize) {
            YUVQueue.poll();
            Log.e(TAG, "丢帧");
        }
        YUVQueue.add(buffer);
    }

    /**
     * 纯H264的文件，是没有时间戳概念的，就是一堆流文件，所以如果手机配置低的话，由于编码时间过长（大于两次帧刷新间隔的话），
     * 会导致很多帧数据丢掉，所以最终的流文件播放出来就会短。反之高端手机由于处理时间短而且相机帧率又高的话就会导致播放时间变长。
     */
    private boolean isAddKeyFrame;
    public void StartEncoderThread(){
        Thread EncoderThread = new Thread(new Runnable() {
            @SuppressLint("NewApi")
            @Override
            public void run() {
                isRuning = true;
                byte[] input = null;
                long pts =  0;
                long generateIndex = 0;
                while (isRuning) {
                    if (YUVQueue.size() > 0){
                        //从缓冲队列中取出一帧
                        input = YUVQueue.poll();  //队列不为空时返回队首值并移除；队列为空时返回null
                        byte[] yuv420sp = new byte[m_width*m_height*3/2];
                        //把待编码的视频帧转换为YUV420格式
                        YuvUtil.NV21ToNV12(input,yuv420sp,m_width,m_height);
                        input = yuv420sp;
                    }
                    if (input != null) {
                        try {
                            long startMs = System.currentTimeMillis();
                            //编码器输入缓冲区
                            ByteBuffer[] inputBuffers = mediaCodec.getInputBuffers();
                            //编码器输出缓冲区
                            ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
                            int inputBufferIndex = mediaCodec.dequeueInputBuffer(-1);
                            if (inputBufferIndex >= 0) {
                                pts = computePresentationTime(generateIndex);
                                ByteBuffer inputBuffer = inputBuffers[inputBufferIndex];
                                inputBuffer.clear();
                                //把转换后的YUV420格式的视频帧放到编码器输入缓冲区中
                                inputBuffer.put(input);
                                mediaCodec.queueInputBuffer(inputBufferIndex, 0, input.length, pts, MediaCodec.BUFFER_FLAG_KEY_FRAME);
                                generateIndex += 1;
                            }

                            int outputBufferIndex = mediaCodec.dequeueOutputBuffer(mbBufferInfo, TIMEOUT_USEC);
                            while (outputBufferIndex >= 0) {
                                ByteBuffer outputBuffer = outputBuffers[outputBufferIndex];
                                byte[] outData = new byte[mbBufferInfo.size];
                                outputBuffer.get(outData);
                                if(mbBufferInfo.flags == BUFFER_FLAG_CODEC_CONFIG){
                                    configbyte = new byte[mbBufferInfo.size];
                                    configbyte = outData;
                                }else if(mbBufferInfo.flags == BUFFER_FLAG_KEY_FRAME){
                                    byte[] keyframe = new byte[mbBufferInfo.size + configbyte.length];
                                    System.arraycopy(configbyte, 0, keyframe, 0, configbyte.length);
                                    //把编码后的视频帧从编码器输出缓冲区中拷贝出来
                                    System.arraycopy(outData, 0, keyframe, configbyte.length, outData.length);
                                    if (h264Listener != null){
                                        h264Listener.onPreview(keyframe,m_width,m_height);
                                    }
                                    outputStream.write(keyframe, 0, keyframe.length);
                                }else{
                                    if (h264Listener != null){
                                        h264Listener.onPreview(outData,m_width,m_height);
                                    }else {
                                        Log.e(TAG, "h264Listener is null");
                                    }
                                    outputStream.write(outData, 0, outData.length);
                                }

                                mediaCodec.releaseOutputBuffer(outputBufferIndex, false);
                                outputBufferIndex = mediaCodec.dequeueOutputBuffer(mbBufferInfo, TIMEOUT_USEC);
                            }
                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                    } else {
                        try {
                            //这里可以根据实际情况调整编码速度
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        EncoderThread.start();
    }

    private void stopEncoder() {
        if (null != mediaCodec){
            mediaCodec.stop();
            mediaCodec.release();
            mediaCodec = null;
        }
    }

    public void stopThread(){
        isRuning = false;
        try {
            stopEncoder();
            outputStream.flush();
            outputStream.close();
            h264Listener = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mediaUtil.release();
    }
    /**
     * 计算pts
     * @param frameIndex
     * @return
     */
    private long computePresentationTime(long frameIndex) {
        return 132 + frameIndex * 1000000 / m_framerate;
    }

    public boolean isEncodering(){
        return isRuning;
    }

    private PreviewFrameListener h264Listener;
    public void setPreviewListner(PreviewFrameListener listener){
        h264Listener = listener;
    }
    public interface PreviewFrameListener {
        void onPreview(byte[] data, int width, int height);
    }

}
