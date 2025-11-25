---
title: Application Resources Overview
---
# Overview of Application Resources

Application resources in this project are located under the <SwmPath>[src/main/resources/](src/main/resources/)</SwmPath> directory. They consist of various files that provide essential support for configuration, user interface templates, localization, and startup customization. These resources help separate static content and configuration from the application logic, promoting maintainability and flexibility.

# Configuration Files

The configuration files, primarily the <SwmPath>[src/…/resources/application.properties](src/main/resources/application.properties)</SwmPath> files, define environment-specific settings. There are multiple variants such as the default configuration, PostgreSQL-specific, and MySQL-specific files. This setup allows the application to adapt seamlessly to different database backends and environment parameters without modifying the source code.

# User Interface Templates

HTML templates are stored in the `templates` directory. These include files like <SwmPath>[src/…/templates/error.html](src/main/resources/templates/error.html)</SwmPath> and <SwmPath>[src/…/templates/welcome.html](src/main/resources/templates/welcome.html)</SwmPath>, which are used to render views for the user interface. The templates support dynamic content through placeholders and fragments, enabling flexible and reusable UI components.

# Localization Support

Localization is implemented using message property files located in the `messages` directory. These files provide translations for multiple languages, including English, Spanish, and German. For example, the <SwmPath>[src/…/messages/messages.properties](src/main/resources/messages/messages.properties)</SwmPath> file contains key-value pairs such as `duplicate=is already in use`, which are referenced in the UI to display localized messages to users.

# Custom Startup Banner

The <SwmPath>[src/…/resources/banner.txt](src/main/resources/banner.txt)</SwmPath> file contains a custom banner message that is displayed during application startup. This feature enhances the startup experience by allowing branding or informational messages to be shown when the application launches.

# Example of Localization Usage

A practical example of localization usage is the <SwmPath>[src/…/messages/messages.properties](src/main/resources/messages/messages.properties)</SwmPath> file where the key `duplicate` maps to the message `is already in use`. This message can be referenced in the user interface to inform users about duplicate entries in a localized manner, improving user experience across different languages.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
