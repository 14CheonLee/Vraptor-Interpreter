package com.interpreter.dto;

import com.interpreter.dto.chassis.ChassisData;
import com.interpreter.dto.node.ServerData;

import javax.json.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AllSensorData extends DataObject {

    private final ChassisData chassisData;
    private final ServerData serverData;
    private final String timeStamp;

    private AllSensorData(AllSensorDataBuilder allSensorDataBuilder) {
        this.chassisData = allSensorDataBuilder.chassisData;
        this.serverData = allSensorDataBuilder.serverData;
        this.timeStamp = allSensorDataBuilder.timeStamp;
    }

    public ChassisData getChassisData() {
        return chassisData;
    }

    public ServerData getServerData() {
        return serverData;
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
        private ServerData serverData;

        public AllSensorDataBuilder() {
            this.timeStamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
        }

        public AllSensorDataBuilder setChassisData(ChassisData chassisData) {
            this.chassisData = chassisData;
            return this;
        }

        public AllSensorDataBuilder setServerData(ServerData serverData) {
            this.serverData = serverData;
            return this;
        }

        public AllSensorData build() {
            return new AllSensorData(this);
        }
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("chassis_data", this.chassisData.toJson())
                .add("server_data", this.serverData.toJson())
                .add("timestamp", this.timeStamp)
                .build();
    }

    @Override
    public String toString() {
        return "AllSensorData{" +
                "chassisData=" + chassisData +
                ", serverData=" + serverData +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
