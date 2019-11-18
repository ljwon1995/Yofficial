package com.example.yofficial;


public class UserInfo {
    private String id;
    private String pw;

    private int cookLevel;
    private float cookExp;

    private int chefLevel;
    private float chefExp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public int getCookLevel() {
        return cookLevel;
    }

    public void setCookLevel(int cookLevel) {
        this.cookLevel = cookLevel;
    }

    public float getCookExp() {
        return cookExp;
    }

    public void setCookExp(float cookExp) {
        this.cookExp = cookExp;
    }

    public int getChefLevel() {
        return chefLevel;
    }

    public void setChefLevel(int chefLevel) {
        this.chefLevel = chefLevel;
    }

    public float getChefExp() {
        return chefExp;
    }

    public void setChefExp(float chefExp) {
        this.chefExp = chefExp;
    }
}
