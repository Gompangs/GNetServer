package auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gompang.ServerStarter;
import com.gompang.domain.Player;
import com.gompang.service.AuthService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

@SpringBootTest(classes = ServerStarter.class)
@RunWith(SpringRunner.class)
public class AuthTest {

    @Value("${server.token.issuer}")
    private String issuer;

    @Value("${server.token.secret}")
    private String secret;

    @Value("${server.token.expiration}")
    private int expiration;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void uuid() {
        logger.info("UUID : {}", createUUID());
    }

    @Test
    public void token() throws InterruptedException {
        Player player = new Player();
        player.setPlayerId(createUUID());
        player.setDeviceId(createUUID());

        String token = generateToken(player);
        logger.info("token : {}", token);
        logger.info("token size : {}", token.length());

        DecodedJWT decode = JWT.decode(token);

        logger.info("getId : {}", decode.getId());
        logger.info("getHeader : {}", decode.getHeader());
        logger.info("getPayload : {}", decode.getPayload());
        logger.info("getSignature : {}", decode.getSignature());
        logger.info("getClaims : {}", decode.getClaims());
        logger.info("getAlgorithm : {}", decode.getAlgorithm());
        logger.info("getAudience : {}", decode.getAudience());
        logger.info("getContentType : {}", decode.getContentType());
        logger.info("getIssuedAt : {}", decode.getIssuedAt());
        logger.info("getExpiresAt : {}", decode.getExpiresAt());

        Thread.sleep(1000);

        decode = JWT.decode(token);

        logger.info("getId : {}", decode.getId());
        logger.info("getHeader : {}", decode.getHeader());
        logger.info("getPayload : {}", decode.getPayload());
        logger.info("getSignature : {}", decode.getSignature());
        logger.info("getClaims : {}", decode.getClaims());
        logger.info("getAlgorithm : {}", decode.getAlgorithm());
        logger.info("getAudience : {}", decode.getAudience());
        logger.info("getContentType : {}", decode.getContentType());
        logger.info("getIssuedAt : {}", decode.getIssuedAt());
        logger.info("getExpiresAt : {}", decode.getExpiresAt());
    }

    private String createUUID(){
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }

    private String generateToken(Player player) {
        String token = null;
        try {
            DateTime expireDate = new DateTime(DateTimeZone.UTC);

            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withAudience(player.getPlayerId())
                    .withJWTId(createUUID())
                    .withIssuedAt(new Date())
                    .withExpiresAt(expireDate.plusMinutes(expiration).toDate())
                    .withClaim("did", player.getDeviceId()) // device id
                    .withIssuer(issuer)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException | JWTCreationException exception) {
            //UTF-8 encoding not supported
            exception.printStackTrace();
        }
        return token;
    }
}
