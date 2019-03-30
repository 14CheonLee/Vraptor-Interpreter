package com.interpreter.boundary;

import com.interpreter.dto.AllSensorData;
import com.interpreter.dto.chassis.ChassisData;
import com.interpreter.dto.node.NodeData;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/sensor")
public class AllSensorResource extends ResourceObject {

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
        /**
         * @TODO
         * 아마 바꿔야 할 것 같음. (BinaryAccess 에서 build 가 진행되도록 ? )
         */
        ChassisData chassisData = new ChassisData.ChassisDataBuilder()
                .build();
        ArrayList nodeDataList = new ArrayList<>();
        AllSensorData allSensorData = new AllSensorData.AllSensorDataBuilder()
                .setChassisData(chassisData)
                .setNodeDataList(nodeDataList)
                .build();

        return Response.ok(allSensorData.toJson().toString()).build();
    }
}
