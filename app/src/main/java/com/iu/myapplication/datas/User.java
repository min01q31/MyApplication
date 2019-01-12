package com.iu.myapplication.datas;

import android.os.UserManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String user_id;
    private String name;
    private String email;
    private String profile_image;

    public static User getUserFromJson(JSONObject userJson){
        User userObject = new User();

        try {
            userObject.setId(userJson.getInt("id"));
            userObject.setUser_id(userJson.getString("user_id"));
            userObject.setName(userJson.getString("name"));
            userObject.setEmail(userJson.getString("email"));
            userObject.setProfile_image(userJson.getString("profile_image"));
        } catch (JSONException e) {
            e.printStackTrace();
        } return userObject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}
