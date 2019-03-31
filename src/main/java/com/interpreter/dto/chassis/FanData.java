package com.interpreter.dto.chassis;

import com.interpreter.dto.DataObject;

import javax.json.Json;
import javax.json.JsonObject;

public class FanData extends DataObject {

    private final int fanNumber;
    private final int speed;

    private FanData(FanDataBuilder fanDataBuilder) {
        this.fanNumber = fanDataBuilder.fanNumber;
        this.speed = fanDataBuilder.speed;
    }

    public int getFanNumber() {
        return fanNumber;
    }

    public int getSpeed() {
        return speed;
    }

    /**
     * Builder
     */
    public static class FanDataBuilder {
        private int fanNumber;
        private int speed;

        public FanDataBuilder() {

        }

        public FanDataBuilder setFanNumber(int fanNumber) {
            this.fanNumber = fanNumber;
            return this;
        }

        public FanDataBuilder setSpeed(int speed) {
            this.speed = speed;
            return this;
        }

        public FanData build() {
            return new FanData(this);
        }
    }

    @Override
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("fan_number", this.fanNumber)
                .add("speed", this.speed)
                .build();
    }

    @Override
    public String toString() {
        return "FanData{" +
                "fanNumber=" + fanNumber +
                ", speed=" + speed +
                '}';
    }
}
