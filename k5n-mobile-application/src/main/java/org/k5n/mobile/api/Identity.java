/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api;

import java.util.HashMap;
import java.util.Map;
import org.k5n.mobile.api.auth.UsernamePasswordCredentials;
import org.k5n.mobile.api.exceptions.AuthorizationException;


/**
 *
 * @author Waldemar Kłaczyński
 */
public class Identity {
    
    private final K5NApplication app;
    private boolean authorized;
    private final Map<String, String> userRoles = new HashMap<>();

    public Identity(K5NApplication app) {
        this.app = app;
    }

    public boolean login(String username, String password) throws AuthorizationException {
        authorized = authorize(username, password);
        return authorized;
    }

    public void logout() throws AuthorizationException {
        app.removeAuthorizedClient();
        userRoles.clear();
        authorized = false;
    }
    
    
    private boolean authorize(String username, String password) throws AuthorizationException {
        try {
            userRoles.clear();
            authorized = false;
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
                    username,password
            );
            app.initAuthorizedClient(credentials);
            userRoles.putAll(app.getClient().getUserRoles());
            return true;
        } catch (Exception ex) {
           throw new AuthorizationException(ex);
        }
    }
    
    public boolean hasRole(String name) {
        return userRoles.containsKey(name);
    }

    public boolean isLoggedIn() {
        return authorized;
    }

}
