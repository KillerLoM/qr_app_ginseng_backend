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

    public String generateToken(String email, String role){
        Date now = new Date();
        Date expiryTime = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts
                .builder()
                .setSubject(email)
                .setExpiration(expiryTime)
                .claim("role", role)
                .setIssuedAt(now)
                .signWith(secreteKey)
                .compact();

    }
    public boolean validateToken(String token) throws Exception {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(secreteKey)
                    .build()
                    .parseClaimsJws(token);
        }
        catch (ExpiredJwtException e){
            throw new Exception("Expired token") ;
        }
        catch(JwtException | IllegalArgumentException e){
            throw new Exception("Token is not valid") ;
        }
        return true;
    }
    public String getRoleFromToken(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(secreteKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .get("role", String.class );
        }catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Token is not valid");
        }
    }
}
