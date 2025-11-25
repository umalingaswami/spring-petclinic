---
title: MySQL Database Setup Script for Petclinic
---
# introduction

This document explains the MySQL setup script used for the Petclinic application database. It covers:

1. What the script does to prepare the database environment.
2. The parameters and settings configured in the script.
3. How to run the script to initialize the database.

# what the script does

The script creates a database named `petclinic` if it doesn't already exist. It then sets the default character set to UTF-8 and the collation to `utf8_general_ci`. This ensures the database can store multilingual text properly and sorts data in a consistent way.

After that, it grants all privileges on the `petclinic` database to a user named `petclinic` who can connect from any host (`'%'`). The password for this user is also set to `petclinic`. This user will be used by the application to connect to the database with full access rights.

# parameters and settings

- Database name: `petclinic`
- Character set: `utf8`
- Collation: `utf8_general_ci`
- Database user: `petclinic`
- User host: `%` (any host)
- User password: `petclinic`

These parameters are hardcoded in the script and should match the application's datasource configuration.

# how to run the script

To run this script, you need access to a MySQL server with sufficient privileges to create databases and users.

1. Save the script content to a file, for example <SwmPath>[src/…/mysql/user.sql](src/main/resources/db/mysql/user.sql)</SwmPath>.
2. Connect to your MySQL server using a client like `mysql` command line tool.
3. Run the script with a command like:

```
mysql -u root -p < path/to/user.sql
```

Replace `root` with a user that has the necessary privileges. You will be prompted for the password.

Once executed, the `petclinic` database and user will be ready for the application to use.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
