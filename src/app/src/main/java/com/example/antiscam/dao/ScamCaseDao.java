package com.example.antiscam.dao;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.searchCore.Tokenizer;

import java.util.List;

public interface ScamCaseDao {
    void getAllScamCase(ScamCaseCallback scamCaseCallback);
    interface ScamCaseCallback {
        void onScamCaseReceived(ScamCase scamcase);
    }
    void getAllScamCases(Tokenizer tokenizer, ScamCasesCallback scamCasesCallback);
    interface ScamCasesCallback {
        void onScamCaseReceived(List<ScamCase> scamcase);
    }
    void addScamCase(ScamCase scamcase);

    interface NextIdCallback {
        void onNextId(int nextId);
    }
    void updateNextId(NextIdCallback callback);
    void getDocumentId(int scam_id, OnDocumentIdCallback onDocumentIdCallback);
    interface OnDocumentIdCallback {
        void onDocumentIdReceived(String documentId);
        void onDocumentIdNotFound();
    }
}
