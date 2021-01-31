package com.codingTest.codingTest.services;

import java.util.Map;

public interface PaymentGatewayService {
    public Map cheapPaymentGateway(Map map) throws Exception;
    public Map expensivePaymentGateway(Map map) throws Exception;
    public Map premiumPaymentGateway(Map map) throws Exception;
    public Map updateCheapPaymentGateway(Map map) throws Exception;
    public Map updateExpensivePaymentGateway(Map map) throws Exception;
    public Map updatePremiumPaymentGateway(Map map) throws Exception;
}
