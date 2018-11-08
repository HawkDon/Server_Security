/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.google.gson.Gson;
import entities.User;
import entities.UserFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
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
    @Path("/test")
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
    public void loginUser(String user) {
        User u = gson.fromJson(user, User.class);

        boolean isIdentical = UserFacade.loginUser(u);

        System.out.println(isIdentical);
    }
}
