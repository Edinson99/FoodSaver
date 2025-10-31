-- Eliminar todo el contenido y dejar el archivo vacío
-- O corregir las columnas para que coincidan con tu modelo:

INSERT INTO users (name, password, email, user_type, location, phone, is_active, created_at, updated_at)
VALUES
('Vendedor Test', '123456', 'vendedor.test@example.com', 'VENDOR', 'Ciudad Test', '1234567890', true, NOW(), NOW()),
('Comprador Test', '123456', 'comprador.test@example.com', 'BUYER', 'Ciudad Test', '0987654321', true, NOW(), NOW());