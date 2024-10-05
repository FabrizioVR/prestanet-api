package com.prestanet.service;

import com.prestanet.dto.PrestamoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface PrestamoService {
    PrestamoDTO registrarSolicitud(PrestamoDTO prestamoDTO, String dniCliente);
}
