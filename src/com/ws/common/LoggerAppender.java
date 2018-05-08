package com.ws.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.ws.Constant;

/**
 * The class is used to log the equals lever logger.
 */
public class LoggerAppender extends DailyRollingFileAppender{

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        return this.getThreshold().equals(priority);
    }

    public static Boolean DEBUG = Boolean.TRUE;
    public static Boolean DEBUG_LESSER = Boolean.TRUE;
    private static Logger logger = Logger.getLogger(Constant.BLANK);
    private static final String tag = Constant.NETTY;

    private static SimpleDateFormat sdf = new SimpleDateFormat(Constant.TIMEFORMAT,Locale.getDefault());

    public static void logDebug(String str) {
        if (DEBUG)
            logger.debug(tag + Constant.HASHTAG_T + sdf.format(new Date()) + Constant.HASHTAG_M +str + Constant.DOLLAR_M);
    }

    public  static void logInfo(String str) {
        if (DEBUG)
            logger.info(tag + Constant.HASHTAG_T + sdf.format(new Date()) + Constant.HASHTAG_M +str + Constant.DOLLAR_M);
    }

    public static void logError(String str) {
        if (DEBUG)
            logger.error(tag + Constant.HASHTAG_T + sdf.format(new Date()) + Constant.HASHTAG_M +str + Constant.DOLLAR_M);
    }

    public static void logFatal(String str) {
        if (DEBUG)
            logger.fatal(tag + Constant.HASHTAG_T + sdf.format(new Date()) + Constant.HASHTAG_M +str + Constant.DOLLAR_M);
    }
}

