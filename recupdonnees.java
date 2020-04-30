/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.io.Preferences;

/**
 *
 * @author khaoula
 */
public class recupdonnees {

    public static Preferences pref;
    
    private static int id;

    public static void setId(int id) {
        pref.set("id", id);
    }

    public static int getId() {
        return pref.get("id", id);
    }
      
    
    private static String username;

    public static void setUserName(String username) {
        pref.set("username", username);
    }

    public static String getUserName() {
        return pref.get("username", username);
    }

    private static String password;

    public static void setPass(String password) {
        pref.set("password", password);
    }

    public static String getPass() {
        return pref.get("password", password);
    }

    private static String email;

    public static void setEmail(String email) {
        pref.set("email", email);
    }

    public static String getEmail() {
        return pref.get("email", email);
    }

     private static String photo;
    public static void setPhoto(String photo){
        pref.set("photo",photo);
    }
    
    public static String getPhoto(){
        return pref.get("photo", photo);
    }

}
