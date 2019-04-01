package com.interpreter.system;

import com.interpreter.dto.AllSensorData;
import com.interpreter.dto.chassis.ChassisData;
import com.interpreter.dto.chassis.FanData;
import com.interpreter.dto.node.NodeData;
import com.interpreter.dto.node.ServerData;

import java.util.ArrayList;

public class BinaryAccess extends AccessObject implements Accessable {

    private static BinaryAccess instance;

    public static BinaryAccess getInstance() {
        if (instance == null) {
            instance = new BinaryAccess();
        }

        return instance;
    }

    private BinaryAccess() { }

    public boolean setFanMode(boolean fanAutoSwitch) {
        return true;
    }

    public boolean setFanSpeed(int fanNumber, int speed) {
        return true;
    }

    public boolean setPowerStatus(int nodeNumber, boolean powerStatus) {
        return true;
    }

    public AllSensorData getAllSensorData() {
        /**
         * @TODO
         * Should get system data
         * These are dummy data
         */
        return new AllSensorData.AllSensorDataBuilder()
                .setChassisData(getChassisData())
                .setServerData(getServerData())
                .build();
    }

    public ChassisData getChassisData() {
        /**
         * @TODO
         * Should get system data
         * These are dummy data
         */
        return new ChassisData.ChassisDataBuilder()
                .setTemperature(30)
                .setFanDataList(getAllFanData())
                .setFanAutoSwitch(true)
                .build();
    }

    public FanData getFanData(int fanNumber) {
        /**
         * @TODO
         * Should get system data
         * These are dummy data
         */
        return new FanData.FanDataBuilder()
                .setFanNumber(fanNumber)
                .setSpeed(200)
                .build();
    }

    public ArrayList<FanData> getAllFanData() {
        /**
         * @TODO
         * Should get system data
         * (6 역시 밖의 .properties 로 뺄 것)
         */
        ArrayList<FanData> fanDataList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            fanDataList.add(getFanData(i));
        }

        return fanDataList;
    }

    public ServerData getServerData() {
        /**
         * @TODO
         * Should get system data
         * These are dummy data
         */
        return new ServerData.ServerDataBuilder()
                .setNodeDataList(getAllNodeData())
                .build();
    }

    public NodeData getNodeData(int nodeNumber) {
        /**
         * @TODO
         * Should get system data
         */
        return new NodeData.NodeDataBuilder()
                .setNodeNumber(nodeNumber)
                .setPowerStatus(true)
                .build();
    }

    public ArrayList<NodeData> getAllNodeData() {
        /**
         * @TODO
         * Should get system data
         * (32 역시 밖의 .properties 로 뺄 것)
         */
        ArrayList<NodeData> nodeDataList = new ArrayList<>();

        for (int i = 0; i < 32; i++) {
            nodeDataList.add(getNodeData(i));
        }

        return nodeDataList;
    }
}
