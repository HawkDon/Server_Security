/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import filter.Secured;
import com.google.gson.Gson;
import entities.User;
import datamappers.UserDataMapper;
import logic.UserFacade;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(String user) {
        User u = gson.fromJson(user, User.class);

        String response = UserDataMapper.addUser(u);

        return response;
    }

    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(String user) {
        User u = gson.fromJson(user, User.class);

        Response response = UserFacade.userValidation(u);

        return response;
    }

    @GET
    @Secured
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String test() {
        return gson.toJson("hellooooo");
    }
}
