/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api;

import org.k5n.mobile.api.auth.UsernamePasswordCredentials;
import org.k5n.mobile.api.exceptions.AuthorizationException;


/**
 *
 * @author Waldemar Kłaczyński
 */
public class Identity {
    
    private final K5NApplication ws;
    private String sessionId;

    public Identity(K5NApplication ws) {
        this.ws = ws;
    }

    public String getSessionId() {
        return sessionId;
    }
    
    public boolean login(String login, String password) throws AuthorizationException {
        this.sessionId = authorize(login, password);
        return sessionId != null;
    }
    
    
    private String authorize(String login, String password) throws AuthorizationException {
        
        try {
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
                    login,password
            );
            //ibs.initClient(credentials);
            
            return generateSessionID();
        } catch (Exception ex) {
           throw new AuthorizationException(ex);
        }
    }

    private static int sessionNonceID = 0;

    private String generateSessionID() {
        sessionNonceID++;
        return "xyzzy - session " + sessionNonceID;
    }

    public boolean isLoggedIn() {
        return false;
    }

}
