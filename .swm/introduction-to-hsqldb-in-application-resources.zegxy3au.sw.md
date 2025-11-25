---
title: Introduction to Hsqldb in Application Resources
---
# Introduction to Hsqldb

Hsqldb is an embedded relational database integrated within the application to provide a lightweight, fast, and serverless data management solution. It eliminates the need for an external database server, simplifying development and testing environments.

# Purpose of Hsqldb in the Application

The primary role of Hsqldb in the application is to supply a predefined database schema and initial sample data. This setup ensures that the application can start immediately with a consistent and meaningful dataset, which is essential for development, testing, and demonstration.

# Database Schema Definition

The database schema is defined in the <SwmPath>[src/…/h2/schema.sql](src/main/resources/db/h2/schema.sql)</SwmPath> file located in the application resources. This SQL script creates tables representing core domain entities such as vets, specialties, owners, pets, and visits. It also defines indexes and foreign key constraints to enforce data integrity and relationships between these entities.

# Initial Data Population

The <SwmPath>[src/…/h2/data.sql](src/main/resources/db/h2/data.sql)</SwmPath> file complements the schema by inserting initial sample data into the database tables. This includes records for vets, specialties, pet types, owners, pets, and visits. By populating these tables at startup, the application gains immediate access to realistic data, facilitating functional testing and user interface demonstrations.

# How Hsqldb Works in the Application Lifecycle

When the application starts, Hsqldb automatically executes the <SwmPath>[src/…/h2/schema.sql](src/main/resources/db/h2/schema.sql)</SwmPath> script to establish the database structure, followed by the <SwmPath>[src/…/h2/data.sql](src/main/resources/db/h2/data.sql)</SwmPath> script to load the sample data. This process ensures that the embedded database is fully prepared without manual intervention, enabling seamless application initialization.

# Example: Vets Table Setup

For instance, the <SwmPath>[src/…/h2/schema.sql](src/main/resources/db/h2/schema.sql)</SwmPath> script creates a `vets` table with columns for `id`, `first_name`, and `last_name`. Correspondingly, the <SwmPath>[src/…/h2/data.sql](src/main/resources/db/h2/data.sql)</SwmPath> script inserts sample vet records such as 'James Carter' and 'Helen Leary'. This example illustrates how the schema and data scripts work together to provide a ready-to-use database on application launch.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
