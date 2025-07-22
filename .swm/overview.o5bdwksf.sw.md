---
title: Overview
---
The Spring PetClinic repository is a sample application designed to demonstrate the use of Spring Boot and related technologies. It provides a high-level overview of how to build a web application for managing a pet clinic, including functionalities for handling owners, pets, and veterinary services. The Spring PetClinic application serves as a demonstration of how to use Spring Boot to build a web application. It includes various packages that represent different aspects of a pet clinic, such as owners, pets, and veterinary services. The application is structured to showcase best practices in Spring Boot development, including the use of controllers, repositories, and entities to manage data and interactions within the pet clinic domain.

# Main Components

### Application Core (<SwmPath>[src/main/](src/main/)</SwmPath>)

The Application Core is responsible for configuring essential properties such as database initialization, web settings, JPA configurations, internationalization, actuator endpoints, logging levels, and caching of static resources.

- **Vet**
  - **Vet repository**
    - **Classes**
      - <SwmLink doc-title="The vetrepository interface">[The vetrepository interface](/.swm/the-vetrepository-interface.gcr4a.sw.md)</SwmLink>
  - **Vet controller**
    - **Classes**
      - <SwmLink doc-title="The vetcontroller class">[The vetcontroller class](/.swm/the-vetcontroller-class.tdxta.sw.md)</SwmLink>
  - **Vet**
    - **Classes**
      - <SwmLink doc-title="The vet class">[The vet class](/.swm/the-vet-class.7kafm.sw.md)</SwmLink>
- **System**
  - **Web configuration**
    - **Classes**
      - <SwmLink doc-title="The webconfiguration class">[The webconfiguration class](/.swm/the-webconfiguration-class.e2pln.sw.md)</SwmLink>
  - **Cache configuration**
    - **Classes**
      - <SwmLink doc-title="The cacheconfiguration class">[The cacheconfiguration class](/.swm/the-cacheconfiguration-class.ovpnm.sw.md)</SwmLink>
- **Model**
  - **Base entity**
    - **Classes**
      - <SwmLink doc-title="The baseentity class">[The baseentity class](/.swm/the-baseentity-class.d2iaw.sw.md)</SwmLink>
  - **Named entity**
    - **Classes**
      - <SwmLink doc-title="The namedentity class">[The namedentity class](/.swm/the-namedentity-class.sgttj.sw.md)</SwmLink>
  - **Person**
    - **Classes**
      - <SwmLink doc-title="The person class">[The person class](/.swm/the-person-class.ljlhh.sw.md)</SwmLink>
- **Owner**
  - **Visit controller**
    - **Classes**
      - <SwmLink doc-title="The visitcontroller class">[The visitcontroller class](/.swm/the-visitcontroller-class.9fdmc.sw.md)</SwmLink>
  - **Visit**
    - **Classes**
      - <SwmLink doc-title="The visit class">[The visit class](/.swm/the-visit-class.fhfw7.sw.md)</SwmLink>
  - **Pet validator**
    - **Classes**
      - <SwmLink doc-title="The petvalidator class">[The petvalidator class](/.swm/the-petvalidator-class.mterp.sw.md)</SwmLink>
  - **Pet type formatter**
    - **Classes**
      - <SwmLink doc-title="The pettypeformatter class">[The pettypeformatter class](/.swm/the-pettypeformatter-class.q6glk.sw.md)</SwmLink>
  - **Pet controller**
    - **Classes**
      - <SwmLink doc-title="The petcontroller class">[The petcontroller class](/.swm/the-petcontroller-class.gwo74.sw.md)</SwmLink>
  - **Pet**
    - **Classes**
      - <SwmLink doc-title="The pet class">[The pet class](/.swm/the-pet-class.0ul59.sw.md)</SwmLink>
  - **Owner repository**
    - **Classes**
      - <SwmLink doc-title="The ownerrepository interface">[The ownerrepository interface](/.swm/the-ownerrepository-interface.u3bv7.sw.md)</SwmLink>
  - **Owner controller**
    - **Classes**
      - <SwmLink doc-title="The ownercontroller class">[The ownercontroller class](/.swm/the-ownercontroller-class.tbhfh.sw.md)</SwmLink>
  - **Owner**
    - **Classes**
      - <SwmLink doc-title="The owner class">[The owner class](/.swm/the-owner-class.nfapc.sw.md)</SwmLink>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
