package com.interpreter.boundary;

import com.interpreter.system.BinaryAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/fan")
public class FanResource extends ResourceObject {

    private static final Logger logger = LoggerFactory.getLogger(FanResource.class);
    private BinaryAccess binaryAccess = BinaryAccess.getInstance();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDefaultStatus() {
        JsonObject statusJsonObject = Json.createObjectBuilder()
                .add("status", "ok")
                .build();

        return Response.ok(statusJsonObject.toString()).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/set_auto_switch")
    public Response setFanMode(@FormParam("fan_auto_switch") boolean fanAutoSwitch, @FormParam("default_temperature") int defaultTemperature) {
        JsonObject statusJsonObject;

        try {
            binaryAccess.setFanMode(fanAutoSwitch, defaultTemperature);

            /**
             * @TODO
             * status 와 data 부분 합치는 것 따로 부모 클래스에서 만들어서 상속받을 것 (중복 제거)
             */
            JsonObject dataJsonObject = Json.createObjectBuilder()
                    .add("fan_auto_switch", fanAutoSwitch)
                    .add("default_temperature", defaultTemperature)
                    .build();

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
                    .add("data", dataJsonObject)
                    .build();
        } catch (Exception e) {
            logger.error("Failed to set 'fan_mode' data to system : " + e);

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "fail")
                    .build();
        }
        
        return Response.ok(statusJsonObject.toString()).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/set_speed")
    public Response setFanSpeed(@FormParam("fan_number") int fanNumber, @FormParam("speed") int speed) {
        JsonObject statusJsonObject;

        try {
            binaryAccess.setFanSpeed(fanNumber, speed);

            JsonObject dataJsonObject = Json.createObjectBuilder()
                    .add("fan_number", fanNumber)
                    .add("speed", speed)
                    .build();

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
                    .add("data", dataJsonObject)
                    .build();
        } catch (Exception e) {
            logger.error("Failed to set 'fan_speed' data to system : " + e);

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "fail")
                    .build();
        }

        return Response.ok(statusJsonObject.toString()).build();
    }
}
