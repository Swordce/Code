package com.cxmedia.goods.MVP.model;

public class MessageDetailModel {

    /**
     * data : {"id":1,"content":"内容","status":"状态","name":"测试标题","createTime":"2019-08-23 17:39:47","updateTime":"2019-08-23 17:40:36","type":"类型"}
     * respMsg : 操作成功
     * respCode : 0
     */

    private DataBean data;
    private String respMsg;
    private String respCode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public static class DataBean {
        /**
         * id : 1
         * content : 内容
         * status : 状态
         * name : 测试标题
         * createTime : 2019-08-23 17:39:47
         * updateTime : 2019-08-23 17:40:36
         * type : 类型
         */

        private int id;
        private String content;
        private String status;
        private String name;
        private String createTime;
        private String updateTime;
        private String type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
