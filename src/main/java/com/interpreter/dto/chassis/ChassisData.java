package com.interpreter.dto.chassis;

import com.interpreter.dto.DataObject;

import javax.json.JsonObject;
import java.util.ArrayList;

public class ChassisData extends DataObject {

    private final int temperature;
    private final ArrayList fanDataList;
    private final boolean fanAutoSwitch;

    private ChassisData(ChassisDataBuilder chassisDataBuilder) {
        this.temperature = chassisDataBuilder.temperature;
        this.fanDataList = chassisDataBuilder.fanDataList;
        this.fanAutoSwitch = chassisDataBuilder.fanAutoSwitch;
    }

    public int getTemperature() {
        return temperature;
    }

    public ArrayList getFanDataList() {
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
        private ArrayList fanDataList;
        private boolean fanAutoSwitch;

        public ChassisDataBuilder() {

        }

        public ChassisDataBuilder setTemperature(int temperature) {
            this.temperature = temperature;
            return this;
        }

        public ChassisDataBuilder setFanDataList(ArrayList fanDataList) {
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
        return null;
    }
}
