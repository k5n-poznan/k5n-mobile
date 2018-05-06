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
public class BasicUserPrincipal implements Principal {

    private final String username;

    public BasicUserPrincipal(final String username) {
        super();
        this.username = username;
    }

    @Override
    public String getName() {
        return this.username;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.username);
        return hash;
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
        final BasicUserPrincipal other = (BasicUserPrincipal) obj;
        return Objects.equals(this.username, other.username);
    }


    @Override
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        buffer.append("[principal: ");
        buffer.append(this.username);
        buffer.append("]");
        return buffer.toString();
    }

}

