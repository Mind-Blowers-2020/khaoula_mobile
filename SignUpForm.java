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
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

import entites.Userrrr;
import java.io.IOException;

import service.ServiceUser;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm  {
 TextField username;
  TextField role;
       TextField password,
       confirmPassword,emaill;
    public SignUpForm(Resources res)  {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        username = new TextField("", "Username", 20, TextField.ANY);
        password = new TextField("", "Password", 20, TextField.PASSWORD);
        confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        emaill = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
           role = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        
        
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        emaill.setSingleLineTextArea(false);
          role.setSingleLineTextArea(false);
        
        
          Button fb= new Button("Se connecter avec facebook");
        
      
    
        
        
        Button next = new Button("Ajouter");
        Button signIn = new Button("Se Connecter");
        
        
        
        /* next.addActionListener((register) -> {
                      String url ="http://127.0.0.1/backt/web/app_dev.php/saisons/register?username=" + username.getText().toString();

                ConnectionRequest req = new ConnectionRequest(url, false);
                req.addArgument("username", username.getText());
                req.addArgument("password", password.getText());
                req.addArgument("email", emaill.getText());
               // req.addArgument("roles", role.getText());
                
                       
                req.addResponseListener((response)-> {
                        
                        byte[] data = (byte[]) response.getMetaData();
                        String s = new String(data);
                        System.out.println(s);                                                
                        if (s.equals("success")) {                            
                        } else {
                            Dialog.show("Erreur", "erreur", "Ok", null);
                        }                    
                });

                NetworkManager.getInstance().addToQueue(req);
            
        });*/
          next.addActionListener(new ActionListener() {
              

            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((username.getText().length()==0)||(password.getText().length()==0)||(emaill.getText().length()==0)
                        
                       )
                {
                    Dialog.show("error", " Remplissez tous les champs svp !", "Ok", null);
                }
                else
                {
                    try {
                        
          
   Userrrr user = new Userrrr(username.getText().toString(),password.getText().toString(),emaill.getText().toString());
                        if( ServiceUser.getInstance().inscription(user))
                        {  Dialog.show("succes", "connection établie", "Ok", null);
                        }               

                        else
                        {  Dialog.show("verifiez svp", "le username ou l'adresse mail existe deja!", "Ok", null);

                                
                                }

                    } catch (NumberFormatException e) {
                     
                    }
                    
                }
              
            }
            
        });
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Avez vous deja un compte?");
        signIn.addActionListener(e -> new SignInForm(res).show());
        


        Container content = BoxLayout.encloseY(
                new Label("", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
               new FloatingHint(confirmPassword),
               createLineSeparator(),
                new FloatingHint(emaill),
             createLineSeparator()
               //new FloatingHint(role),
               // createLineSeparator()
                
                
               

        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                
                next, fb,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        
}
}
