/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codename1.components.FloatingHint;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.RequestBuilder;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    public SignInForm(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.PNG"), "LogoLabel"));
        
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
         Button fb = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
               
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
        signIn.addActionListener((e) ->{
             
                if ((username.getText().length()==0)||(password.getText().length()==0)
                        //||(emaill.getText().length()==0)
                        
                       )
                {
                    Dialog.show("error", " Remplissez tous les champs svp !", "Ok", null);
                }
      String url = "http://127.0.0.1/backt/web/app_dev.php/saisons/login";
                ConnectionRequest connectionRequest = new ConnectionRequest(url,false);
                    connectionRequest.addArgument("username",username.getText());
                    connectionRequest.addArgument("password",password.getText());
                    connectionRequest.addResponseListener((action) -> {
                     try {
JSONParser j = new JSONParser();
 String json = new String(connectionRequest.getResponseData()) + "";
  if (json.equals("failed")){
 Dialog.show("Echec d'authenfication", "username ou mot de passe éronné", "Ok", null);
 }
 else{
   System.out.println(json);

                            
   Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
  
   
   
     System.out.println(user);
                                float id = Float.parseFloat(user.get("id").toString());
                                System.out.println((int)id);
                                recupdonnees.setId((int)id);
                                recupdonnees.setPass(password.getText());
                                recupdonnees.setUserName(user.get("username").toString());
                                recupdonnees.setEmail(user.get("email").toString());
   
   
   
                            
                            if (user.size() > 0) {
                                new NewsfeedForm(res).show();
                                

                            }
                            }
                                    

                   
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                    
                });
                NetworkManager.getInstance().addToQueue(connectionRequest);
            
        });
        
        
    }
    
    
}
