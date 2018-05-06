/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api.auth;

import java.security.Principal;

/**
 *
 * @author Waldemar Kłaczyński
 */
public interface Credentials {

    Principal getUserPrincipal();

    String getPassword();
    
}
