package com.codingTest.codingTest.controller;

import com.codingTest.codingTest.domain.Payment;
import com.codingTest.codingTest.repositories.PaymentRepository;
import com.codingTest.codingTest.services.PaymentGatewayService;
import com.codingTest.codingTest.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;
import com.codingTest.codingTest.util.ConstantUtil;
import com.codingTest.codingTest.util.ValidateUtil;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController extends BaseController {

    @Autowired
    PaymentGatewayService paymentGatewayService;
    @Autowired
    PaymentRepository paymentRepository;

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ResponseEntity<Map> processpayment(HttpServletRequest request, @RequestBody Map map) {
        loggerHttp(request, "Request", map);
        Integer amount = 0;
        Map resultMap;
        if(map.get("amount") != null){
            amount = (int)(map.get("amount"));
        }
        try {
            resultMap = ValidateUtil.validateAPI("process_payment.json", map);
            if(resultMap == null){
                if (amount <= ConstantUtil.CHEAP_PAYMENT_MAX) {
                    resultMap = paymentGatewayService.cheapPaymentGateway(map);
                } else if (amount >= ConstantUtil.EXPENSIVE_PAYMENT_MIN && amount <= ConstantUtil.EXPENSIVE_PAYMENT_MAX){
                    resultMap = paymentGatewayService.expensivePaymentGateway(map);
                } else {
                    resultMap = paymentGatewayService.premiumPaymentGateway(map);
                }
            }
        } catch (Exception e) {
            logger.error("[FATAL]" ,e);
            resultMap = errorResponse(ConstantUtil.STATUS_ERROR, "payment process", null);
        }

        loggerHttp(request, ConstantUtil.RESPONSE, resultMap);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @RequestMapping(value = "/update_payment", method = RequestMethod.POST)
    public ResponseEntity<Map> updatePayment(HttpServletRequest request, @RequestBody Map map) {
        loggerHttp(request, "Request", map);
        Map resultMap;
        Integer amount = (int)(map.get("amount"));

        try {
            resultMap = ValidateUtil.validateAPI("update_payment.json", map);
            if (amount <= ConstantUtil.CHEAP_PAYMENT_MAX) {
                resultMap = paymentGatewayService.updateCheapPaymentGateway(map);
            } else if (amount >= ConstantUtil.EXPENSIVE_PAYMENT_MIN && amount <= ConstantUtil.EXPENSIVE_PAYMENT_MAX){
                resultMap = paymentGatewayService.updateExpensivePaymentGateway(map);
            } else {
                resultMap = paymentGatewayService.updatePremiumPaymentGateway(map);
            }
        } catch (Exception e) {
            logger.error("[FATAL]" ,e);
            resultMap = errorResponse(ConstantUtil.STATUS_ERROR, "payment process", null);
        }

        loggerHttp(request, ConstantUtil.RESPONSE, resultMap);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
