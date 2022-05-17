package proyect.sharemelody.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
    /**
     * Consigue la instancia del logger
     * @return devuelve el logar si ha encontrado la clase .properties y null si no existe
     */
    public static void info(String s){
        try{
            InputStream configFile = Log.class.getResourceAsStream("/config/logging.properties");
            LogManager.getLogManager().readConfiguration(configFile);

        }catch(SecurityException | IOException | NullPointerException e){
            System.out.println("Error al cargar el logger");
        }
        Logger l = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        l.info(s);
    }

    public static void severe(String s) {
        try {
            InputStream configFile = Log.class.getResourceAsStream("/config/logging.properties");
            LogManager.getLogManager().readConfiguration(configFile);
        } catch (SecurityException | IOException | NullPointerException e) {
            System.out.println("Error al cargar el logger");
        }
        Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.severe(s);
    }
}
