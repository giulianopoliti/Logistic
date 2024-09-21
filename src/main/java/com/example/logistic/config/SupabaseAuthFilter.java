package com.example.logistic.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.logistic.model.roles.*;
import com.example.logistic.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class SupabaseAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioService usuarioService;

    private final JWTVerifier verifier;

    public SupabaseAuthFilter() {
        String jwtSecret = "ZxYtpbpBVOF8RLym+z3dvvCuplLPqYysAFwK14440CYStcS1gq8VW/WfuRblhlzqsb5b0MzZFl2nXpm43NOZXQ==";
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
        this.verifier = JWT.require(algorithm).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = extractToken(request);
        if (token != null) {
            try {
                DecodedJWT jwt = verifier.verify(token);
                String authId = jwt.getSubject();

                Usuario usuario = usuarioService.findByAuthId(authId);
                if (usuario == null) {
                    throw new RuntimeException("Usuario no encontrado");
                }



                List<GrantedAuthority> authorities = new ArrayList<>();

                // Agrega ROLE_SUPERADMIN si es necesario
                if (usuario instanceof Admin) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_SUPERADMIN"));
                }
                if (usuario instanceof Driver) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_DRIVER"));
                }
                if (usuario instanceof OperadorDeposito) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_OPERADOR_DEPOSITO"));
                }
                if (usuario instanceof Vendedor) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_VENDEDOR"));
                }

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        usuario, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}