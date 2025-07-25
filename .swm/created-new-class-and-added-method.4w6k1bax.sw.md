---
title: created new class and added method
---
# Introduction

This document will walk you through the creation of a new class and the addition of a method in the <SwmToken path="/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java" pos="24:3:3" line-data=" * PetClinic Spring Boot Application.">`PetClinic`</SwmToken> application.

The purpose of this change is to introduce a simple greeting functionality that can be utilized across the application.

We will cover:

1. Why the <SwmToken path="/src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" pos="1:4:4" line-data="public class SimpleGreeting {">`SimpleGreeting`</SwmToken> class was created.
2. How the greet method is implemented and its role.
3. How the <SwmToken path="/src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" pos="1:4:4" line-data="public class SimpleGreeting {">`SimpleGreeting`</SwmToken> class is utilized in the application.

# Creation of <SwmToken path="/src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" pos="1:4:4" line-data="public class SimpleGreeting {">`SimpleGreeting`</SwmToken> class

<SwmSnippet path="src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" line="1">

---

The <SwmToken path="/src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" pos="1:4:4" line-data="public class SimpleGreeting {">`SimpleGreeting`</SwmToken> class was created to encapsulate the logic for generating greeting messages. This design decision allows for a modular approach, making it easier to manage and extend greeting functionalities in the future.

```
public class SimpleGreeting {
    private String message;

    public SimpleGreeting(String message) {
        this.message = message;
    }

    public void greet() {
        System.out.println("Hello, " + message + "!");
    }
```

---

</SwmSnippet>

# Implementation of greet method

<SwmSnippet path="src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" line="12">

---

The greet method in the <SwmToken path="/src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" pos="1:4:4" line-data="public class SimpleGreeting {">`SimpleGreeting`</SwmToken> class is responsible for outputting a greeting message to the console. This method is straightforward, focusing on the core functionality of displaying a message prefixed with "Hello,".

```
    public static void main(String[] args) {
        SimpleGreeting greeting = new SimpleGreeting("World");
        greeting.greet();
    }
}
```

---

</SwmSnippet>

# Utilization in <SwmToken path="/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java" pos="30:4:4" line-data="public class PetClinicApplication {">`PetClinicApplication`</SwmToken>

<SwmSnippet path="src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java" line="35">

---

The <SwmToken path="/src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" pos="1:4:4" line-data="public class SimpleGreeting {">`SimpleGreeting`</SwmToken> class is utilized in the <SwmToken path="/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java" pos="30:4:4" line-data="public class PetClinicApplication {">`PetClinicApplication`</SwmToken> to demonstrate its functionality. The <SwmToken path="/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java" pos="40:7:7" line-data="    public static void callSimpleGreeting(String message) {">`callSimpleGreeting`</SwmToken> method instantiates the <SwmToken path="/src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" pos="1:4:4" line-data="public class SimpleGreeting {">`SimpleGreeting`</SwmToken> class and invokes the greet method, showcasing how the greeting feature can be integrated into the application.

```
	

    /**
     * Calls the SimpleGreeting class to print a greeting message.
     */
    public static void callSimpleGreeting(String message) {
        org.springframework.samples.petclinic.service.SimpleGreeting greeting =
            new org.springframework.samples.petclinic.service.SimpleGreeting(message);
        greeting.greet();
    }
```

---

</SwmSnippet>

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
