package logic;

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

public class Token {

    // Convert JSON to object

    public static String createToken(User user) {

        // Temporarely
        String secretPassword = user.getUserName();

        // Used algoritm to sign our password.

        Algorithm algorithm = Algorithm.HMAC256(secretPassword);

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

    public static void validateToken(String token) throws Exception {
        User user = decodeTokenForVerification(token);

        // Used algoritm to sign our password.
        Algorithm algorithm = Algorithm.HMAC256(user.getUserName());

        try {
            // Verify token else throw exception.
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("id", user.getId())
                    .withClaim("userName", user.getUserName())
                    .build();
            verifier.verify(token);
        } catch (JWTVerificationException exception){
            System.out.println("Invalid signature claims happened. The token does not match");
            throw new Exception();
        }

    }

    public static User decodeTokenForVerification (String token) {
        // Get json
        Gson gson = new Gson();
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
