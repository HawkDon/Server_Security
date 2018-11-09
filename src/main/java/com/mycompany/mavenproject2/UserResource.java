/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.google.gson.Gson;
import entities.User;
import entities.UserFacade;
import Tokens.Token;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Mathias BJ
 */
@Path("user")
public class UserResource {

    Gson gson = new Gson();
    Token tokenFacade = new Token();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.mycompany.mavenproject2.UserResource
     *
     * @return an instance of java.lang.String
     */
    @Path("/Tokens")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser() {
        User user = new User("Oliver", "derpderp");
        return gson.toJson(user);
    }

    @Path("/adduser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser(String user) {
        User u = gson.fromJson(user, User.class);

        UserFacade.addUser(u);
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(String user) {
        User u = gson.fromJson(user, User.class);

        boolean isIdentical = UserFacade.loginUser(u);

        String token;
        User newUser;
        Response response;

        if(isIdentical) {
                // Get credentials and sign it a token
                newUser = UserFacade.getUserCredentials(u);
                token = tokenFacade.createToken(newUser);
                response = Response.ok(gson.toJson(token), MediaType.APPLICATION_JSON).build();
        } else {
            response = Response.status(Response.Status.FORBIDDEN).build();
        }
        return response;
    }
}
