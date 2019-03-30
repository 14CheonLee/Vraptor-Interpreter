package com.interpreter.boundary;

import com.interpreter.dto.chassis.ChassisData;
import com.interpreter.dto.chassis.FanData;
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
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/set_fan_mode")
    public Response setFanMode(@FormParam("fan_auto_switch") boolean fanAutoSwitch) {
        JsonObject statusJsonObject;

        try {
//            ChassisData chassisData = new ChassisData.ChassisDataBuilder()
//                    .setFanAutoSwitch(fanAutoSwitch)
//                    .build();
            binaryAccess.setFanMode(fanAutoSwitch);

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
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
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/set_speed")
    public Response setFanSpeed(@FormParam("fan_num") int fanNumber, @FormParam("speed") int speed) {
        JsonObject statusJsonObject;

        try {
//            FanData fanData = new FanData.FanDataBuilder()
//                    .setFanNumber(fanNumber)
//                    .setSpeed(speed)
//                    .build();

            binaryAccess.setFanSpeed(fanNumber, speed);

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
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
