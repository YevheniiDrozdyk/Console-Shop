package com.epam.drozdyk.consoleshop.server.util;

import com.epam.drozdyk.consoleshop.constant.Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses http request.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 19 Apr 2017
 */
public class RequestParser {
    private static Pattern pattern = Pattern.compile("GET /.+(?=HTTP)");

    /**
     * Returns URL from http request.
     *
     * @param request http request
     * @return url string object
     */
    public static String getUrl(String request) {
        Matcher matcher = pattern.matcher(request);
        if (matcher.find()) {
            return matcher.group().split(" ")[1];
        }

        return Message.ERROR_ILLEGAL_URL;
    }
}
