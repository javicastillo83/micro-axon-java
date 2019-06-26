package com.sbaxon.business.service.dto;

import com.sbaxon.business.service.aggregate.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateServiceDTO {

    private String name;

    private ServiceType serviceType;

}
