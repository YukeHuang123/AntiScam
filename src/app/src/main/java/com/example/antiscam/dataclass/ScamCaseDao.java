package com.example.antiscam.dataclass;

import com.example.antiscam.bean.ScamCase;
import com.example.antiscam.core.Tokenizer;

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
    int getSizeOfScamCase();

    public interface NextIdCallback {
        void onNextId(int nextId);
    }
    public void updateNextId(NextIdCallback callback);
    void getDocumentId(int scam_id, OnDocumentIdCallback onDocumentIdCallback);
    interface OnDocumentIdCallback {
        void onDocumentIdReceived(String documentId);
        void onDocumentIdNotFound();
    }
}
