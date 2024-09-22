


CREATE TABLE Clients (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        address VARCHAR(255),
                        phoneNumber VARCHAR(15),
                        isProfessional BOOLEAN DEFAULT FALSE
);

CREATE TYPE ProjectStatus AS ENUM ('Ongoing', 'Completed', 'Canceled');

CREATE TABLE Projects (
                         id SERIAL PRIMARY KEY,
                         projectName VARCHAR(255) NOT NULL,
                         surfaceArea NUMERIC(5, 2) DEFAULT NULL,
                         profitMargin NUMERIC(5, 2) DEFAULT NULL,
                         totalCost NUMERIC(10, 2) DEFAULT 0.00,
                         projectStatus ProjectStatus DEFAULT 'Ongoing' NOT NULL,
                         clientID INT REFERENCES Clients(id)
);

CREATE TABLE Components (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           componentType VARCHAR(255) NOT NULL,
                           tvaRate NUMERIC(5, 2) DEFAULT NULL,
                           projectID INT REFERENCES Projects(id)
);

CREATE TABLE Materials (
                          quantity NUMERIC(10, 2) NOT NULL,
                          unitCost NUMERIC(10, 2) NOT NULL,
                          transportCost NUMERIC(10, 2) NOT NULL,
                          qualityCoefficient NUMERIC(5, 2) NOT NULL
) INHERITS (Components);

CREATE TABLE Labors (
                       laborType VARCHAR(100) NOT NULL,
                       hourlyRate NUMERIC(10, 2) NOT NULL,
                       WorkHours NUMERIC(5, 2) NOT NULL,
                       WorkerProductivity NUMERIC(5, 2) NOT NULL
) INHERITS (Components);


CREATE TABLE Devis (
                       id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                       estimatedAmount NUMERIC(10, 2) NOT NULL,
                       issueDate DATE NOT NULL,
                       validityDate DATE NOT NULL,
                       accepted BOOLEAN DEFAULT FALSE,
                       projectID INT REFERENCES Projects(id)
);