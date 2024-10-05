package com.prestanet.mapper;

import com.prestanet.dto.ClienteDTO;
import com.prestanet.dto.PrestamoDTO;
import com.prestanet.model.entity.Cliente;
import com.prestanet.model.entity.Prestamo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    private final ModelMapper modelMapper;

    public ClienteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ClienteDTO toDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {
        return modelMapper.map(clienteDTO, Cliente.class);
    }
}
