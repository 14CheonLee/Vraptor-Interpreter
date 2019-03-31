package com.interpreter.dto.node;

import com.interpreter.dto.DataObject;

import javax.json.Json;
import javax.json.JsonObject;

public class NodeData extends DataObject {

    private final int nodeNumber;
    private final boolean powerStatus;

    private NodeData(NodeDataBuilder nodeDataBuilder) {
        this.nodeNumber = nodeDataBuilder.nodeNumber;
        this.powerStatus = nodeDataBuilder.powerStatus;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public boolean isPowerStatus() {
        return powerStatus;
    }

    /**
     * Builder
     */
    public static class NodeDataBuilder {

        private int nodeNumber;
        private boolean powerStatus;

        public NodeDataBuilder() {

        }

        public NodeDataBuilder setNodeNumber(int nodeNumber) {
            this.nodeNumber = nodeNumber;
            return this;
        }

        public NodeDataBuilder setPowerStatus(boolean powerStatus) {
            this.powerStatus = powerStatus;
            return this;
        }

        public NodeData build() {
            return new NodeData(this);
        }
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("node_number", this.nodeNumber)
                .add("power_status", this.powerStatus)
                .build();
    }

    @Override
    public String toString() {
        return "NodeData{" +
                "nodeNumber=" + nodeNumber +
                ", powerStatus=" + powerStatus +
                '}';
    }
}
