-- Create the "users" table
CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

-- Insert initial data into the "users" table, checking for conflicts
INSERT INTO users (id, username, password, role) VALUES
('bb37892c-4abb-4c76-90af-8df2f7c59088', 'john.doe', 'şifre1', 'NORMAL_USER') ON CONFLICT (id) DO NOTHING;
INSERT INTO users (id, username, password, role) VALUES
('a4339688-c487-4459-bbb8-f68f76cd76c4', 'jane.smith', 'şifre2', 'ADMIN') ON CONFLICT (id) DO NOTHING;
INSERT INTO users (id, username, password, role) VALUES
('74356293-b4ce-4820-8f1e-88fb88e803c2', 'alice.johnson', 'şifre3', 'NORMAL_USER') ON CONFLICT (id) DO NOTHING;
INSERT INTO users (id, username, password, role) VALUES
('cde0bfdc-26ba-4318-91fd-44f84d8d092d', 'bob.wilson', 'şifre4', 'NORMAL_USER') ON CONFLICT (id) DO NOTHING;

-- Create the "products" table
CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    price DOUBLE PRECISION NOT NULL,
    category VARCHAR(255) NOT NULL
);

-- Insert initial data into the "products" table, checking for conflicts
INSERT INTO products (id, name, description, price, category) VALUES
('6bec9eee-b318-429f-b100-b3f2a04a0361', 'Laptop', 'Powerful laptop with Intel Core i7 processor', 1099.99, 'ELECTRONICS') ON CONFLICT (id) DO NOTHING;
INSERT INTO products (id, name, description, price, category) VALUES
('f13b1b53-f65c-4f92-9b47-d55c533c8e76', 'T-shirt', 'Comfortable cotton T-shirt in various colors', 12.99, 'CLOTHING') ON CONFLICT (id) DO NOTHING;
INSERT INTO products (id, name, description, price, category) VALUES
('fa8b5f08-25e5-4a3f-aa86-c79fa2d81373', 'Book', 'Bestselling novel by a famous author', 19.99, 'BOOKS') ON CONFLICT (id) DO NOTHING;
INSERT INTO products (id, name, description, price, category) VALUES
('6a29a9fa-10d5-41b3-8d8b-b414eb1d1952', 'Smartphone', 'High-end smartphone with great camera', 699.99, 'ELECTRONICS') ON CONFLICT (id) DO NOTHING;
INSERT INTO products (id, name, description, price, category) VALUES
('89787642-8f68-48d8-976e-db9c59080e05', 'Jeans', 'Stylish denim jeans for all occasions', 29.99, 'CLOTHING') ON CONFLICT (id) DO NOTHING;

-- Create the "stock" table
CREATE TABLE IF NOT EXISTS stock (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

-- Insert initial data into the "stock" table, checking for conflicts
INSERT INTO stock (id, user_id, product_id, quantity) VALUES
('50e842a5-20d1-4035-9868-e077ed7469f6', (SELECT id FROM users WHERE username = 'john.doe' LIMIT 1), (SELECT id FROM products WHERE name = 'Laptop' LIMIT 1), 5) ON CONFLICT (id) DO NOTHING;
INSERT INTO stock (id, user_id, product_id, quantity) VALUES
('eef32010-50cd-462e-ae98-db7e60cfbb5a', (SELECT id FROM users WHERE username = 'john.doe' LIMIT 1), (SELECT id FROM products WHERE name = 'T-shirt' LIMIT 1), 10) ON CONFLICT (id) DO NOTHING;
INSERT INTO stock (id, user_id, product_id, quantity) VALUES
('5120ecda-88f7-4ce5-9d04-9296dafbd82f', (SELECT id FROM users WHERE username = 'jane.smith' LIMIT 1), (SELECT id from products where name = 'Laptop' LIMIT 1), 8) ON CONFLICT (id) DO NOTHING;
INSERT INTO stock (id, user_id, product_id, quantity) VALUES
('b4837a90-3f52-4389-b3d5-0bc73ce95fc6', (SELECT id FROM users WHERE username = 'jane.smith' LIMIT 1), (SELECT id FROM products WHERE name = 'Book' LIMIT 1), 15) ON CONFLICT (id) DO NOTHING;
INSERT INTO stock (id, user_id, product_id, quantity) VALUES
('15eef2c5-1439-4ec2-96ba-7b32a8ae202f', (SELECT id FROM users WHERE username = 'alice.johnson' LIMIT 1), (SELECT id from products where name = 'Smartphone' LIMIT 1), 3) ON CONFLICT (id) DO NOTHING;
INSERT INTO stock (id, user_id, product_id, quantity) VALUES
('2936557a-2612-4678-a0b5-9289dabce4e7', (SELECT id FROM users WHERE username = 'alice.johnson' LIMIT 1), (SELECT id FROM products WHERE name = 'Jeans' LIMIT 1), 12) ON CONFLICT (id) DO NOTHING;
