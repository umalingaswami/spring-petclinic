---
title: Database schema creation script for HSQLDB
---
# introduction

This document explains the database schema creation script for HSQLDB used in the project. The script defines the tables, indexes, and foreign key constraints needed to represent the domain model in the database.

We will cover:

1. How the script handles existing tables before creation.
2. The main tables and their columns.
3. Indexes and why they are added.
4. Foreign key constraints and their role in data integrity.
5. How to run the script.

# handling existing tables

<SwmSnippet path="/src/main/resources/db/hsqldb/schema.sql" line="1">

---

The script starts by dropping all relevant tables if they already exist. This ensures a clean state before creating the schema, avoiding conflicts or errors from leftover tables.

```plsql
DROP TABLE vet_specialties IF EXISTS;
DROP TABLE vets IF EXISTS;
DROP TABLE specialties IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE pets IF EXISTS;
DROP TABLE types IF EXISTS;
DROP TABLE owners IF EXISTS;
```

---

</SwmSnippet>

# main tables and columns

The script defines tables for vets, specialties, <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="1:4:4" line-data="DROP TABLE vet_specialties IF EXISTS;">`vet_specialties`</SwmToken> (join table), types (pet types), owners, pets, and visits. Each table has an ID column as the primary key, mostly using <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="11:3:5" line-data="  id         INTEGER IDENTITY PRIMARY KEY,">`INTEGER IDENTITY`</SwmToken> for auto-incrementing IDs.

<SwmSnippet path="/src/main/resources/db/hsqldb/schema.sql" line="10">

---

- <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="2:4:4" line-data="DROP TABLE vets IF EXISTS;">`vets`</SwmToken> and <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="3:4:4" line-data="DROP TABLE specialties IF EXISTS;">`specialties`</SwmToken> store basic info about veterinarians and their specialties.
- <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="1:4:4" line-data="DROP TABLE vet_specialties IF EXISTS;">`vet_specialties`</SwmToken> links vets to their specialties via foreign keys.
- <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="6:4:4" line-data="DROP TABLE types IF EXISTS;">`types`</SwmToken> holds pet types like dog or cat.
- <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="7:4:4" line-data="DROP TABLE owners IF EXISTS;">`owners`</SwmToken> stores owner details including name, address, city, and telephone.
- <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="5:4:4" line-data="DROP TABLE pets IF EXISTS;">`pets`</SwmToken> links to owners and types, storing pet-specific info like name and birth date.
- <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="4:4:4" line-data="DROP TABLE visits IF EXISTS;">`visits`</SwmToken> records visits for pets with date and description.

```plsql
CREATE TABLE vets (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX vets_last_name ON vets (last_name);

CREATE TABLE specialties (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE vet_specialties (
  vet_id       INTEGER NOT NULL,
  specialty_id INTEGER NOT NULL
);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE owners (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR_IGNORECASE(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  telephone  VARCHAR(20)
);
CREATE INDEX owners_last_name ON owners (last_name);

CREATE TABLE pets (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  birth_date DATE,
  type_id    INTEGER NOT NULL,
  owner_id   INTEGER
);
ALTER TABLE pets ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
ALTER TABLE pets ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name ON pets (name);

CREATE TABLE visits (
  id          INTEGER IDENTITY PRIMARY KEY,
  pet_id      INTEGER,
  visit_date  DATE,
  description VARCHAR(255)
);
ALTER TABLE visits ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
CREATE INDEX visits_pet_id ON visits (pet_id);
```

---

</SwmSnippet>

# indexes

Indexes are created on columns frequently used in queries, such as last names, specialty names, pet names, and foreign key columns. This improves query performance when searching or joining tables.

# foreign key constraints

Foreign keys enforce relationships between tables, ensuring data consistency. For example:

- <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="1:4:4" line-data="DROP TABLE vet_specialties IF EXISTS;">`vet_specialties`</SwmToken> references <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="2:4:4" line-data="DROP TABLE vets IF EXISTS;">`vets`</SwmToken> and <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="3:4:4" line-data="DROP TABLE specialties IF EXISTS;">`specialties`</SwmToken>.
- <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="5:4:4" line-data="DROP TABLE pets IF EXISTS;">`pets`</SwmToken> references <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="7:4:4" line-data="DROP TABLE owners IF EXISTS;">`owners`</SwmToken> and <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="6:4:4" line-data="DROP TABLE types IF EXISTS;">`types`</SwmToken>.
- <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="4:4:4" line-data="DROP TABLE visits IF EXISTS;">`visits`</SwmToken> references <SwmToken path="src/main/resources/db/hsqldb/schema.sql" pos="5:4:4" line-data="DROP TABLE pets IF EXISTS;">`pets`</SwmToken>.

These constraints prevent orphan records and maintain referential integrity.

# running the script

This script is a plain SQL file located at <SwmPath>[src/…/hsqldb/schema.sql](src/main/resources/db/hsqldb/schema.sql)</SwmPath>. It can be run using any HSQLDB client or embedded in the application startup to initialize the database schema. Running it will drop existing tables and recreate them with the defined structure, indexes, and constraints.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
