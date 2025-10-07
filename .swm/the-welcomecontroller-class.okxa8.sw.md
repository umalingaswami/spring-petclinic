---
title: The WelcomeController class
---
This document covers the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="23:2:2" line-data="class WelcomeController {">`WelcomeController`</SwmToken> class. We will explain:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="23:2:2" line-data="class WelcomeController {">`WelcomeController`</SwmToken> is and its purpose.
2. The variables and functions defined in <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="23:2:2" line-data="class WelcomeController {">`WelcomeController`</SwmToken>, including the welcome function.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="23:2:2" line-data="class WelcomeController {">`WelcomeController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="23:2:2" line-data="class WelcomeController {">`WelcomeController`</SwmToken> is a Spring MVC controller class located in <SwmPath>[src/…/system/WelcomeController.java](src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java)</SwmPath>. It is responsible for handling HTTP GET requests to the root URL ("/") of the application. Its main purpose is to serve the welcome page of the application by returning the logical view name "welcome" which is resolved by the view resolver to render the welcome page.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" line="25">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="26:5:5" line-data="	public String welcome() {">`welcome`</SwmToken> is the only method defined in <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="23:2:2" line-data="class WelcomeController {">`WelcomeController`</SwmToken>. It is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="25:2:2" line-data="	@GetMapping(&quot;/&quot;)">`GetMapping`</SwmToken>("/"), which maps HTTP GET requests for the root URL to this method. When invoked, it returns the string "welcome", indicating the name of the view to be rendered.

```java
	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="23:2:2" line-data="class WelcomeController {">`WelcomeController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="23:2:2" line-data="class WelcomeController {">`WelcomeController`</SwmToken> is used to handle HTTP GET requests mapped to the root path "/". It is annotated with @Controller, indicating it is a Spring MVC controller that processes web requests. The method welcome() is mapped with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java" pos="25:2:2" line-data="	@GetMapping(&quot;/&quot;)">`GetMapping`</SwmToken>("/"), which means it responds to GET requests at the root URL by returning a view name, typically rendering the welcome page of the application.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
