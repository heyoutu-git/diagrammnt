package com.diagrammnt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class SsoUtil {
    private static final String SECRET = "diagrammnt-sso-secret-key-2024-min-256bits!!";
    private static SecretKey getKey() { return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)); }
    public static boolean validateToken(String token) {
        try { Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token); return true; } catch (Exception e) { return false; }
    }
    public static Claims parseToken(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }
    public static Long getUserId(String token) { return parseToken(token).get("userId", Long.class); }
    public static String getDisplayName(String token) { return parseToken(token).get("displayName", String.class); }
    public static String getRole(String token) { return parseToken(token).get("role", String.class); }
}
