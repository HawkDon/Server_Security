package Tokens;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import entities.User;

import java.util.Base64;
import java.util.Date;

public class Token {

    // password to sign. Think of it as a password and the bread and butter to keep the token secret. If this password is exposed you are having a bad time.
    private final String secretPassword = "secret";

    // Used algoritm to sign our password.
    private final Algorithm algorithm = Algorithm.HMAC256(secretPassword);

    // Convert JSON to object
    Gson gson = new Gson();


    public String createToken(User user) {
        String token = null;
        try {
            token = JWT.create()
                    .withClaim("id", user.getId())
                    .withClaim("userName", user.getUserName())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            System.out.println("Claims could not be converted to token:");
            System.out.println(exception.getMessage());
        }
        return token;
    }

    public void verifyToken(String token) {
        User user = decodeTokenForVerification(token);
        try {
            // Verify token else throw exception.
            System.out.println(JWT.decode(token).getPayload());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("id", user.getUserName())
                    .withClaim("password", user.getPassword())
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt.getToken());
            System.out.println(new String(Base64.getDecoder().decode(jwt.getPayload())));
        } catch (JWTVerificationException exception){
            System.out.println("Invalid signature claims happened. The token does not match");
            System.out.println(exception.getMessage());
        }

    }

    public User decodeTokenForVerification (String token) {
        // Decode to verify later
        User user = new User();
        try {
            DecodedJWT tokenDecoded = JWT.decode(token);
            String jsonPayload = new String(Base64.getDecoder().decode(tokenDecoded.getPayload()));
            user = gson.fromJson(jsonPayload, User.class);
            return user;
        } catch(JWTDecodeException exception) {
            System.out.println("Token decoding failed in decodeTokenForVerification: ");
            System.out.println(exception.getMessage());
        }
        return user;
    }
}
