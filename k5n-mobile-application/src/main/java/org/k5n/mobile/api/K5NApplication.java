/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api;

import org.k5n.mobile.api.auth.Credentials;
import org.k5n.mobile.api.service.WordpressClient;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class K5NApplication {

    protected MobilePlatform platform;
    protected Identity identity;
    protected WordpressClient client;

    public K5NApplication(MobilePlatform platform) {
        this.platform = platform;
        this.identity = new Identity(this);
    }

    public Identity getIdentity() {
        return identity;
    }

    public void initAuthorizedClient(Credentials credentials) {
        client = new WordpressClient(credentials);
    }

    public void removeAuthorizedClient() {
        client = null;
    }

    public WordpressClient getClient() {
        if (client == null) {
            client = new WordpressClient(null);
        }
        return client;
    }

}
