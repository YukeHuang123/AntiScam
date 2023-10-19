package com.example.antiscam.tool;

import android.content.Context;

import com.example.antiscam.bean.ScamCaseWithUser;

/**
 * @author Zhaoyun Xu, u7558707
 */
public class HistoryCache {
    private static HistoryCache instance;

    private HistoryCache() {
    }

    public static HistoryCache getInstance() {
        if (instance == null) {
            instance = new HistoryCache();
        }
        return instance;
    }


    public LRUCache<String, ScamCaseWithUser> getCache(Context context) {
        return CacheToFile.loadCacheFromInternalStorage(context);
    }

    public void setCache(Context context, LRUCache<String, ScamCaseWithUser> cache) {
        CacheToFile.saveCacheToInternalStorage(context, cache);
    }
}
