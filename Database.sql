-- Database: bati_cuisine




-- 1. Table: Clients
CREATE TABLE clients (
                         client_id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         address TEXT,
                         phone VARCHAR(20),
                         is_professional BOOLEAN DEFAULT FALSE -- True for professionals
);

-- 2. Create ENUM type for project_status
CREATE TYPE project_status_enum AS ENUM ('En cours', 'Terminé', 'Annulé');

-- 3. Table: Projects
CREATE TABLE projects (
                          project_id SERIAL PRIMARY KEY,
                          client_id INT REFERENCES clients(client_id) ON DELETE CASCADE,
                          project_name VARCHAR(255) NOT NULL,
                          margin_percentage NUMERIC(5, 2) NOT NULL, -- Beneficiary margin percentage
                          total_cost NUMERIC(10, 2) DEFAULT 0.0, -- Calculated cost of the project
                          project_status project_status_enum DEFAULT 'En cours', -- Project status (ENUM)
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Table: Materials
CREATE TABLE materials (
                           material_id SERIAL PRIMARY KEY,
                           project_id INT REFERENCES projects(project_id) ON DELETE CASCADE,
                           name VARCHAR(255) NOT NULL, -- Material name
                           unit_cost NUMERIC(10, 2) NOT NULL, -- Unit cost (e.g., per square meter)
                           quantity NUMERIC(10, 2) NOT NULL, -- Quantity used
                           component_type VARCHAR(50) NOT NULL, -- Type (Material or Labor)
                           vat_rate NUMERIC(5, 2) NOT NULL, -- VAT percentage
                           transport_cost NUMERIC(10, 2), -- Transport cost
                           quality_factor NUMERIC(3, 2) DEFAULT 1.0 -- Quality factor (1.0 = standard)
);

-- 5. Table: Labor
CREATE TABLE labor (
                       labor_id SERIAL PRIMARY KEY,
                       project_id INT REFERENCES projects(project_id) ON DELETE CASCADE,
                       labor_type VARCHAR(255) NOT NULL, -- Type of labor (e.g., Ouvrier de base)
                       hourly_rate NUMERIC(10, 2) NOT NULL, -- Hourly rate
                       hours_worked NUMERIC(10, 2) NOT NULL, -- Number of hours worked
                       productivity_factor NUMERIC(3, 2) DEFAULT 1.0, -- Productivity factor
                       vat_rate NUMERIC(5, 2) NOT NULL -- VAT percentage
);

-- 6. Table: Quotes (Devis)
CREATE TABLE quotes (
                        quote_id SERIAL PRIMARY KEY,
                        project_id INT REFERENCES projects(project_id) ON DELETE CASCADE,
                        estimated_amount NUMERIC(10, 2) NOT NULL, -- Estimated cost
                        issue_date DATE NOT NULL, -- Date of issue
                        valid_until DATE NOT NULL, -- Valid until date
                        is_accepted BOOLEAN DEFAULT FALSE -- Accepted by client
);

-- 7. Table: Cost Summary
CREATE TABLE cost_summary (
                              summary_id SERIAL PRIMARY KEY,
                              project_id INT REFERENCES projects(project_id) ON DELETE CASCADE,
                              material_cost NUMERIC(10, 2) DEFAULT 0.0, -- Total material cost
                              labor_cost NUMERIC(10, 2) DEFAULT 0.0, -- Total labor cost
                              vat NUMERIC(10, 2) DEFAULT 0.0, -- Total VAT amount
                              margin_amount NUMERIC(10, 2) DEFAULT 0.0, -- Margin applied
                              total_with_margin NUMERIC(10, 2) DEFAULT 0.0 -- Final cost with margin
);

-- Sample insertions for testing

-- Insert sample clients
INSERT INTO clients (name, address, phone, is_professional)
VALUES ('Mme Dupont', '12 Rue des Fleurs, Paris', '06 12345678', FALSE),
       ('SARL BatiPro', '45 Avenue des Champs, Lyon', '04 56781234', TRUE);

-- Insert sample project
INSERT INTO projects (client_id, project_name, margin_percentage, project_status)
VALUES (1, 'Rénovation Cuisine Mme Dupont', 15.0, 'En cours'),
       (2, 'Installation Cuisine SARL BatiPro', 20.0, 'En cours');

-- Insert sample materials
INSERT INTO materials (project_id, name, unit_cost, quantity, component_type, vat_rate, transport_cost, quality_factor)
VALUES (1, 'Carrelage', 30, 20, 'Matériel', 20, 50, 1.1),
       (1, 'Peinture', 15, 10, 'Matériel', 20, 20, 1.0);

-- Insert sample labor
INSERT INTO labor (project_id, labor_type, hourly_rate, hours_worked, productivity_factor, vat_rate)
VALUES (1, 'Ouvrier de base', 20, 40, 1.0, 20),
       (1, 'Ouvrier spécialisé', 35, 20, 1.1, 20);

-- Insert sample quotes
INSERT INTO quotes (project_id, estimated_amount, issue_date, valid_until, is_accepted)
VALUES (1, 3381.00, '2024-09-10', '2024-10-10', TRUE),
       (2, 4500.00, '2024-09-15', '2024-10-15', FALSE);

-- Insert sample cost summary
INSERT INTO cost_summary (project_id, material_cost, labor_cost, vat, margin_amount, total_with_margin)
VALUES (1, 880.00, 1570.00, 441.00, 441.00, 3381.00);

