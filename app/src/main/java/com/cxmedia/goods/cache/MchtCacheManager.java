package com.cxmedia.goods.cache;

import java.util.Stack;

public class MchtCacheManager {
    private static MchtCacheManager cacheManager;

    private Stack<MchtCache> stack;

    private MchtCacheManager() {
        stack = new Stack<>();
    }


    public static MchtCacheManager getInstance() {

        if (cacheManager == null) {
            cacheManager = new MchtCacheManager();
        }
        return cacheManager;
    }

    public void push(MchtCache cache) {
        stack.push(cache);
    }

    public MchtCache pop() {

        if (stack.isEmpty())
            return null;

        return stack.pop();
    }


    public MchtCache peek() {

        if (stack.isEmpty())
            return null;

        return stack.peek();
    }
}
