package com.codingTest.codingTest.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface StateService {
    public Map saveState(Map map) throws Exception;
    public Map updateState(Map map) throws Exception;
}
