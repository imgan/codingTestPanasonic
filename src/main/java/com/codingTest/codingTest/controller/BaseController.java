package com.codingTest.codingTest.controller;

import com.codingTest.codingTest.util.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {
    protected org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    protected String getIpAddress(HttpServletRequest request) {
        return request.getHeader("X-FORWARDED-FOR") == null ? request.getRemoteAddr() : request.getHeader("X-FORWARDED-FOR");
    }

    public boolean isExistingData(Object data) {
        return !(data == null || data.toString().isEmpty());
    }

    protected boolean isExistingDataAndStringValue(Object data) {
        if (isExistingData(data)) {
            return (data instanceof String);
        }
        return false;
    }

    protected boolean isExistingDataAndMapValue(Object data) {
        if (isExistingData(data)) {
            if (data instanceof Map) {
                return !((Map) data).isEmpty();
            }
        }
        return false;
    }

    protected boolean isExistingDataAndListValue(Object data) {
        if (isExistingData(data)) {
            if (data instanceof List) {
                return !((List) data).isEmpty();
            }
        }
        return false;
    }

    protected boolean isExistingDataAndIntegerValue(Object data) {
        if (isExistingData(data)) {
            return (data instanceof Integer);
        }
        return false;
    }

    protected boolean isExistingDataAndDateValue(Object data) {
        if (isExistingData(data)) {
            return (DateTimeUtil.convertStringToDateCustomized(data.toString(), DateTimeUtil.DATE_FORMAT) != null);
        }
        return false;
    }

    protected boolean isExistingDataAndDoubleValue(Object data) {
        if (isExistingData(data)) {
            try {
                Double.valueOf(data.toString());
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    protected Map errorResponse(int code, String field, Object data) {
        String info;
        switch (code) {
            case 200:
                info = "Payment is processed : " + field;
                break;
            case 411:
                info = "incomplete data : " + field;
                break;
            case 404:
                info = "Data not found: " + field;
                break;
            case 400:
                info = "The request is invalid : " + field;
                break;
            case 503:
                info = "Service Unavailable : Premium Payment Service Required ";
                break;
            default:
                info = "Any error : " + field;
                break;
        }

        Map resultMap = new HashMap();
        resultMap.put("code", code);
        resultMap.put("info", info);

        if (data != null) {
            if(data instanceof String) {
                Map map = new HashMap();
                map.put("description", data);
                data = map;
            }
        }

        resultMap.put("data", data);
        return resultMap;
    }

    protected void loggerHttp(HttpServletRequest request, String type, Map map) {
        String uri = request.getRequestURI();
        if (request.getQueryString() != null) uri += "?" + request.getQueryString();
        String json = map.toString();
        try {
            ObjectMapper om = new ObjectMapper();
            json = om.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        } catch (JsonProcessingException e) {
            logger.error("[FATAL]", e);
        }
        logger.info(type + " " + uri + " : " + json);
    }

}
