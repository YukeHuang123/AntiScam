package com.example.antiscam.dataclass;

public class scamCase {
//    {
//    "scam_id": "001",
//   "post_user": "1(user_id)",
//    "post_date": "2023-01-15",
//    "scam_type": "Advance fee fraud",
//    "description": "Someone claimed to have a large amount of money stuck in a foreign bank...",
//    "date": "2023-01-15",
//    "amount": 2500.00,
//    "victim_age": 45,
//    "victim_city": Canberra
//}
    private int scam_id;
    private int postUser;
    private String postDate;
    private int avatar;
    private String scamType;
    private String title;
    private String description;
    private String date;
    private float amount;
    private String victimAge;
    private String victimCity;

    public scamCase(int postUser, int avatar, String scamType, String title, String description) {
        this.postUser = postUser;
        this.avatar = avatar;
        this.scamType = scamType;
        this.title = title;
        this.description = description;
    }

    public scamCase(String title, String description) {
        this.title = title;
        this.description = description;
//        this.avatar = avatar;
    }

    public scamCase() {
    }

    public int getScam_id() {
        return scam_id;
    }

    public void setScam_id(int scam_id) {
        this.scam_id = scam_id;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public int getPostUser() {
        return postUser;
    }

    public void setPostUser(int postUser) {
        this.postUser = postUser;
    }

    public String getScamType() {
        return scamType;
    }

    public void setScamType(String scamType) {
        this.scamType = scamType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getVictimAge() {
        return victimAge;
    }

    public void setVictimAge(String victimAge) {
        this.victimAge = victimAge;
    }

    public String getVictimCity() {
        return victimCity;
    }

    public void setVictimCity(String victimCity) {
        this.victimCity = victimCity;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
