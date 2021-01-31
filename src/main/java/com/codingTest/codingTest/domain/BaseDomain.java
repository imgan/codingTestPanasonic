package com.codingTest.codingTest.domain;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

public abstract class BaseDomain {
    private Date createdAt;
    private Date updatedAt;
    private String createBy;

    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Column(name = "created_by")
    public String getCreatedBy() {
        return createBy;
    }

    public void setCreatedBy(String createBy) {
        this.createBy = createBy;
    }

}
