---
title: CrashController Exception Showcase
---
# Introduction

This document explains the rationale and main points behind the implementation of a controller designed to demonstrate exception handling in the application.

We will cover:

1. Why a dedicated controller is used to trigger exceptions.
2. How the exception is triggered and what it demonstrates.
3. How this ties into the error view mechanism.

# purpose of the controller

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CrashController.java" line="18">

---

The controller exists solely to showcase what happens when an exception is thrown during request handling. This is useful for testing and demonstrating the application's error handling capabilities without relying on accidental or real errors. It is a minimal, focused way to provoke an error scenario on demand.

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

# triggering the exception

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CrashController.java" line="31">

---

The controller exposes a single GET endpoint at "/oups". When accessed, it immediately throws a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CrashController.java" pos="33:5:5" line-data="		throw new RuntimeException(">`RuntimeException`</SwmToken> with a clear message explaining its purpose. This direct throwing of an exception simulates a failure in the controller layer, allowing developers to observe how the framework and application respond.

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

# integration with error view

The comment in the controller notes that there is a view named <SwmPath>[src/…/templates/error.html](src/main/resources/templates/error.html)</SwmPath> configured to handle error rendering. When the exception is thrown, the framework routes to this error view, showing a user-friendly error page instead of a stack trace or raw error. This setup demonstrates the full cycle from exception occurrence to user-facing error display.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
