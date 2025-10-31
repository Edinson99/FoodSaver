-- Insertar usuarios de ejemplo
INSERT INTO users (username, password, email, user_type, location, created_at) VALUES
('vendedor1', '$2a$10$EXAMPLE_HASH', 'vendedor1@example.com', 'SELLER', 'Ciudad de México', NOW()),
('comprador1', '$2a$10$EXAMPLE_HASH', 'comprador1@example.com', 'BUYER', 'Guadalajara', NOW());

-- Insertar productos de ejemplo
INSERT INTO products (name, description, category, price, quantity, expiry_date, location, product_status, user_id, created_at) VALUES
('Tomates Frescos', 'Tomates rojos frescos de temporada', 'VERDURAS', 25.50, 10, '2025-01-15', 'Calle 123, Ciudad', 'AVAILABLE', 1, NOW()),
('Pan Integral', 'Pan integral recién horneado', 'PANADERIA', 35.00, 5, '2025-01-10', 'Panadería Central', 'AVAILABLE', 1, NOW());

-- Insertar notificaciones de ejemplo
INSERT INTO notifications (title, message, notification_type, user_id, is_read, created_at) VALUES
('Nuevo producto disponible', 'Hay tomates frescos disponibles en tu área', 'NEW_PRODUCT', 2, false, NOW()),
('Producto vendido', 'Tu producto de pan integral ha sido vendido', 'PRODUCT_SOLD', 1, false, NOW());