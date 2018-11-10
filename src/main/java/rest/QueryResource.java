package rest;



import com.google.gson.Gson;
import entities.QueryMessage;
import logic.QueryFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.InputStream;

@Path("queries")
public class QueryResource {

    Gson gson = new Gson();

    @Context
    private UriInfo context;

    public QueryResource() {
    }

    @Path("/execute")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQueryResult(String queryMessage){
        QueryMessage qm = gson.fromJson(queryMessage, QueryMessage.class);
        
        Response response = QueryFacade.readQuery(qm);

        return response;
    }
}
