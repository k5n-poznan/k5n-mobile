/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api;


/**
 *
 * @author Waldemar Kłaczyński
 */
public class Application {
    
    protected MobilePlatform platform;
    protected Identity identity;
    
    public Application(MobilePlatform platform) {
        this.platform = platform;
        this.identity = new Identity(this);
    }

    public Identity getIdentity() {
        return identity;
    }

}
