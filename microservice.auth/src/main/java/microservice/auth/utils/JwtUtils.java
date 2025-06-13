package microservice.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component
public class JwtUtils {

    @Value("${security.key.private}")
    private String privateKey;
    @Value("${security.user.generator}")
    private String userGenerator;


    public String createToken(String user, String rol){
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(user)
                .withClaim("roles", List.of(rol))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+1800000))
                .withJWTId(UUID.randomUUID().toString())
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
        return jwtToken;
    }

}
