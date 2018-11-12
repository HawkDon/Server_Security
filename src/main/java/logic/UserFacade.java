package logic;

import com.google.gson.Gson;
import datamappers.UserDataMapper;
import entities.ErrorMessage;
import entities.User;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserFacade {

    public static Response userValidation(User u) {
        // Get tools
        Gson gson = new Gson();

        // Check for validation
        boolean isIdentical = UserDataMapper.loginUser(u);

        Response response;

        if(isIdentical) {
            // Get credentials and sign it a token
            User user = UserDataMapper.getUserCredentials(u);
            String token = Token.createToken(user);
            response = Response.ok(gson.toJson(token), MediaType.APPLICATION_JSON).build();
        } else {
            response = Response.ok(gson.toJson(new ErrorMessage("Wrong username or password, please try again.", true)), MediaType.APPLICATION_JSON).build();
        }
        return response;
    }
}
