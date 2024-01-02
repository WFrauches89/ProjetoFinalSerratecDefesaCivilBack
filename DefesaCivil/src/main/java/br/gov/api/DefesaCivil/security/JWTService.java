package br.gov.api.DefesaCivil.security;

import java.util.Date;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import br.gov.api.DefesaCivil.entities.UsuarioEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JWTService {
    
    private static final String SECURITY_KEY = "ChaveMuitoSecretaDefesaCivil";

    public String gerarToken(Authentication authentication){

        int tempoExpiracao = 86400000;
        Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
        UsuarioEntity usuario = (UsuarioEntity) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(usuario.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
                .compact();
    }

    public Optional<Long> obterIdDoUsuario(String token){
        try {
        	Claims claims = Jwts.parser().setSigningKey(SECURITY_KEY).parseClaimsJws(token).getBody();  
        	return Optional.ofNullable(Long.parseLong(claims.getSubject()));

        }catch (Exception e) {
        	return Optional.empty();
        }
    }

}
