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
public class ComunicationException extends MobileException {

    public ComunicationException(String message) {
        super(message);
    }

    public ComunicationException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

    public ComunicationException(String message, Throwable cause) {
        super(message, cause);
    }

}
