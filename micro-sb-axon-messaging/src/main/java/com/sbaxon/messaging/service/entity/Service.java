package com.sbaxon.messaging.service.entity;

import com.sbaxon.business.service.aggregate.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;

    private String name;

    @Enumerated(EnumType.STRING)
    private ServiceType type;

}
