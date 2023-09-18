package com.example.antiscam;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

import java.io.IOException;

public class FirebaseDatabaseTest {

    @Test
    public void testFirebaseAdmin() throws IOException {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("path/in/your/database");
        ref.setValue("New Value using Admin SDK");

        // 确保数据已经写入然后关闭测试。
        try {
            Thread.sleep(10000); // 10秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

