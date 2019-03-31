package com.interpreter.dto.chassis;

import com.interpreter.dto.DataObject;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.util.ArrayList;

public class ChassisData extends DataObject {

    private final int temperature;
    private final ArrayList<FanData> fanDataList;
    private final boolean fanAutoSwitch;

    private ChassisData(ChassisDataBuilder chassisDataBuilder) {
        this.temperature = chassisDataBuilder.temperature;
        this.fanDataList = chassisDataBuilder.fanDataList;
        this.fanAutoSwitch = chassisDataBuilder.fanAutoSwitch;
    }

    public int getTemperature() {
        return temperature;
    }

    public ArrayList<FanData> getFanDataList() {
        return fanDataList;
    }

    public boolean isFanAutoSwitch() {
        return fanAutoSwitch;
    }

    /**
     * Builder
     */
    public static class ChassisDataBuilder {

        private int temperature;
        private ArrayList<FanData> fanDataList;
        private boolean fanAutoSwitch;

        public ChassisDataBuilder() {

        }

        public ChassisDataBuilder setTemperature(int temperature) {
            this.temperature = temperature;
            return this;
        }

        public ChassisDataBuilder setFanDataList(ArrayList<FanData> fanDataList) {
            this.fanDataList = fanDataList;
            return this;
        }

        public ChassisDataBuilder setFanAutoSwitch(boolean fanAutoSwitch) {
            this.fanAutoSwitch = fanAutoSwitch;
            return this;
        }

        public ChassisData build() {
            return new ChassisData(this);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonArrayBuilder fanDataList = Json.createArrayBuilder();

        for (FanData fanData : this.fanDataList) {
            fanDataList.add(fanData.toJson());
        }

        return Json.createObjectBuilder()
                .add("temperature", this.temperature)
                .add("fan_data", fanDataList)
                .add("fan_auto_switch", this.fanAutoSwitch)
                .build();
    }

    @Override
    public String toString() {
        return "ChassisData{" +
                "temperature=" + temperature +
                ", fanDataList=" + fanDataList +
                ", fanAutoSwitch=" + fanAutoSwitch +
                '}';
    }
}
