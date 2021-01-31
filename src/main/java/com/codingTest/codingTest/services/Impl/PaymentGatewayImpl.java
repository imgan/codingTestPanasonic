package com.codingTest.codingTest.services.Impl;

import com.codingTest.codingTest.domain.Payment;
import com.codingTest.codingTest.repositories.PaymentRepository;
import com.codingTest.codingTest.services.PaymentGatewayService;
import com.codingTest.codingTest.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentGatewayImpl extends BaseService implements PaymentGatewayService {
    @Autowired
    PaymentRepository paymentRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map cheapPaymentGateway(Map map) throws Exception {
        Map result = new HashMap();
        Map data = new HashMap();
        String securityCode = null;
        String creditCardNumber = (String) map.get("credit_card_number");
        String cardHolder = (String) map.get("card_holder");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantUtil.DATE_FORMAT);
        Date expirationDate = simpleDateFormat.parse((String) map.get("expiration_date"));
        Double amount = Double.valueOf((Integer) map.get("amount"));
        Payment payment =  paymentRepository.findByCreditCardNumber(creditCardNumber);
        if(payment != null){
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "Duplicate ! ,try another credit card number", null));
            return result;
        }
        if(amount < 0){
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "amount less than 0", null));
            return result;
        }
        if(map.get("security_code") != null){
            securityCode = (String) map.get(("security_code"));
        }

        if (!isExistingDataAndStringValue(creditCardNumber)) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "credit_card_number", null));
            return result;
        }

        if (!isExistingDataAndStringValue(cardHolder)) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "card_holder", null));
            return result;
        }

        if (!isExistingDataAndDateValue(map.get("expiration_date"))) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "expiration_date", null));
            return result;
        }

        if (!isExistingDataAndDoubleValue(map.get("amount"))) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "amount", null));
            return result;
        }


        Payment processPayment = new Payment();
        try {
            processPayment.setCreditCardNumber((creditCardNumber));
            processPayment.setCardHolder(cardHolder);
            processPayment.setAmount(amount);
            processPayment.setExpirationDate(expirationDate);
            processPayment.setSecurityCode(securityCode);
            processPayment.setType(ConstantUtil.CHEAP);
            processPayment.setCreatedAt(new Date());
            processPayment.setCreatedBy(ConstantUtil.SYSTEM);
            paymentRepository.save(processPayment);
            result.put(ConstantUtil.STATUS, ConstantUtil.SUCCESS);
            result.put(ConstantUtil.DATA, ConstantUtil.STATUS_SUCCESS);
            logger.info("Save Payment Success");
        } catch (Exception e){
            logger.error("Save Payment failed" +e);
            result.put(ConstantUtil.STATUS, ConstantUtil.STATUS_ERROR);
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.STATUS_ERROR, "Save Payment", null));
            return result;
        }

        data.put("credit_card_number", creditCardNumber);
        data.put("card_holder",cardHolder);
        data.put("amount", amount);
        data.put("expiration_date",expirationDate);
        result.put(ConstantUtil.DATA, data);
        logger.info("result : " + result);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map expensivePaymentGateway(Map map)throws Exception{
        Map result = new HashMap();
        Map data = new HashMap();
        String securityCode = null;
        String creditCardNumber = (String) map.get("credit_card_number");
        String cardHolder = (String) map.get("card_holder");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantUtil.DATE_FORMAT);
        Date expirationDate = simpleDateFormat.parse((String) map.get("expiration_date"));
        Double amount = Double.valueOf((Integer) map.get("amount"));
        Payment payment =  paymentRepository.findByCreditCardNumber(creditCardNumber);
        if(payment != null){
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "Duplicate ! ,try another credit card number", null));
            return result;
        }
        if(amount < 0){
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "amount less than 0", null));
            return result;
        }

        if(map.get("security_code") != null){
            securityCode = (String) map.get(("security_code"));
        }

        if (!isExistingDataAndStringValue(creditCardNumber)) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "credit_card_number", null));
            return result;
        }

        if (!isExistingDataAndStringValue(cardHolder)) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "card_holder", null));
            return result;
        }

        if (!isExistingDataAndDateValue(map.get("expiration_date"))) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "expiration_date", null));
            return result;
        }

        if (!isExistingDataAndDoubleValue(map.get("amount"))) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "amount", null));
            return result;
        }

        Payment processPayment = new Payment();
        try {
            processPayment.setCreditCardNumber((creditCardNumber));
            processPayment.setCardHolder(cardHolder);
            processPayment.setAmount(amount);
            processPayment.setExpirationDate(expirationDate);
            processPayment.setSecurityCode(securityCode);
            processPayment.setType(ConstantUtil.EXPENSIVE);
            processPayment.setCreatedAt(new Date());
            processPayment.setCreatedBy(ConstantUtil.SYSTEM);
            paymentRepository.save(processPayment);
            result.put(ConstantUtil.STATUS, ConstantUtil.SUCCESS);
            result.put(ConstantUtil.DATA, ConstantUtil.STATUS_SUCCESS);
            logger.info("Save Payment Success");
        } catch (Exception e){
            logger.error("Save Payment failed" +e);
            result.put(ConstantUtil.STATUS, ConstantUtil.STATUS_ERROR);
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.STATUS_ERROR, "Save Payment", null));
            return result;
        }

        data.put("credit_card_number", creditCardNumber);
        data.put("card_holder",cardHolder);
        data.put("amount", amount);
        data.put("expiration_date",expirationDate);
        result.put(ConstantUtil.DATA, data);
        logger.info("result : " + result);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Map premiumPaymentGateway(Map map)throws Exception{
        Map result = new HashMap();
        result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.SERVICE_UNAVAILABLE, "Premium Payment Service", null));
        logger.info("result : " + result);
        return result;
    }

    @Override
    public Map updateCheapPaymentGateway(Map map) throws Exception {
        Map result = new HashMap();
        Map data = new HashMap();
        String securityCode = null;
        String creditCardNumber = (String) map.get("credit_card_number");
        String cardHolder = (String) map.get("card_holder");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantUtil.DATE_FORMAT);
        Date expirationDate = simpleDateFormat.parse((String) map.get("expiration_date"));
        Double amount = Double.valueOf((Integer) map.get("amount"));
        Payment payment = paymentRepository.findByCreditCardNumber(creditCardNumber);
        if(payment == null ){
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.NOT_FOUND, "Credit Card Not found", null));
            return result;
        }
        if(amount < 0){
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "amount less than 0", null));
            return result;
        }
        if(map.get("security_code") != null){
            securityCode = (String) map.get(("security_code"));
        }

        if (!isExistingDataAndStringValue(creditCardNumber)) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "credit_card_number", null));
            return result;
        }

        if (!isExistingDataAndStringValue(cardHolder)) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "card_holder", null));
            return result;
        }

        if (!isExistingDataAndDateValue(map.get("expiration_date"))) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "expiration_date", null));
            return result;
        }

        if (!isExistingDataAndDoubleValue(map.get("amount"))) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "amount", null));
            return result;
        }

        try {
            payment.setUpdatedAt(new Date());
            payment.setSecurityCode(securityCode);
            payment.setExpirationDate(expirationDate);
            payment.setAmount(amount);
            payment.setCardHolder(cardHolder);
            paymentRepository.save(payment);
            logger.info("Update Payment Success");
            result.put(ConstantUtil.STATUS, ConstantUtil.SUCCESS);
            result.put(ConstantUtil.DATA, ConstantUtil.STATUS_SUCCESS);
        } catch (Exception e){
            logger.error("Update Payment failed" +e);
            result.put(ConstantUtil.STATUS, ConstantUtil.STATUS_ERROR);
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.STATUS_ERROR, "Save Payment", null));
            return result;
        }

        data.put("credit_card_number", creditCardNumber);
        data.put("card_holder",cardHolder);
        data.put("amount", amount);
        data.put("expiration_date",expirationDate);
        result.put(ConstantUtil.DATA, data);
        logger.info("result : " + result);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Map updateExpensivePaymentGateway(Map map) throws Exception {
        Map result = new HashMap();
        Map data = new HashMap();
        String securityCode = null;
        String creditCardNumber = (String) map.get("credit_card_number");
        String cardHolder = (String) map.get("card_holder");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstantUtil.DATE_FORMAT);
        Date expirationDate = simpleDateFormat.parse((String) map.get("expiration_date"));
        Double amount = Double.valueOf((Integer) map.get("amount"));
        Payment payment = paymentRepository.findByCreditCardNumber(creditCardNumber);
        if(payment == null ){
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.NOT_FOUND, "Credit Card Not found", null));
            return result;
        }
        if(amount < 0){
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "amount less than 0", null));
            return result;
        }
        if(map.get("security_code") != null){
            securityCode = (String) map.get(("security_code"));
        }

        if (!isExistingDataAndStringValue(creditCardNumber)) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "credit_card_number", null));
            return result;
        }

        if (!isExistingDataAndStringValue(cardHolder)) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "card_holder", null));
            return result;
        }

        if (!isExistingDataAndDateValue(map.get("expiration_date"))) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "expiration_date", null));
            return result;
        }

        if (!isExistingDataAndDoubleValue(map.get("amount"))) {
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.BAD_REQUEST, "amount", null));
            return result;
        }

        try {
            payment.setUpdatedAt(new Date());
            payment.setSecurityCode(securityCode);
            payment.setExpirationDate(expirationDate);
            payment.setAmount(amount);
            payment.setCardHolder(cardHolder);
            paymentRepository.save(payment);
            logger.info("Update Payment Success");
            result.put(ConstantUtil.STATUS, ConstantUtil.SUCCESS);
            result.put(ConstantUtil.DATA, ConstantUtil.STATUS_SUCCESS);
        } catch (Exception e){
            logger.error("Update Payment failed" +e);
            result.put(ConstantUtil.STATUS, ConstantUtil.STATUS_ERROR);
            result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.STATUS_ERROR, "Save Payment", null));
            return result;
        }

        data.put("credit_card_number", creditCardNumber);
        data.put("card_holder",cardHolder);
        data.put("amount", amount);
        data.put("expiration_date",expirationDate);
        result.put(ConstantUtil.DATA, data);
        logger.info("result : " + result);
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Map updatePremiumPaymentGateway(Map map) throws Exception {
        Map result = new HashMap();
        result.put(ConstantUtil.DATA, errorResponse(ConstantUtil.SERVICE_UNAVAILABLE, "Premium Payment Service", null));
        logger.info("result : " + result);
        return result;
    }

}
