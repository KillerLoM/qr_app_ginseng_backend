package qr.app.backend.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
@Component
public class JwtUtils {
    private final Key secreteKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    @Value("${application.jwt.expiration-time}")
    private long EXPIRATION_TIME;

    public String generateToken(String email){
        Date now = new Date();
        Date expiryTime = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts
                .builder()
                .setSubject(email)
                .setExpiration(expiryTime)
                .setIssuedAt(now)
                .signWith(secreteKey)
                .compact();

    }
    public String validateToken(String token)  {
            Jwts.parserBuilder()
                    .setSigningKey(secreteKey)
                    .build()
                    .parseClaimsJws(token);
            return "valid";

    }
}
