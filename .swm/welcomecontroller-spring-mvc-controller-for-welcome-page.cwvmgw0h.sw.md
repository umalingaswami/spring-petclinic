---
title: 'WelcomeController: Spring MVC Controller for Welcome Page'
---
# Introduction

This document explains the design and implementation of the welcome page controller in the Spring Petclinic application. It answers these questions:

1. Why is a dedicated controller created for the welcome page?
2. How does the controller handle HTTP requests?
3. What is the role of the returned string in the controller method?

# purpose of the welcome controller

The welcome page is the entry point of the application, so it needs a dedicated controller to handle requests to the root URL ("/"). This controller is minimal and focused solely on serving the welcome view. This keeps the application modular and separates concerns clearly.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="17">

---

The controller is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="22:0:1" line-data="@Controller">`@Controller`</SwmToken>, which marks it as a Spring MVC controller that can handle web requests.

```java
package org.springframework.samples.petclinic.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {
```

---

</SwmSnippet>

# handling the root URL request

The controller defines a single method mapped to the HTTP GET request for the root path ("/"). This is done using the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="25:1:7" line-data="	@GetMapping(&quot;/&quot;)">`@GetMapping("/")`</SwmToken> annotation. When a user accesses the base URL of the application, this method is invoked.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="25">

---

The method returns the logical view name "welcome". Spring MVC resolves this name to the actual welcome page template (e.g., a Thymeleaf or JSP file named "welcome"). This approach decouples the controller logic from the view technology.

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
