package com.epam.drozdyk.consoleshop.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Builds HTTP header.
 *
 * @author Yevhenii Drozdyk
 * @version 1.0 20 Apr 2017
 */
public class HTTPHeader {

    public static String buildHttpResponse(String json) {
        StringBuilder response = new StringBuilder();
        response.append("HTTP/1.1 200 OK\r\n");
        response.append("Server:http\r\n");
        response.append("Content-Type:json\r\n");
        response.append("Content-Length:");
        response.append(json.length());
        response.append("\r\n");
        response.append("Date:");
        response.append(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        response.append("\r\n");
        response.append("Connection:close\r\n\r\n");
        response.append(json);

        return response.toString();
    }
}
