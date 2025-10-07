---
title: 'WelcomeController: Spring MVC Controller for Welcome Page'
---
# Introduction

This document explains the design and implementation choices behind the welcome page controller in the system module. We will cover:

1. Why a dedicated controller class was created for the welcome page.
2. How the controller handles HTTP requests and maps them to views.

# why a dedicated controller class for the welcome page

The welcome page is a simple entry point for the application, so it makes sense to isolate its handling in a focused controller. This keeps the code modular and easy to maintain. The controller is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="22:0:1" line-data="@Controller">`@Controller`</SwmToken> to mark it as a Spring MVC component that handles web requests.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="17">

---

This is shown in the class declaration and annotation:

```java
package org.springframework.samples.petclinic.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {
```

---

</SwmSnippet>

# how the controller maps requests to views

The controller uses the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="25:1:7" line-data="	@GetMapping(&quot;/&quot;)">`@GetMapping("/")`</SwmToken> annotation to map HTTP GET requests for the root URL to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="26:5:7" line-data="	public String welcome() {">`welcome()`</SwmToken> method. This method returns the logical view name <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="26:5:5" line-data="	public String welcome() {">`welcome`</SwmToken>, which Spring MVC resolves to the corresponding template or JSP page.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="25">

---

This approach keeps the controller simple and declarative, relying on Spring MVC's conventions for request handling and view resolution:

```java
	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
