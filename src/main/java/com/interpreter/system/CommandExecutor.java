package com.interpreter.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandExecutor {

    public static CommandExecutor getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final CommandExecutor INSTANCE = new CommandExecutor();
    }

    private static final Logger logger = LoggerFactory.getLogger(CommandExecutor.class);

    private CommandExecutor() { }

    public String execute(String command) {
        StringBuilder cmdResultBuilder = new StringBuilder();
        Process process;
        int waitResult = 0;

        try {
            process = Runtime.getRuntime().exec(command);
            waitResult = process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                cmdResultBuilder.append(line);
            }
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            logger.error("Wait Code : " + waitResult);
        }

        return cmdResultBuilder.toString();
    }
}
