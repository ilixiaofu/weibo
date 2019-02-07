package com.springcloud.weibo.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

// get请求编码问题
public class EncodingRequest extends HttpServletRequestWrapper {

    private String encoding = "UTF-8";
    private HttpServletRequest request;

    public EncodingRequest(HttpServletRequest request, String encoding) {
        super(request);
        this.request = request;
        if (encoding != null) {
            this.encoding = encoding;
        }
    }

    public String getParameter(String arg0) {
        String value = request.getParameter(arg0);
        String characterEncoding = request.getCharacterEncoding();
        try {
            value = new String(value.getBytes(characterEncoding), encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }
}
