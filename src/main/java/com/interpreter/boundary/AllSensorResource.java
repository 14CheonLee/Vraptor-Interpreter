package com.interpreter.boundary;

import com.interpreter.system.CommandAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sensor")
public class AllSensorResource extends ResourceObject {

    private static final Logger logger = LoggerFactory.getLogger(AllSensorResource.class);
    private CommandAccess commandAccess = CommandAccess.getInstance();

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
        JsonObject dataJsonObject;

        try {
            dataJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
                    .add("sensor", commandAccess.getAllSensorData().toJson())
                    .build();

            return Response.ok(dataJsonObject.toString()).build();
        } catch (Exception e) {
            logger.error("Failed to get 'all_sensor_data' data from system : " + e);

            dataJsonObject = Json.createObjectBuilder()
                    .add("status", "fail")
                    .build();

            return Response.ok(dataJsonObject.toString()).build();
        }
    }
}
