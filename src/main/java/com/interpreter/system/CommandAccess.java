package com.interpreter.system;

import com.interpreter.Configuration;
import com.interpreter.dto.AllSensorData;
import com.interpreter.dto.chassis.ChassisData;
import com.interpreter.dto.chassis.FanData;
import com.interpreter.dto.node.NodeData;
import com.interpreter.dto.node.ServerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class CommandAccess extends AccessObject implements Accessable {

    public static CommandAccess getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final CommandAccess INSTANCE = new CommandAccess();
    }

    private static final Logger logger = LoggerFactory.getLogger(CommandAccess.class);
    private Configuration configuration = Configuration.getInstance();
    private CommandExecutor commandExecutor = CommandExecutor.getInstance();

    private CommandAccess() { }

    public boolean setFanMode(int fanAutoSwitch) {
        int commandResult;
        String cmd = configuration.getProperty("set_fan_mode") + " " + fanAutoSwitch;

        commandResult = Integer.parseInt(commandExecutor.execute(cmd));

        return commandResult != -1;
    }

    public boolean setFanMode(int fanAutoSwitch, int defaultTemperature) {
        int commandResult;
        String cmd = configuration.getProperty("set_fan_mode") + " " + fanAutoSwitch + " " + defaultTemperature;

        commandResult = Integer.parseInt(commandExecutor.execute(cmd));

        return commandResult != -1;
    }

    public boolean setFanSpeed(int fanNumber, int speed) {
        int commandResult;
        String cmd = configuration.getProperty("set_fan_speed") + " " + fanNumber + " " + speed;

        commandResult = Integer.parseInt(commandExecutor.execute(cmd));

        return commandResult != -1;
    }

    /**
     * @param nodeNumber
     * @param powerStatus (0 : Reset, 1 : Off)
     */
    public boolean setPowerStatus(int nodeNumber, int powerStatus) {
        int commandResult;
        String cmd = configuration.getProperty("set_power_status") + " " + nodeNumber + " " + powerStatus;

        commandResult = Integer.parseInt(commandExecutor.execute(cmd));

        return commandResult != -1;
    }

    public AllSensorData getAllSensorData() {
        return new AllSensorData.AllSensorDataBuilder()
                .setChassisData(getChassisData())
                .setServerData(getServerData())
                .build();
    }

    public ChassisData getChassisData() {
        float currentTemperatureValue;
        String cmd = configuration.getProperty("get_temperature");

        currentTemperatureValue = Float.parseFloat(commandExecutor.execute(cmd));

        return new ChassisData.ChassisDataBuilder()
                .setTemperature(currentTemperatureValue)
                .setFanDataList(getAllFanData())
                .setFanAutoSwitch(getFanMode())
                .build();
    }

    public int getFanMode() {
        int currentFanMode;
        String cmd = configuration.getProperty("get_fan_mode");

        // 0 : Manual | 1 : Auto | -1 : Error
        currentFanMode = Integer.parseInt(commandExecutor.execute(cmd));

        return currentFanMode;
    }

    public FanData getFanData(int fanNumber) {
        int currentFanSpeed;
        String cmd = configuration.getProperty("get_fan_speed") + " " + fanNumber;

        currentFanSpeed = Integer.parseInt(commandExecutor.execute(cmd));

        return new FanData.FanDataBuilder()
                .setFanNumber(fanNumber)
                .setSpeed(currentFanSpeed)
                .build();
    }

    public ArrayList<FanData> getAllFanData() {
        int fanNumber = Integer.parseInt(configuration.getProperty("fan_number"));
        ArrayList<FanData> fanDataList = new ArrayList<>();

        for (int i = 0; i < fanNumber; i++) {
            fanDataList.add(getFanData(i));
        }

        return fanDataList;
    }

    public ServerData getServerData() {
        return new ServerData.ServerDataBuilder()
                .setNodeDataList(getAllNodeData())
                .build();
    }

    public NodeData getNodeData(int nodeNumber) {
        int currentPowerStatus;
        String cmd = configuration.getProperty("get_power_status") + " " + nodeNumber;

        currentPowerStatus = Integer.parseInt(commandExecutor.execute(cmd));

        return new NodeData.NodeDataBuilder()
                .setNodeNumber(nodeNumber)
                .setPowerStatus(currentPowerStatus != -1)
                .build();
    }

    public ArrayList<NodeData> getAllNodeData() {
        int nodeNumber = Integer.parseInt(configuration.getProperty("node_number"));
        ArrayList<NodeData> nodeDataList = new ArrayList<>();

        for (int i = 0; i < nodeNumber; i++) {
            nodeDataList.add(getNodeData(i));
        }

        return nodeDataList;
    }
}
