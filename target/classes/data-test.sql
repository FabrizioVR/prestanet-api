-- Crear un usuario
INSERT INTO usuario (nombre_usuario, contraseña)
VALUES ('admin', 'password123');

-- Crear dos clientes
INSERT INTO cliente (nombre_cliente, dni)
VALUES
    ('Cliente Uno', '12345678'),  -- Primer cliente
    ('Cliente Dos', '87654321');   -- Segundo cliente

-- Crear préstamos para los clientes
-- Obtener el idCliente del primer cliente
INSERT INTO prestamo (monto, tipo_prestamo, interes, fecha_solicitud, id_usuario, id_cliente)
VALUES
    (1000.00, 'UN_MES', 5.0, '2024-09-05', 1, 1),  -- Préstamo del primer cliente por 1 mes
    (5000.00, 'SEIS_MESES', 10.0, '2024-09-05',1 ,2);  -- Préstamo del segundo cliente por 6 meses
