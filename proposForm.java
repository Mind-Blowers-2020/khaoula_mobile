/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.ui.Button;
import com.codename1.ui.util.Resources;

/**
 *
 * @author khaoula
 */
class proposForm  extends BaseForm{

    public proposForm(Resources res) {
        
         Button retour = new Button("retour");

        retour.addActionListener(e-> new NewsfeedForm(res).show());
        add(retour);

    }
    
}
