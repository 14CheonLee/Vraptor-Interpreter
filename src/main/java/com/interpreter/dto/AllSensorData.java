package com.interpreter.dto;

import com.interpreter.dto.DataObject;
import com.interpreter.dto.chassis.ChassisData;
import com.interpreter.dto.node.NodeData;

import javax.json.JsonObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AllSensorData extends DataObject {

    private final ChassisData chassisData;
    private final ArrayList nodeDataList;
    private final String timeStamp;

    private AllSensorData(AllSensorDataBuilder allSensorDataBuilder) {
        this.chassisData = allSensorDataBuilder.chassisData;
        this.nodeDataList = allSensorDataBuilder.nodeDataList;
        this.timeStamp = allSensorDataBuilder.timeStamp;
    }

    public ChassisData getChassisData() {
        return chassisData;
    }

    public ArrayList getNodeDataList() {
        return nodeDataList;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Builder
     */
    public static class AllSensorDataBuilder {
        private final String timeStamp;

        private ChassisData chassisData;
        private ArrayList nodeDataList;

        public AllSensorDataBuilder() {
            this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm..ss").format(new Date());
        }

        public AllSensorDataBuilder setChassisData(ChassisData chassisData) {
            this.chassisData = chassisData;
            return this;
        }

        public AllSensorDataBuilder setNodeDataList(ArrayList nodeDataList) {
            this.nodeDataList = nodeDataList;
            return this;
        }

        public AllSensorData build() {
            return new AllSensorData(this);
        }
    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}
