package com.interpreter.dto.node;

import com.interpreter.dto.DataObject;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;

public class ServerData extends DataObject {

    private final ArrayList<NodeData> nodeDataList;

    private ServerData(ServerDataBuilder serverDataBuilder) {
        this.nodeDataList = serverDataBuilder.nodeDataList;
    }

    public ArrayList<NodeData> getNodeDataList() {
        return nodeDataList;
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

        public ServerDataBuilder() {

        }

        public ServerDataBuilder setNodeDataList(ArrayList<NodeData> nodeDataList) {
            this.nodeDataList = nodeDataList;
            return this;
        }

        public ServerData build() {
            return new ServerData(this);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonArrayBuilder nodeDataListBuilder = Json.createArrayBuilder();

        for (NodeData nodeData : this.nodeDataList) {
            nodeDataListBuilder.add(nodeData.toJson());
        }

        return Json.createObjectBuilder()
                .add("node_data", nodeDataListBuilder)
                .build();
    }
}
