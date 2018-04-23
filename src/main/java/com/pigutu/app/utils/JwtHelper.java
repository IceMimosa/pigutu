package com.pigutu.app.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

// We need a signing key, so we'll create one just for this example. Usually
// the key would be read from your application configuration instead.

public class JwtHelper {

    public static String createJWT(String username){
        Key key = MacProvider.generateKey();

        String compactJws = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return compactJws;
    }
}