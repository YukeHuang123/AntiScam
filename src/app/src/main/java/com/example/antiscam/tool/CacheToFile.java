package com.example.antiscam.tool;

import android.content.Context;
import android.util.Log;

import com.example.antiscam.bean.ScamCaseWithUser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class CacheToFile {
    public static void saveCacheToInternalStorage(Context context, LRUCache<String, ScamCaseWithUser> cache) {
        Gson gson = new GsonBuilder()
                        .setExclusionStrategies(new DoublyLinkedListExclusionStrategy())
                        .create();

        String data = gson.toJson(cache);
//        String data = JSON.toJSONString(cache);
        saveStringToFile(data, context);
    }

    public static LRUCache<String, ScamCaseWithUser> loadCacheFromInternalStorage(Context context) {
        LRUCache<String, ScamCaseWithUser> cache = null;

        File cacheFile = new File(context.getFilesDir(), "newCache.json");

        if (!cacheFile.exists()) {

            return null;
        }

        Log.d("cacheDir", cacheFile.getAbsolutePath());

        String cacheString = readStringFromFile(context);
//        cache = JSON.parseObject(cacheString, new TypeReference<LRUCache<String, ScamCaseWithUser>>() {});
        Gson gson = new Gson();
        cache = gson.fromJson(cacheString, new TypeToken<LRUCache<String, ScamCaseWithUser>>(){}.getType());


        return cache;
    }

    private static void saveStringToFile(String data, Context context) {
        try {
            // Save the string to a file in internal storage
            FileOutputStream fileOutputStream = context.openFileOutput("newCache.json", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readStringFromFile(Context context) {
        try {
            FileInputStream fileInputStream = context.openFileInput("newCache.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
