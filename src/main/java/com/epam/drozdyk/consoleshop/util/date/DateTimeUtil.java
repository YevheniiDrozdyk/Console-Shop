package com.epam.drozdyk.consoleshop.util.date;

import com.epam.drozdyk.consoleshop.constant.Message;
import com.epam.drozdyk.consoleshop.exception.IllegalDateFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts input string into date.
 *
 * @author Yevhenii Drozdyk
 * @version 2.0 27 Mar 2017
 */
public class DateTimeUtil {
    public static final String DATE_FORMAT_PATTERN = "yyyy.MM.dd:HH:mm";
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);

    private DateTimeUtil() {

    }

    /**
     * Parses input string into date object.
     *
     * @param input input string
     * @return converted date
     */
    public static Date parse(String input) {
        try {
            return dateFormatter.parse(input);
        } catch (ParseException e) {
            throw new IllegalDateFormatException(Message.ERROR_DATE_FORMAT, e.getCause());
        }
    }
}
