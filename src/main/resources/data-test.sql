-- Inserción de Usuarios
INSERT INTO usuario (nombre_usuario, contraseña) VALUES
    ('usuario1', 'password1')
    ON CONFLICT (nombre_usuario) DO NOTHING;


-- Inserción de Clientes (solo personas naturales)
INSERT INTO cliente (nombre_cliente, DNI, RUC, tipo_cliente) VALUES
    ('Cliente Uno', '12345678', NULL, 'NATURAL')
    ON CONFLICT (DNI) DO NOTHING,
    ('Cliente Dos', '23456789', NULL, 'NATURAL')
ON CONFLICT (DNI) DO NOTHING; -- Cambié a 'Cliente Tres' para evitar duplicados

-- Inserción de Préstamos
INSERT INTO prestamo (monto, tipo_prestamo, interes, fecha_solicitud, id_usuario, id_cliente) VALUES
    (2000.00, 'UNO_MES', 20.00, '2024-10-01', 1, 1) -- Préstamo a 1 mes para Cliente Uno
    ON CONFLICT (id_usuario, id_cliente, tipo_prestamo, fecha_solicitud) DO NOTHING,
    (2000.00, 'SEIS_MESES', 20.00, '2024-10-01', 1, 2) -- Préstamo a 6 meses para Cliente Dos
ON CONFLICT (id_usuario, id_cliente, tipo_prestamo, fecha_solicitud) DO NOTHING;

-- Inserción de Cronogramas de Pago (6 cronogramas para el préstamo de 6 meses, 1 para el de 1 mes)
INSERT INTO cronograma_pago (fecha_cronograma, monto_por_pagar, estado_pago, id_prestamo) VALUES
    ('2024-10-01', 2000.00, 'EN_PROCESO', 1) -- Cronograma para el préstamo de 1 mes
    ON CONFLICT (fecha_cronograma, id_prestamo) DO NOTHING,
    ('2024-10-01', 500.00, 'EN_PROCESO', 2) -- Primer pago para el préstamo de 6 meses
ON CONFLICT (fecha_cronograma, id_prestamo) DO NOTHING,
    ('2024-11-01', 500.00, 'EN_PROCESO', 2) -- Segundo pago para el préstamo de 6 meses
    ON CONFLICT (fecha_cronograma, id_prestamo) DO NOTHING,
    ('2024-12-01', 500.00, 'EN_PROCESO', 2) -- Tercer pago para el préstamo de 6 meses
    ON CONFLICT (fecha_cronograma, id_prestamo) DO NOTHING,
    ('2025-01-01', 500.00, 'EN_PROCESO', 2) -- Cuarto pago para el préstamo de 6 meses
    ON CONFLICT (fecha_cronograma, id_prestamo) DO NOTHING,
    ('2025-02-01', 500.00, 'EN_PROCESO', 2) -- Quinto pago para el préstamo de 6 meses
    ON CONFLICT (fecha_cronograma, id_prestamo) DO NOTHING,
    ('2025-03-01', 500.00, 'EN_PROCESO', 2) -- Sexto pago para el préstamo de 6 meses
    ON CONFLICT (fecha_cronograma, id_prestamo) DO NOTHING;
