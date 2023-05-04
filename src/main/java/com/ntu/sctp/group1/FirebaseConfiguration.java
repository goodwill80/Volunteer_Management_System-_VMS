package com.ntu.sctp.group1;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.ntu.sctp.group1.DataTransferObject.FirebaseCredentials;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfiguration {

    @Value("${type}")
    private String type;

    @Value("${project_id}")
    private String project_id;

    @Value("${private_key_id}")
    private String private_key_id;

    @Value("${private_key}")
    private String private_key;

    @Value("${client_email}")
    private String client_email;

    @Value("${client_id}")
    private String client_id;

    @Value("${auth_uri}")
    private String auth_uri;

    @Value("${token_uri}")
    private String token_uri;

    @Value("${auth_provider_x509_cert_url}")
    private String auth_provider_x509_cert_url;

    @Value("${client_x509_cert_url}")
    private String client_x509_cert_url;

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {

        FirebaseCredentials firebaseCredentials = new FirebaseCredentials();

        firebaseCredentials.setType(type);
        firebaseCredentials.setProject_id(project_id);
        firebaseCredentials.setPrivate_key_id(private_key_id);
        firebaseCredentials.setPrivate_key(private_key);
        firebaseCredentials.setClient_email(client_email);
        firebaseCredentials.setClient_id(client_id);
        firebaseCredentials.setAuth_uri(auth_uri);
        firebaseCredentials.setToken_uri(token_uri);
        firebaseCredentials.setAuth_provider_x509_cert_url(auth_provider_x509_cert_url);
        firebaseCredentials.setClient_x509_cert_url(client_x509_cert_url);



        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(firebaseCredentials);
        InputStream targetStream = new ByteArrayInputStream(jsonString.getBytes());

        var options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(targetStream))
//               .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                .build();

        var firebaseApp = FirebaseApp.initializeApp(options);


        return FirebaseAuth.getInstance(firebaseApp);


    }
}