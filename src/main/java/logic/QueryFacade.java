package logic;

import com.google.gson.Gson;
import datamappers.QueryDataMapper;
import entities.ErrorMessage;
import entities.QueryMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.List;

public class QueryFacade {

    public static Response readQuery(QueryMessage qm) {

        Gson gson = new Gson();
        List listOfItems = QueryDataMapper.getListOfItems(qm.getQuery());

        Response response;
        if (!listOfItems.isEmpty()) {
            response = Response.ok(gson.toJson(listOfItems, List.class), MediaType.APPLICATION_JSON).build();
        } else {
            response = Response.ok(gson.toJson(new ErrorMessage("Invalid sqlquery string, please try again", true)), MediaType.APPLICATION_JSON).build();
        }
        return response;
    }

    public static Response readQueryNotSafe(String id) {

        Gson gson = new Gson();
        List listOfItems = QueryDataMapper.notSafeSelectStatement(id);

        Response response;
        if (!listOfItems.isEmpty()) {
            response = Response.ok(gson.toJson(listOfItems, List.class), MediaType.APPLICATION_JSON).build();
        } else {
            response = Response.ok(gson.toJson(new ErrorMessage("Invalid sqlquery string, please try again", true)), MediaType.APPLICATION_JSON).build();
        }
        return response;
    }


    public static Response readQuerySafe(String id) {

        Gson gson = new Gson();
        List listOfItems = QueryDataMapper.safeSelectStatement(id);

        Response response;
        if (!listOfItems.isEmpty()) {
            response = Response.ok(gson.toJson(listOfItems, List.class), MediaType.APPLICATION_JSON).build();
        } else {
            response = Response.ok(gson.toJson(new ErrorMessage("Invalid query string, please try again", true)), MediaType.APPLICATION_JSON).build();
        }
        return response;
    }

    /*public static Response readQueryBatchNotSafe(String id) {

        Gson gson = new Gson();
        List listOfItems = QueryDataMapper.notSafeSelectBatchStatement(id);

        Response response;
        if (!listOfItems.isEmpty()) {
            response = Response.ok(gson.toJson(listOfItems, List.class), MediaType.APPLICATION_JSON).build();
        } else {
            response = Response.ok(gson.toJson(new ErrorMessage("Invalid query string, please try again", true)), MediaType.APPLICATION_JSON).build();
        }
        return response;
    }*/

}
