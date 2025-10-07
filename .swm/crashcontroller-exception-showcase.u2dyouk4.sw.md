---
title: CrashController Exception Showcase
---
# Introduction

This document explains the rationale and main points behind the implementation of a controller designed to demonstrate exception handling in the application.

We will cover:

1. Why a dedicated controller is used to trigger exceptions.
2. How the exception is triggered and what it demonstrates.
3. The role of the controller in relation to error views.

# purpose of the crash controller

The controller exists solely to showcase what happens when an exception is thrown during request processing. This is useful for testing and demonstrating the application's error handling mechanisms without relying on accidental or unpredictable errors.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CrashController.java" line="18">

---

The class is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CrashController.java" pos="28:0:1" line-data="@Controller">`@Controller`</SwmToken> to register it as a Spring MVC controller, making it accessible via HTTP requests.

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller used to showcase what happens when an exception is thrown
 *
 * @author Michael Isvy
 * <p/>
 * Also see how a view that resolves to "error" has been added ("error.html").
 */
@Controller
class CrashController {
```

---

</SwmSnippet>

# triggering an exception explicitly

The controller exposes a single GET endpoint mapped to <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CrashController.java" pos="31:5:6" line-data="	@GetMapping(&quot;/oups&quot;)">`/oups`</SwmToken>. When accessed, this method immediately throws a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CrashController.java" pos="33:5:5" line-data="		throw new RuntimeException(">`RuntimeException`</SwmToken> with a clear message indicating its purpose.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CrashController.java" line="31">

---

This explicit exception throwing bypasses any normal processing and forces the application to handle an error scenario. It helps verify that the error view (typically resolved to <SwmPath>[src/…/templates/error.html](src/main/resources/templates/error.html)</SwmPath>) is correctly displayed when exceptions occur.

```java
	@GetMapping("/oups")
	public String triggerException() {
		throw new RuntimeException(
				"Expected: controller used to showcase what " + "happens when an exception is thrown");
	}

}
```

---

</SwmSnippet>

# integration with error views

The comment in the code notes that there is an error view configured to handle exceptions. By throwing an exception here, the controller triggers the error resolution mechanism, allowing developers to see how the application responds visually and functionally to runtime errors.

This setup is minimal but effective for demonstrating exception handling behavior in a controlled way.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
