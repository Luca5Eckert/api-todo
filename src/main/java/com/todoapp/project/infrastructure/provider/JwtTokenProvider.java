package com.todoapp.project.infrastructure.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.security.Key;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long validityInMilliseconds;

    // O construtor agora recebe a chave secreta e o tempo de validade
    public JwtTokenProvider(String secret, long validityInMilliseconds) {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Chave gerada a partir da string secreta
        this.validityInMilliseconds = validityInMilliseconds;
    }

    /**
     * Gera um token JWT para um usuário, incluindo o ID.
     * @param username O nome de usuário para o qual o token será gerado.
     * @param userId O ID do usuário a ser incluído no token.
     * @return O token JWT como uma string.
     */
    public String generateToken(String username, String userId) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("userId", userId);

        Date now = new Date();
        Date expiration = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida um token JWT.
     * @param token O token JWT a ser validado.
     * @return true se o token for válido, false caso contrário.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém o ID do usuário de um token JWT.
     * @param token O token JWT.
     * @return O ID do usuário.
     */
    public UUID getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return UUID.fromString( (String) claims.get("userId") );
    }
}