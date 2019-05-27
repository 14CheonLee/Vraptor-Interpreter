package com.interpreter.boundary;

import com.interpreter.system.CommandAccess;
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
    private CommandAccess commandAccess = CommandAccess.getInstance();

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
    public Response setFanMode(@FormParam("fan_auto_switch") int fanAutoSwitch) {
        JsonObject statusJsonObject;
        int commandResult;

        try {
            commandResult = commandAccess.setFanMode(fanAutoSwitch);
            if (commandResult == -1) throw new Exception();

            /**
             * @TODO
             * status 와 data 부분 합치는 것 따로 부모 클래스에서 만들어서 상속받을 것 (중복 제거)
             */
            JsonObject inputDataJsonObject = Json.createObjectBuilder()
                    .add("fan_auto_switch", fanAutoSwitch)
                    .build();

            JsonObject outputDataJsonObject = Json.createObjectBuilder()
                    .add("command_result", commandResult)
                    .build();

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
                    .add("input_data", inputDataJsonObject)
                    .add("output_data", outputDataJsonObject)
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
    @Path("/set_auto_switch_with_temp")
    public Response setFanModeWithTemperature(@FormParam("fan_auto_switch") int fanAutoSwitch, @FormParam("default_temperature") int defaultTemperature) {
        JsonObject statusJsonObject;
        int commandResult;

        try {
            commandResult = commandAccess.setFanMode(fanAutoSwitch, defaultTemperature);
            if (commandResult == -1) throw new Exception();

            JsonObject inputDataJsonObject = Json.createObjectBuilder()
                    .add("fan_auto_switch", fanAutoSwitch)
                    .add("default_temperature", defaultTemperature)
                    .build();

            JsonObject outputDataJsonObject = Json.createObjectBuilder()
                    .add("command_result", commandResult)
                    .build();

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
                    .add("input_data", inputDataJsonObject)
                    .add("output_data", outputDataJsonObject)
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
        int commandResult;

        try {
            commandResult = commandAccess.setFanSpeed(fanNumber, speed);
            if (commandResult == -1) throw new Exception();

            JsonObject inputDataJsonObject = Json.createObjectBuilder()
                    .add("fan_number", fanNumber)
                    .add("speed", speed)
                    .build();

            JsonObject outputDataJsonObject = Json.createObjectBuilder()
                    .add("command_result", commandResult)
                    .build();

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
                    .add("input_data", inputDataJsonObject)
                    .add("output_data", outputDataJsonObject)
                    .build();
        } catch (Exception e) {
            logger.error("Failed to set 'fan_speed' data to system : " + e);

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "fail")
                    .build();
        }

        return Response.ok(statusJsonObject.toString()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/get_auto_switch")
    public Response getFanMode() {
        JsonObject dataJsonObject;
        int currentFanMode;

        try {
            currentFanMode = commandAccess.getFanMode();
            if (currentFanMode == -1) throw new Exception();

            dataJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
                    .add("fan_auto_switch", currentFanMode)
                    .build();

            return Response.ok(dataJsonObject.toString()).build();
        } catch (Exception e) {
            logger.error("Failed to get 'fan_mode' data from system : " + e);

            dataJsonObject = Json.createObjectBuilder()
                    .add("status", "fail")
                    .build();

            return Response.ok(dataJsonObject.toString()).build();
        }
    }
}
