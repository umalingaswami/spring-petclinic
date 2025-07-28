---
title: updated with new method in existing class
---
# Introduction

This document explains the rationale behind adding a new method to an existing class. We will cover:

1. Why the new method was added to this particular class.
2. What the new method does and how it fits into the existing codebase.
3. How this method impacts the class's responsibilities and usage.

# why add a new method to this class

The class in question is a simple utility class designed to handle basic operations related to greetings or simple calculations. Adding the new method here keeps related functionality grouped together, avoiding the need for a separate utility class or external helper. This maintains cohesion and makes the class more versatile without overcomplicating it.

# what the new method does

<SwmSnippet path="src/main/java/org/springframework/samples/petclinic/SimpleGreeting.java" line="21">

---

The new method calculates the square of an integer and prints the result. This is a straightforward operation that complements the class's existing responsibilities by providing a simple mathematical utility. It does not return a value but outputs the result directly, which suggests its use is primarily for quick debugging or demonstration purposes rather than for further computation.

```
    public void square(int a){
        int square =a*a;
        System.out.println("Square is: " +square);
    }
```

---

</SwmSnippet>

# impact on the class and usage

By adding this method, the class now supports a basic arithmetic operation alongside its existing features. This can simplify client code that needs to perform this calculation without introducing additional dependencies. However, since the method prints directly to the console, it is not suitable for scenarios requiring the squared value for further processing. If such use cases arise, a future enhancement might involve returning the value instead of printing it.

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
