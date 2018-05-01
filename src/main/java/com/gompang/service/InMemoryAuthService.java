package com.gompang.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.gompang.common.support.CommonSupport;
import com.gompang.domain.Player;
import com.gompang.packet.AuthResult;
import com.gompang.repository.PlayerStore;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class InMemoryAuthService implements AuthService {

    @Value("${server.token.issuer}")
    private String issuer;

    @Value("${server.token.secret}")
    private String secret;

    @Value("${server.token.expiration}")
    private Integer expiration;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PlayerStore playerStore;

    @Autowired
    private CommonSupport commonSupport;

    @PostConstruct
    public void init() {

    }

    @Override
    public PlayerToken login(Player player) {
        Player existingPlayer = playerStore.getPlayer(player.getPlayerId());

        if (existingPlayer != null) {
            if (existingPlayer.getStoredDeviceIds().contains(player.getDeviceId())) {
                // login stored device
                logger.debug("login stored device : {}", player.getDeviceId());
            } else {
                // login new device
                logger.debug("login with new device : {}", player.getDeviceId());

                // add device id into device list
                playerStore.putDeviceId(player);
            }
        } else {
            // new Player
            logger.debug("new player : {}", player);
            playerStore.putPlayer(player);
        }

        // create token
        return new PlayerToken(player, generateToken(player));
    }

    private String generateToken(Player player) {
        String token = null;
        try {
            DateTime expireDate = new DateTime(DateTimeZone.UTC);

            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withAudience(player.getPlayerId())
                    .withJWTId(commonSupport.getUUID())
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

    private String createUUID() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }
}
