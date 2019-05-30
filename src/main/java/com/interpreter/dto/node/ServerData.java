package com.interpreter.dto.node;

import com.interpreter.dto.DataObject;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;

public class ServerData extends DataObject {

    private final ArrayList<NodeData> nodeDataList;
    private final int serverPowerStatus;

    private ServerData(ServerDataBuilder serverDataBuilder) {
        this.nodeDataList = serverDataBuilder.nodeDataList;
        this.serverPowerStatus = serverDataBuilder.serverPowerStatus;
    }

    public ArrayList<NodeData> getNodeDataList() {
        return nodeDataList;
    }

    public int getServerPowerStatus() {
        return serverPowerStatus;
    }

    public JsonObject getNodeDataListToJson() {
        JsonArrayBuilder nodeDataList = Json.createArrayBuilder();

        for (NodeData nodeData : this.nodeDataList) {
            nodeDataList.add(nodeData.toJson());
        }

        return Json.createObjectBuilder()
                .add("node_data", nodeDataList)
                .build();
    }

    /**
     * Builder
     */
    public static class ServerDataBuilder {
        private ArrayList<NodeData> nodeDataList;
        private int serverPowerStatus;

        public ServerDataBuilder() {

        }

        public ServerDataBuilder setNodeDataList(ArrayList<NodeData> nodeDataList) {
            this.nodeDataList = nodeDataList;
            return this;
        }

        public ServerDataBuilder setServerPowerStatus(int powerStatus) {
            this.serverPowerStatus = powerStatus;
            return this;
        }

        public ServerData build() {
            return new ServerData(this);
        }
    }

    @Override
    public JsonObject toJson() {
//        JsonArrayBuilder nodeDataListBuilder = Json.createArrayBuilder();
//
//        for (NodeData nodeData : this.nodeDataList) {
//            nodeDataListBuilder.add(nodeData.toJson());
//        }

        return Json.createObjectBuilder()
//                .add("node_data", nodeDataListBuilder)
                .add("power_status", this.serverPowerStatus)
                .build();
    }

    @Override
    public String toString() {
        return "ServerData{" +
                "serverPowerStatus=" + serverPowerStatus +
//                ", nodeDataList=" + nodeDataList +
                '}';
    }
}
