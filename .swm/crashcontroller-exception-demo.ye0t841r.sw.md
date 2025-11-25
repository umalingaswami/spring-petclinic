---
title: CrashController Exception Demo
---
# introduction

This document explains why and how the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CrashController.java" pos="29:2:2" line-data="class CrashController {">`CrashController`</SwmToken> was implemented in the system package. It answers these questions:

1. Why introduce a controller that throws an exception deliberately?
2. How does the controller trigger the exception?
3. How does this fit into the overall error handling strategy?

# purpose of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CrashController.java" pos="29:2:2" line-data="class CrashController {">`CrashController`</SwmToken>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CrashController.java" line="18">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CrashController.java" pos="29:2:2" line-data="class CrashController {">`CrashController`</SwmToken> exists to demonstrate what happens when an exception is thrown during request handling. This is useful for testing and showcasing the application's error handling behavior, including how error views are resolved. The controller is annotated with @Controller, making it a Spring MVC component that handles web requests.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CrashController.java" line="31">

---

The controller exposes a single GET endpoint mapped to "/oups". When this endpoint is accessed, the method <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CrashController.java" pos="32:5:7" line-data="	public String triggerException() {">`triggerException()`</SwmToken> immediately throws a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CrashController.java" pos="33:5:5" line-data="		throw new RuntimeException(">`RuntimeException`</SwmToken> with a descriptive message. This is not a bug but an intentional crash to simulate failure scenarios.

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

The comment in the code mentions that there is an error view named <SwmPath>[src/…/templates/error.html](src/main/resources/templates/error.html)</SwmPath> configured elsewhere. When the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CrashController.java" pos="33:5:5" line-data="		throw new RuntimeException(">`RuntimeException`</SwmToken> is thrown, Spring’s default error handling mechanism will catch it and forward the user to this error page. This setup allows developers to verify that exceptions are properly caught and that the user sees a meaningful error page instead of a stack trace or blank screen.

This controller is minimal by design, focusing solely on provoking an exception to test the error handling pipeline.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
