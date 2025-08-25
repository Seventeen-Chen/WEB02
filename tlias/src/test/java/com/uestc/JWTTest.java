package com.uestc;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

public class JWTTest {

    @Test
    public void testJwt(){
        Jwts.builder().signWith(SignatureAlgorithm.HS256)
    }
}
