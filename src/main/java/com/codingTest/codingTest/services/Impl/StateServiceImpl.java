package com.codingTest.codingTest.services.Impl;

import com.codingTest.codingTest.domain.State;
import com.codingTest.codingTest.repositories.StateRepository;
import com.codingTest.codingTest.services.PaymentGatewayService;
import com.codingTest.codingTest.services.StateService;
import com.codingTest.codingTest.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StateServiceImpl extends BaseService implements StateService {
    @Autowired
    StateRepository stateRepository;

    @Override
    public Map saveState(Map map) throws Exception {
        Map result = new HashMap();
        String stateName = (String) map.get("state_name");
        State state = new State();
        try {
            state.setStateName(stateName);
            state.setCreatedAt(new Date());
            state.setCreatedBy(ConstantUtil.SYSTEM);
            stateRepository.save(state);
            logger.info("save state success");
            result.put(ConstantUtil.STATUS, ConstantUtil.SUCCESS);
            result.put(ConstantUtil.DATA, ConstantUtil.STATUS_SUCCESS);
        } catch (Exception e){
            logger.error("Save state failed" +e);
            result.put(ConstantUtil.STATUS, ConstantUtil.STATUS_ERROR);
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.STATUS_ERROR, "Save Payment", null));
            return result;
        }
        Map data = new HashMap();
        data.put("state_name", stateName);
        result.put(ConstantUtil.DATA, data);
        logger.info("result : " + result);
        return result;
    }

    @Override
    public Map updateState(Map map) throws Exception {
        Map result = new HashMap();
        String stateName = "";
        Integer stateId = null;
        if(map.get("state_name") != null ){
             stateName = (String) map.get("state_name");
        }
        if(map.get("state_id") != null ){
             stateId = (Integer) map.get("state_id");
        }

        State state = stateRepository.findById(stateId).orElse(null);
        if(state == null){
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.NOT_FOUND, "State not found, try another", null));
            return result;
        }
        try {
            state.setStateName(stateName);
            state.setCreatedAt(new Date());
            state.setCreatedBy(ConstantUtil.SYSTEM);
            stateRepository.save(state);
            logger.info("update state success");
            result.put(ConstantUtil.STATUS, ConstantUtil.SUCCESS);
            result.put(ConstantUtil.DATA, ConstantUtil.STATUS_SUCCESS);
        } catch (Exception e){
            logger.error("update state failed" +e);
            result.put(ConstantUtil.STATUS, ConstantUtil.STATUS_ERROR);
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.STATUS_ERROR, "Save State", null));
            return result;
        }
        Map data = new HashMap();
        data.put("state_name", stateName);
        result.put(ConstantUtil.DATA, data);
        logger.info("result : " + result);
        return result;
    }
}
