package com.zeinab.core.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Data
@Entity
public class Transaction {

    @Id
    private Long id;
    private BigDecimal amount;
    private Long datetime;

    @OneToOne
    private Account from;

    @OneToOne
    private Account to;
}
