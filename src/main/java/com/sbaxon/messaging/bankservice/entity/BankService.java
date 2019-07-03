package com.sbaxon.messaging.bankservice.entity;

import com.sbaxon.domain.bankservice.aggregate.BankServiceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class BankService {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;

    private String name;

    @Enumerated(EnumType.STRING)
    private BankServiceType type;

}
