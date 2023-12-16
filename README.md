# QuanLiCuaHangDienThoai Project

## Overview

The QuanLiCuaHangDiDong project is a simple desktop application built using Java Swing for the graphical user interface (GUI) and MySQL for data storage. The application is designed for managing a mobile phone store, allowing users to perform various operations related to inventory management.

## Features

- **User Authentication:** The application includes a login system to ensure secure access. Users can log in with their credentials.

- **Product Management:** Allows users to add, edit, and delete mobile phone products in the inventory.

- **Customer Management:** Provides functionality for managing customer information, including adding and updating customer details.

- **Order Processing:** Enables users to create and process orders, keeping track of sales and transactions.

- **Database Integration:** Utilizes MySQL for storing and retrieving data, ensuring persistence and data integrity.

## Requirements

- Java Development Kit (JDK) 8 or later
- MySQL Database Server
- MySQL Connector/J (JDBC driver for MySQL)

## Setup

1. **Database Setup:**
   - Run file "Script.sql"
   - Execute the SQL script provided in the `database` folder to set up the necessary tables and data.

2. **Configure Database Connection:**
   - Open the `config.properties` file and update the database connection properties (URL, username, password).

3. **Build and Run:**
   - Compile and run file "MainForm.java" int the application using a Java IDE or command-line tools.
   - Ensure that the required libraries, including the MySQL Connector/J, are included in the classpath.

## Usage

1. **Login:**
   - Launch the application and log in using valid credentials.

2. **Navigate:**
   - Explore different sections of the application using the navigation menu.

3. **Perform Operations:**
   - Add, edit, or delete products and customer information.
   - Process orders and manage inventory.

4. **Logout:**
   - Safely log out of the application to protect user access.

## Contributors

- [Duy Tran]

## Acknowledgments

- Acknowledge any external libraries, frameworks, or resources used in the project.

