package com.epam.drozdyk.consoleshop.server.util;

import com.epam.drozdyk.consoleshop.constant.Message;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {

    @Test
    public void getUrlByHttpRequest() throws Exception {
        final String request = "GET /shop/test HTTP/1.1";
        final String expected = "/shop/test";
        final String actual = RequestParser.getUrl(request);

        assertEquals(expected, actual);
    }

    @Test
    public void getUrlByNotHttpRequest() {
        final String request = "Give me test";
        final String expected = Message.ERROR_ILLEGAL_URL;
        final String actual = RequestParser.getUrl(request);

        assertEquals(expected, actual);
    }

}