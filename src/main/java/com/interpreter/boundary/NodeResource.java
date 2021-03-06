package com.interpreter.boundary;

import com.interpreter.dto.node.NodeData;
import com.interpreter.dto.node.ServerData;
import com.interpreter.system.CommandAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/node")
public class NodeResource extends ResourceObject {

    private static final Logger logger = LoggerFactory.getLogger(NodeResource.class);
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
    @Path("/set_power_status")
    public Response setNodePowerStatus(@FormParam("node_number") int nodeNumber, @FormParam("power_status") int powerStatus) {
        JsonObject statusJsonObject;
        int commandResult;

        try {
            commandResult = commandAccess.setPowerStatus(nodeNumber, powerStatus);
            if (commandResult == -1) throw new Exception();

            JsonObject inputDataJsonObject = Json.createObjectBuilder()
                    .add("node_number", nodeNumber)
                    .add("power_status", powerStatus)
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
            logger.error("Failed to set 'power_status' data to system : " + e);

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "fail")
                    .build();
        }

        return Response.ok(statusJsonObject.toString()).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/set_server_power_status")
    public Response setServerPowerStatus(@FormParam("power_status") int powerStatus) {
        JsonObject statusJsonObject;
        int commandResult;

        try {
            commandResult = commandAccess.setServerPowerStatus(powerStatus);
            if (commandResult == -1) throw new Exception();

            JsonObject inputDataJsonObject = Json.createObjectBuilder()
                    .add("power_status", powerStatus)
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
            logger.error("Failed to set 'server_power_status' data to system : " + e);

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "fail")
                    .build();
        }

        return Response.ok(statusJsonObject.toString()).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/get_all_node_data")
    public Response getAllNodeData() {
        JsonObject dataJsonObject;

        try {
            dataJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
                    .add("nodes", commandAccess.getServerData().getNodeDataListToJson())
                    .build();

            return Response.ok(dataJsonObject.toString()).build();
        } catch (Exception e) {
            logger.error("Failed to get 'all_node_data' data from system : " + e);

            dataJsonObject = Json.createObjectBuilder()
                    .add("status", "fail")
                    .build();

            return Response.ok(dataJsonObject.toString()).build();
        }
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/get_node_data")
    public Response getNodePowerStatus(@FormParam("node_number") int nodeNumber) {
        JsonObject dataJsonObject;

        try {
            dataJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
                    .add("node", commandAccess.getNodeData(nodeNumber).toJson())
                    .build();

            return Response.ok(dataJsonObject.toString()).build();
        } catch (Exception e) {
            logger.error("Failed to get 'node_data' data from system : " + e);

            dataJsonObject = Json.createObjectBuilder()
                    .add("status", "fail")
                    .build();

            return Response.ok(dataJsonObject.toString()).build();
        }
    }
}
