package com.example.antiscam.singleton;

import android.content.Context;

import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.tool.CacheToFile;
import com.example.antiscam.tool.LRUCache;

public class CacheSingleton {
    private static CacheSingleton instance;

    private CacheSingleton() {
        // 私有构造函数，防止外部实例化
    }

    public static CacheSingleton getInstance() {
        if (instance == null) {
            instance = new CacheSingleton();
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
