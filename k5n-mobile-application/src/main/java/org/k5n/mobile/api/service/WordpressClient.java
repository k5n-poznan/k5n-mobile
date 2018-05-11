/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.k5n.mobile.api.service;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.k5n.mobile.api.MobilePlatform;
import org.k5n.mobile.api.Properties;
import org.k5n.mobile.api.SettingService;
import org.k5n.mobile.api.auth.Credentials;
import org.k5n.mobile.api.auth.UsernamePasswordCredentials;
import org.k5n.mobile.api.exceptions.MobileException;

/**
 *
 * @author Waldemar Kłaczyński
 */
public class WordpressClient {

    private String endpointURL;
    private Client client;

    public WordpressClient(Credentials credentials) {
        init(credentials);
    }
    
    public void test() {
        
    }
    
    public Map<String,String> getUserRoles() throws Exception {

        WebTarget target = target().path("roles");
        Response response = target.request()
                .accept(MediaType.TEXT_PLAIN)
                .get();

        if (response.getStatus() != 200) {
            throw new MobileException("Błąd połączenia kod: " + response.getStatus());
        }

        String out = response.readEntity(String.class);
        System.out.println(out);
        return Collections.EMPTY_MAP;
    }
    
    private WebTarget target() {
        String url = endpointURL;
        WebTarget target = client.target(url);
        return target;
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

            @SuppressWarnings("Convert2Lambda")
            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            ClientConfig clientConfig = new ClientConfig();

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());

            ClientBuilder cb = ClientBuilder.newBuilder()
                    .withConfig(clientConfig)
                    .hostnameVerifier(hostnameVerifier)
                    .sslContext(sslContext);

            client = cb.build();

            if (credentials instanceof UsernamePasswordCredentials) {
                UsernamePasswordCredentials unpc = (UsernamePasswordCredentials) credentials;
                
                HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(
                        unpc.getUserName(), unpc.getPassword());

                client.register(feature);
            }
            boolean enableDebugLogging = true;

            if (enableDebugLogging) {
                client.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY);
            }

            JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider(JacksonJsonProvider.BASIC_ANNOTATIONS);
            client.register(jacksonJsonProvider);

        } catch (Throwable th) {
            throw new RuntimeException(th);
        }
    }

    private static class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

}
