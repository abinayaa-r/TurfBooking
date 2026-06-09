//package com.turf.authserver.controller;
//
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/.well-known")
//public class JwkController {
//
//    @Value("${jwt.public.key}")
//    private String publicKey; // your RSA public key
//
//    @GetMapping("/jwks.json")
//    public Map<String, Object> getJwks() {
//        Map<String, Object> jwk = new HashMap<>();
//        jwk.put("kty", "RSA");
//        jwk.put("use", "sig");
//        jwk.put("alg", "RS256");
//        jwk.put("kid", "authserver-key");
//        jwk.put("n", Base64.getEncoder().encodeToString(publicKey.getBytes()));
//        jwk.put("e", "AQAB");
//        return Map.of("keys", List.of(jwk));
//    }
//}
