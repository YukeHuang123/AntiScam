package com.example.antiscam.dao;

import java.util.List;

public interface BlockDao {
    List<String> getDocumentId(String field, String email);
    List<String> getBlockedUsers(String blockerEmail);
    List<String> getBlockers(String blockedUserEmail);
}
