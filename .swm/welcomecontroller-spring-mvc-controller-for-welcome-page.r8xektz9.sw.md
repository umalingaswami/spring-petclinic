---
title: 'WelcomeController: Spring MVC Controller for Welcome Page'
---
# Introduction

This document explains the design and implementation of the welcome page controller in the application. It answers these questions:

1. Why is a dedicated controller created for the welcome page?
2. How does the controller handle HTTP requests?
3. What is the role of the returned string in the controller method?

# purpose of the welcome controller

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="17">

---

The welcome controller is a Spring MVC controller responsible for handling requests to the root URL ("/"). It serves as the entry point for users accessing the application, directing them to the welcome page. This separation keeps the routing logic for the welcome page isolated and clear.

```java
package org.springframework.samples.petclinic.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {
```

---

</SwmSnippet>

# handling HTTP GET requests

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="25">

---

The controller uses the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="25:1:7" line-data="	@GetMapping(&quot;/&quot;)">`@GetMapping("/")`</SwmToken> annotation to map HTTP GET requests for the root path to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="26:5:7" line-data="	public String welcome() {">`welcome()`</SwmToken> method. This method is invoked whenever a user navigates to the base URL of the app.

```java
	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}

}
```

---

</SwmSnippet>

# returning the view name

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="25">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="26:5:7" line-data="	public String welcome() {">`welcome()`</SwmToken> method returns the string <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="26:5:5" line-data="	public String welcome() {">`welcome`</SwmToken>. This string corresponds to the logical name of the view that should be rendered. The view resolver in Spring MVC will use this name to locate and render the appropriate welcome page template.

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
