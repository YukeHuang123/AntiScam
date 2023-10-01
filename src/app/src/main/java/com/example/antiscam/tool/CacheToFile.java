package com.example.antiscam.tool;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CacheToFile {
    public void saveCacheToInternalStorage(Context context, LRUCache<String, Integer> cache) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("cache.ser", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(cache);
            oos.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LRUCache<String, Integer> loadCacheFromInternalStorage(Context context) {
        LRUCache<String, Integer> cache = null;
        try {
            FileInputStream fileInputStream = context.openFileInput("cache.ser");
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            cache = (LRUCache<String, Integer>) ois.readObject();
            ois.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cache;
    }

}
