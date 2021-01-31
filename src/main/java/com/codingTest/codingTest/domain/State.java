package com.codingTest.codingTest.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="state")
public class State extends BaseDomain{
    private Integer id;
    private String stateName;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_generator")
    @SequenceGenerator(name="state_generator", sequenceName = "state_id_seq", allocationSize=1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "state_name", length = 50)
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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
