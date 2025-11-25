---
title: 'WelcomeController: Spring MVC Controller for Welcome Page'
---
# Introduction

This document explains the design and implementation of the welcome page controller in the Spring Petclinic application. It answers these questions:

1. Why is a dedicated controller created for the welcome page?
2. How does the controller handle HTTP requests for the root URL?
3. Why is the controller implemented as a Spring MVC controller with specific annotations?

# purpose of the welcome controller

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="1">

---

The welcome page serves as the entry point of the application, typically shown when users access the root URL. To handle this, a dedicated controller class is created in <SwmPath>[src/…/system/WelcomeController.java](src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java)</SwmPath>. This isolates the welcome page logic from other parts of the app and keeps the code organized. The file starts with standard license and package declarations, which are boilerplate and not central to the logic.

```java
/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
```

---

</SwmSnippet>

# spring mvc controller setup

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="17">

---

The controller class is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="22:0:1" line-data="@Controller">`@Controller`</SwmToken>, marking it as a Spring MVC controller that can handle web requests. This annotation enables Spring to detect and register it as a component during startup. The class is placed in the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="17:2:10" line-data="package org.springframework.samples.petclinic.system;">`org.springframework.samples.petclinic.system`</SwmToken> package, which groups system-level controllers.

```java
package org.springframework.samples.petclinic.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WelcomeController {
```

---

</SwmSnippet>

# handling the root url request

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="25">

---

The controller defines a single method annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="25:1:7" line-data="	@GetMapping(&quot;/&quot;)">`@GetMapping("/")`</SwmToken>. This maps HTTP GET requests for the root URL `/` to this method. The method returns the string <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="26:5:5" line-data="	public String welcome() {">`welcome`</SwmToken>, which corresponds to the logical view name. Spring MVC resolves this to the actual welcome page template (e.g., a Thymeleaf or JSP file named <SwmPath>[src/…/templates/welcome.html](src/main/resources/templates/welcome.html)</SwmPath> or `welcome.jsp`). This setup cleanly separates the URL routing from the view rendering.

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
