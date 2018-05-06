/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api.auth;

import java.security.Principal;
import java.util.Objects;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class UsernamePasswordCredentials implements Credentials {
    
    private final BasicUserPrincipal principal;
    private final String password;
    
    public UsernamePasswordCredentials(final String userName, final String password) {
        super();
        this.principal = new BasicUserPrincipal(userName);
        this.password = password;
    }
    
    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    public String getUserName() {
        return this.principal.getName();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return this.principal.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsernamePasswordCredentials other = (UsernamePasswordCredentials) obj;
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return Objects.equals(this.principal, other.principal);
    }
    
    @Override
    public String toString() {
        return this.principal.toString();
    }
    
    
    
}
