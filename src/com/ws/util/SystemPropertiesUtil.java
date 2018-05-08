package com.ws.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.ws.Constant;


/**
 * The class is used to read system properties file.
 * */
public class SystemPropertiesUtil {
    private static Properties properties = null;
    static {
        InputStream in = null;
        try {
            properties = new Properties();
            in = SystemPropertiesUtil.class.getClassLoader().getResourceAsStream(Constant.SYSTEM_PROPERTIES);
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The method is used to get the value in the properties object.
     * @ value.
     * */
    public static String getPropetiesValueByKey(String key) {
        return properties.getProperty(key);
    }
}
