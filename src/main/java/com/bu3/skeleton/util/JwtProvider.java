package com.bu3.skeleton.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.ExpiredJwtException;

import java.util.Date;

import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

@Service
@RequiredArgsConstructor
public class JwtProvider {



    @Value("MzMtODUtMTktMjEtMTUtMjItNTktMTctMTItNDEtNDMtMTktMTYtMjItMjMtMTgtNDktMjktMjItMTItMTctMTYtMTQtNDItMTgtMTgtMTQtMTQtMTMtMTQtMTItMTctMzktMTItMTktMTUtMjItMjctMTgtMjEtMTktMTYtMTYtMTMtMTUtMTQtMTgtMTMtMTktMTYtMTYtMjMtMzItMTgtMjYtMTItMTYtMTItMTUtMTYtMTktMTItMTItMTctMzktMjctMTctMTktMjktMTctMTItMzktMTctMjMtMjctMTUtMTgtMTItMTYtMTktMTctMTUtMjEtMTctMTItMTktMTUtMjItMzItMTctMTYtMTctMTUtMTctMTItMjItMTUtMzItMTItMTgtMjItMTItMTctMTYtMTctMTgtMTktMTctMTYtMjItMzktMTctMTItMzAtMTItMjItMTMtMTItMTktMzAtMTItMTUtMTgtMTUtMTUtMTUtMTMtMTgtMTUtMTktMTktMTktMTctMTItMTctMTctMTYtMTUtMTUtMTgtMTktMTItMTktMTUtMTItMTktMTctMTUtMTktMTItMTctMTYtMTctMTItMTIt")
    private String jwtSecret;

    @Value("30000")
    private int jwtExpiration;

    public String generateToken(String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException ex) {
            // Xử lý riêng lỗi khi token hết hạn, không cần in ra lỗi
            return false;
        } catch (Exception ex) {
            // Xử lý các lỗi khác
            return false;
        }
    }

}
