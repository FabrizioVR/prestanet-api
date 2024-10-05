package com.prestanet.service;

import com.prestanet.dto.PrestamoDTO;
import com.prestanet.model.entity.Prestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrestamoService {
    PrestamoDTO registrarSolicitud(PrestamoDTO prestamoDTO, String dniCliente);
}
