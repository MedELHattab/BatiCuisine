# Bati-Cuisine

Bati-Cuisine is a Java-based kitchen renovation project management application. It helps manage clients, track project components (materials and labor), calculate costs, and generate project quotes. The app provides an efficient way to manage projects from start to finish using PostgreSQL as the database.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Client Management**: Add, search, and manage clients.
- **Project Creation**: Create new kitchen renovation projects and manage existing ones.
- **Component Handling**: Add materials and labor to each project with detailed cost calculation.
- **Cost Calculation**: Automatically calculates total project costs including discounts for professional clients.
- **Quote Generation**: Generate, save, and manage project quotes (Devis).
- **PostgreSQL**: All data is stored in a PostgreSQL database for reliability and scalability.

## Technologies Used

- **Java** (Core Java, JDBC)
- **PostgreSQL** (Database)
- **Maven** (Build tool)
- **IntelliJ IDEA** (IDE)
- **Docker** (For PostgreSQL container)
- **Design Patterns**: Singleton, Repository, Service

## Project Structure

The project follows a layered architecture and uses various design patterns:

