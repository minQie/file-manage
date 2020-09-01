package priv.wmc.file.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import priv.wmc.file.constant.StaticConstants;

/**
 * 时间工具类
 *
 * @author 王敏聪
 * @date 2019/11/15 11:46
 */
public final class DateUtils {

    private DateUtils() {}

    public static String getCurrentDateString() {
        return new SimpleDateFormat(StaticConstants.DATE_PATTERN).format(new Date());
    }

    public static String getCurrentDateTimeString() {
        return new SimpleDateFormat(StaticConstants.DATE_TIME_PATTERN).format(new Date());
    }

    public static String getDateTimeString(Date date) {
        return getString(date, StaticConstants.DATE_TIME_PATTERN);
    }

    public static String getString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

}
