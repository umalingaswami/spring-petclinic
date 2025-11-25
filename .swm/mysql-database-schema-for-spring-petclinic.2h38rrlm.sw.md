---
title: MySQL Database Schema for Spring Petclinic
---
# introduction

This document explains the MySQL database schema design for the Spring Petclinic application. It covers the main tables and their relationships, focusing on how the schema supports the domain model and enforces data integrity.

We will cover:

1. How vets and their specialties are modeled and linked.
2. How pet types, owners, and pets are structured and related.
3. How visits are recorded and connected to pets.

# vets and specialties

The schema defines a <SwmToken path="src/main/resources/db/mysql/schema.sql" pos="1:10:10" line-data="CREATE TABLE IF NOT EXISTS vets (">`vets`</SwmToken> table to store veterinarian details with an index on last names for efficient lookup. Specialties are stored separately in the <SwmToken path="src/main/resources/db/mysql/schema.sql" pos="8:10:10" line-data="CREATE TABLE IF NOT EXISTS specialties (">`specialties`</SwmToken> table, also indexed by name for quick searches.

To represent the many-to-many relationship between vets and specialties, a join table <SwmToken path="src/main/resources/db/mysql/schema.sql" pos="14:10:10" line-data="CREATE TABLE IF NOT EXISTS vet_specialties (">`vet_specialties`</SwmToken> is created. It enforces referential integrity with foreign keys to both <SwmToken path="src/main/resources/db/mysql/schema.sql" pos="1:10:10" line-data="CREATE TABLE IF NOT EXISTS vets (">`vets`</SwmToken> and <SwmToken path="src/main/resources/db/mysql/schema.sql" pos="8:10:10" line-data="CREATE TABLE IF NOT EXISTS specialties (">`specialties`</SwmToken> and ensures uniqueness of each vet-specialty pair to avoid duplicates.

<SwmSnippet path="/src/main/resources/db/mysql/schema.sql" line="1">

---

This design cleanly separates vets and specialties while allowing flexible assignment of multiple specialties to each vet.

```plsql
CREATE TABLE IF NOT EXISTS vets (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS specialties (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS vet_specialties (
  vet_id INT(4) UNSIGNED NOT NULL,
  specialty_id INT(4) UNSIGNED NOT NULL,
  FOREIGN KEY (vet_id) REFERENCES vets(id),
  FOREIGN KEY (specialty_id) REFERENCES specialties(id),
  UNIQUE (vet_id,specialty_id)
) engine=InnoDB;
```

---

</SwmSnippet>

# pet types, owners, and pets

Pet types are stored in the <SwmToken path="src/main/resources/db/mysql/schema.sql" pos="22:10:10" line-data="CREATE TABLE IF NOT EXISTS types (">`types`</SwmToken> table, indexed by name for fast retrieval. Owners are stored in the <SwmToken path="src/main/resources/db/mysql/schema.sql" pos="28:10:10" line-data="CREATE TABLE IF NOT EXISTS owners (">`owners`</SwmToken> table with personal and contact details, indexed by last name to support owner searches.

The <SwmToken path="src/main/resources/db/mysql/schema.sql" pos="38:10:10" line-data="CREATE TABLE IF NOT EXISTS pets (">`pets`</SwmToken> table links each pet to its owner and type via foreign keys. Indexing pet names supports quick pet lookups. This structure enforces that every pet has a valid type and optionally an owner, maintaining data consistency.

<SwmSnippet path="/src/main/resources/db/mysql/schema.sql" line="22">

---

This setup reflects the domain model where pets belong to owners and have a defined type.

```plsql
CREATE TABLE IF NOT EXISTS types (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(80),
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS owners (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  address VARCHAR(255),
  city VARCHAR(80),
  telephone VARCHAR(20),
  INDEX(last_name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS pets (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30),
  birth_date DATE,
  type_id INT(4) UNSIGNED NOT NULL,
  owner_id INT(4) UNSIGNED,
  INDEX(name),
  FOREIGN KEY (owner_id) REFERENCES owners(id),
  FOREIGN KEY (type_id) REFERENCES types(id)
) engine=InnoDB;
```

---

</SwmSnippet>

# visits

Visits are recorded in the <SwmToken path="src/main/resources/db/mysql/schema.sql" pos="49:10:10" line-data="CREATE TABLE IF NOT EXISTS visits (">`visits`</SwmToken> table, which links each visit to a pet through a foreign key. It stores the visit date and description. This ensures that visits cannot exist without an associated pet, preserving referential integrity.

<SwmSnippet path="/src/main/resources/db/mysql/schema.sql" line="49">

---

This table supports tracking medical history and appointments for pets.

```plsql
CREATE TABLE IF NOT EXISTS visits (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  pet_id INT(4) UNSIGNED,
  visit_date DATE,
  description VARCHAR(255),
  FOREIGN KEY (pet_id) REFERENCES pets(id)
) engine=InnoDB;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
