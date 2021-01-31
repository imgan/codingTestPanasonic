package com.codingTest.codingTest.controller;

import com.codingTest.codingTest.repositories.StateRepository;
import com.codingTest.codingTest.services.StateService;
import com.codingTest.codingTest.util.ConstantUtil;
import com.codingTest.codingTest.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/state")
public class StateController extends BaseController{
    @Autowired
    StateService stateService;

    @RequestMapping(value = "/add_state", method = RequestMethod.POST)
    public ResponseEntity<Map> addState(HttpServletRequest request, @RequestBody Map map) {
        loggerHttp(request, "Request", map);
        Map resultMap;
        try {
            resultMap = ValidateUtil.validateAPI("state/save_state.json", map);
            if(resultMap == null){
                resultMap = stateService.saveState(map);
            }
        } catch (Exception e) {
            logger.error("[FATAL]" ,e);
            resultMap = errorResponse(ConstantUtil.STATUS_ERROR, "save state", null);
        }

        loggerHttp(request, ConstantUtil.RESPONSE, resultMap);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/update_state", method = RequestMethod.POST)
    public ResponseEntity<Map> updateState(HttpServletRequest request, @RequestBody Map map) {
        loggerHttp(request, "Request", map);
        Map resultMap;
        try {
            resultMap = ValidateUtil.validateAPI("state/update_state.json", map);
            if(resultMap == null){
                resultMap = stateService.updateState(map);
            }
        } catch (Exception e) {
            logger.error("[FATAL]" ,e);
            resultMap = errorResponse(ConstantUtil.STATUS_ERROR, "save state", null);
        }

        loggerHttp(request, ConstantUtil.RESPONSE, resultMap);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
