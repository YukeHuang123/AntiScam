//package com.example.antiscam;
//
//import android.app.Application;
//import android.graphics.Bitmap;
//import android.util.Log;
//
//import com.example.antiscam.bean.ScamCaseWithUser;
//import com.example.antiscam.tool.LRUCache;
//import com.google.gson.Gson;
//
//import org.checkerframework.checker.units.qual.K;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class CacheApplication extends Application {
//    LRUCache<Integer, ScamCaseWithUser> lruCache;
//    File cachePath = getCacheDir();
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        lruCache = new LRUCache<>(100);
//        Log.d("lru", "lru capacity " + lruCache.get(0));
//        Gson gson = new Gson();
//        String json = gson.toJson(lruCache);
//
//    }
//
//    public LRUCache<Integer, ScamCaseWithUser> getCache() {
//        return lruCache;
//    }
//
//    public void saveCacheToFile(LRUCache<Integer, ScamCaseWithUser> cache, String filename) {
//        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
//            oos.writeObject(cache);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public LRUCache<Integer, ScamCaseWithUser> loadCacheFromFile(String filename) {
//        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
//            return (LRUCache<Integer, ScamCaseWithUser>) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
//
