package com.prestanet.mapper;

import com.prestanet.dto.CronogramaPagoDTO;
import com.prestanet.dto.PrestamoDTO;
import com.prestanet.model.entity.CronogramaPago;
import com.prestanet.model.entity.Prestamo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CronogramaPagoMapper {
    private final ModelMapper modelMapper;

    public CronogramaPagoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CronogramaPagoDTO toDTO(CronogramaPago cronogramaPago) {
        return modelMapper.map(cronogramaPago, CronogramaPagoDTO.class);
    }

    public CronogramaPago toEntity(CronogramaPagoDTO cronogramaPago) {
        return modelMapper.map(cronogramaPago, CronogramaPago.class);
    }
}
