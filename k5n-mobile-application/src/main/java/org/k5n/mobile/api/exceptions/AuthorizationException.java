/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api.exceptions;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class AuthorizationException extends Exception {

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

}
