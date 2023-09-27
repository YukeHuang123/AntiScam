package com.example.antiscam.dataclass;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.antiscam.bean.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class scamCaseListAccess {
    public static List<scamCaseList> loadJsonData(Context context, String fileName) {
        List<scamCaseList> dataList = new ArrayList<>();
        String post_user;
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(fileName);

            StringBuilder jsonString = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray jsonArray = new JSONArray(jsonString.toString());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                scamCaseList scamcase = new scamCaseList();
                scamcase.setTitle(jsonObject.getString("title"));
                scamcase.setDescription(jsonObject.getString("description"));
                scamcase.setScam_type(jsonObject.getString("scam_type"));
                scamcase.setScam_id(jsonObject.getInt("scam_id"));
                scamcase.setAmount(jsonObject.optDouble("amount"));
                post_user = jsonObject.getString("post_user");
                UserDaoImpl userDaoimpl = new UserDaoImpl();
                userDaoimpl.getUserByEmail(post_user, new UserDao.UserCallback() {
                    @Override
                    public void onUserReceived(User user) {
                        String username = user.getUsername();
                        scamcase.setUser_name(username);
//                        String email = user.getEmail();
                        String avatar = user.getAvatar();
                        scamcase.setUser_avatar(avatar);
                    }

                    @Override
                    public void onUsersReceived(List<User> users) {

                    }
                });

                dataList.add(scamcase);
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return dataList;
    }


}
