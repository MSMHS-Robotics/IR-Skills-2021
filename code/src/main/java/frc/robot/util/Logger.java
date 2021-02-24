package frc.robot.util;

import java.io.File;
import java.util.ArrayList;

public class Logger {
    private ArrayList<String> log = new ArrayList<>();
    private int linesAdded = 0;
    private File logFile;
    private static Logger logger;

    public Logger() {
        logger = new Logger();
    }

    public static Logger getInstance() {
        return logger;
    }

    public void log(String string) {
        log.add(string);
        linesAdded++;
    }
}
