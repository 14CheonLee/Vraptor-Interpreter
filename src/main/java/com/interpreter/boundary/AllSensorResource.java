package com.interpreter.boundary;

import com.interpreter.system.BinaryAccess;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sensor")
public class AllSensorResource extends ResourceObject {

    private BinaryAccess binaryAccess = BinaryAccess.getInstance();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDefaultState() {
        JsonObject statusJsonObject = Json.createObjectBuilder()
                .add("status", "ok")
                .build();

        return Response.ok(statusJsonObject.toString()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/get_all_data")
    public Response getAllData() {
        JsonObject dataJsonObject = Json.createObjectBuilder()
                .add("sensor", binaryAccess.getAllSensorData().toJson())
                .build();

        return Response.ok(dataJsonObject.toString()).build();
    }
}
