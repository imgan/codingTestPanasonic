package com.codingTest.codingTest.domain;

import jdk.jfr.DataAmount;

import javax.persistence.*;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name="payment")
public class Payment extends BaseDomain {
    private Integer id;
    private String creditCardNumber;
    private String cardHolder;
    private Date expirationDate;
    private String securityCode;
    private Double amount;
    private String type;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "process_payment_generator")
    @SequenceGenerator(name="process_payment_generator", sequenceName = "process_payment_id_seq", allocationSize=1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="credit_card_number", length=20)
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Column(name="card_holder",length = 50)
    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    @Column(name="expiration_date")
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }


    @Column(name="security_code", length = 3)
    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Column(name="amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(name = "type_payment")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getCreatedBy() {
        return super.getCreatedBy();
    }

    @Override
    public void setCreatedBy(String createdBy) {
        super.setCreatedBy(createdBy);
    }

    @Override
    public Date getCreatedAt(){
        return super.getCreatedAt();
    }

    @Override
    public void setCreatedAt(Date createdAt) {
        super.setCreatedAt(createdAt);
    }
}
