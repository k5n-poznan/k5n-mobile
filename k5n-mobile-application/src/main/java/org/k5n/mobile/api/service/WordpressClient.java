/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api.service;

import java.io.IOException;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.api.Properties;
import org.k5n.mobile.api.SettingService;
import org.k5n.mobile.api.auth.Credentials;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class WordpressClient {

    private String endpointURL;

    public void test() {
        
    }

    private void init(Credentials credentials) {
        try {
            MobilePlatform mp = MobilePlatform.getIstance();
            SettingService setting = mp.getSettingService();

            endpointURL = setting.retrieve(Properties.ENDPOINT_URL);
            if (endpointURL == null) {
                throw new IOException("Nie ustawiono adresu banku w ustawieniach.");
            }

            if (!endpointURL.endsWith("/")) {
                endpointURL += "/";
            }
            endpointURL += "index.php/wp-json/wp-msg/v1";

        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

}
