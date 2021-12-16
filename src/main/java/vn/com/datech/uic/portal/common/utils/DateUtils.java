package vn.com.datech.uic.portal.common.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The type Date utils.
 */
@Service
@Log4j2
public final class DateUtils {

    private DateUtils() {
    }

    /**
     * Gets current time in millis.
     *
     * @return the current time in millis
     */
    public static long getCurrentTimeInMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    /**
     * Add day date.
     *
     * @param dt the dt
     * @return the date
     */
    public Date addDay(Date dt) {
    	
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(dt); 
    	c.add(Calendar.DATE, 1);
    	dt = c.getTime();

        return dt;
    }

    /**
     * Sub month date.
     *
     * @param dt    the dt
     * @param month the month
     * @return the date
     */
    public Date subMonth(Date dt,int month ) {
	
    	Calendar c = Calendar.getInstance(); 
    	c.setTime(dt); 
    	c.add(Calendar.MONTH, month);
    	dt = c.getTime();

        return dt;
    }

    /**
     * String to date date.
     *
     * @param strDate the str date
     * @param pattern the pattern
     * @return the date
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (strDate == null || strDate.isEmpty()) {
            return null;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = (Date) sdf.parseObject(strDate);
        } catch (ParseException e) {
            log.error("DateUtils stringToDate():" + e);
        }
        return date;
    }

    /**
     * Date to string string.
     *
     * @param date    the date
     * @param pattern the pattern
     * @return the string
     */
    public static String dateToString(Date date, String pattern) {
        String result = null;
        if (date == null) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            result = sdf.format(date);
        } catch (Exception e) {
            log.error("DateUtils dateToString():" + e);
        }
        return result;
    }
}
