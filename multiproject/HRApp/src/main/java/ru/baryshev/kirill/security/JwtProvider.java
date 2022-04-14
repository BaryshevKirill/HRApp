package ru.baryshev.kirill.security;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Component
@Log
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final UserDetailsService userDetailsService;

    public JwtProvider(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }

//    public String generateToken(String login) {
//        Date date = Date.from(LocalDateTime.now().plusHours(8).atZone(ZoneId.systemDefault()).toInstant());
//        return Jwts.builder()
//                .setSubject(login)
//                .setIssuedAt(new Date())
//                .setExpiration(date)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
//    }

    public String generateToken(String login, String role) {

        Claims claims = Jwts.claims().setSubject(login);
        claims.put("role", role);
//        Date date = Date.from(LocalDateTime.now().plusHours(8).atZone(ZoneId.systemDefault()).toInstant());
        Date date = Date.from(LocalDateTime.now().plusSeconds(600).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
            return !claimsJws.getBody().getExpiration().before(new Date());
//        } catch (ExpiredJwtException expEx) {
//            log.severe("Token expired" + expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.severe("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            log.severe("Malformed jwt");
        } catch (SignatureException sEx) {
            log.severe("Invalid signature");
//        } catch (Exception e) {
//            log.severe("invalid token");
        }
        return false;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getLoginFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getLoginFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

//    public String resolveToken(HttpServletRequest request) {
//        return request.getHeader()
//    }

}
