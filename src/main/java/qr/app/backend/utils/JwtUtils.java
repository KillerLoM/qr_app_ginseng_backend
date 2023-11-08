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
    @Value("${server.secret-key}")
    private String secretKey;
    @Value("${server.expiration-time}")
    private long EXPIRATION_TIME;
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }
    public String generateToken(String email){
        Date now = new Date();
        Date expiryTime = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts
                .builder()
                .setSubject(email)
                .setExpiration(expiryTime)
                .setIssuedAt(now)
                .signWith(key())
                .compact();

    }
    public String validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token);
            return "valid";
        }catch(ExpiredJwtException ex){
            return "token is expired";
        }catch(JwtException e){
            return "Failed";
        }catch (IllegalArgumentException  e){
            return "Token is not valid";
        }
    }
}
