package com.prestanet.mapper;

import com.prestanet.dto.PrestamoDTO;
import com.prestanet.model.entity.Prestamo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PrestamoMapper {
    private final ModelMapper modelMapper;

    public PrestamoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PrestamoDTO toDTO(Prestamo prestamo) {
            return modelMapper.map(prestamo, PrestamoDTO.class);
    }

    public Prestamo toEntity(PrestamoDTO prestamoDTO) {
        return modelMapper.map(prestamoDTO, Prestamo.class);
    }
}
