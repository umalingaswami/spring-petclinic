---
title: updated with new method in existing class
---
# Introduction

This document explains the rationale behind adding a new method to an existing class and the related design choices. We will cover:

1. Why the new method was added and what it does.
2. How the new method fits within the existing class structure.
3. The approach taken to handle method implementation and output.

# adding a new method to an existing class

The new method was introduced in the class located at <SwmPath>[src/…/petclinic/SimpleGreeting.java](/src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java)</SwmPath>. This method calculates the square of an integer and prints the result. The purpose is to extend the class functionality with a simple mathematical operation that can be reused wherever needed.

<SwmSnippet path="src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" line="21">

---

This method is straightforward: it takes an integer input, computes its square, and outputs the result to the console. This approach keeps the method focused on a single responsibility and avoids side effects beyond printing.

```
    public void square(int a){
        int square =a*a;
        System.out.println("Square is: " +square);
    }
    returnType add(){
        
    }
```

---

</SwmSnippet>

# context of the existing classes

The existing classes like <SwmToken path="/src/main/java/org/springframework/samples/petclinic/Employee.java" pos="1:4:4" line-data="public class Employee{">`Employee`</SwmToken> and <SwmToken path="/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java" pos="30:4:4" line-data="public class PetClinicApplication {">`PetClinicApplication`</SwmToken> serve different roles. For example, <SwmToken path="/src/main/java/org/springframework/samples/petclinic/Employee.java" pos="1:4:4" line-data="public class Employee{">`Employee`</SwmToken> currently only contains a main method that prints a greeting, which suggests it might be a placeholder or a simple test class.

The <SwmToken path="/src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java" pos="30:4:4" line-data="public class PetClinicApplication {">`PetClinicApplication`</SwmToken> class includes a utility method to print active Spring profiles, which is useful for debugging and environment awareness during application startup. This method accesses the Spring environment statically and outputs the active profiles or a message if none are set.

<SwmSnippet path="src/main/java/org/springframework/samples/petclinic/Employee.java" line="1">

---

These classes show a pattern of including utility or simple output methods, which aligns with the addition of the new method in <SwmToken path="/src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" pos="1:4:4" line-data="public class SimpleGreeting {">`SimpleGreeting`</SwmToken>. The new method follows this pattern by providing a utility function that outputs a calculated value.

```
public class Employee{
    public static void main(String[] args){
        System.out.println("hello");
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="src/main/java/org/springframework/samples/petclinic/PetClinicApplication.java" line="45">

---

&nbsp;

```
 
    /**
     * Prints the current active Spring profiles.
     */
    public static void printActiveProfiles() {
        String[] profiles = org.springframework.context.annotation.AnnotationConfigApplicationContext
            .getEnvironmentStatic().getActiveProfiles();
        if (profiles.length == 0) {
            System.out.println("No active Spring profiles.");
        } else {
            System.out.println("Active Spring profiles: " + String.join(", ", profiles));
        }
    }

```

---

</SwmSnippet>

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
