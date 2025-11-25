---
title: 'PetTypeFormatter: Spring Formatter for PetType'
---
# Introduction

This document explains the rationale and main points behind the implementation of a Spring Formatter for the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken> class. We will cover:

1. Why a Formatter is used instead of a PropertyEditor.
2. How the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="37:4:4" line-data="public class PetTypeFormatter implements Formatter&lt;PetType&gt; {">`PetTypeFormatter`</SwmToken> converts between <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken> objects and their string representations.
3. How the formatter accesses the available pet types for parsing.

# why use a formatter for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken>

Spring introduced the Formatter SPI as a more modern and flexible alternative to legacy <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="29:3:3" line-data=" * PropertyEditors. See the following links for more details: - The Spring ref doc:">`PropertyEditors`</SwmToken>. Formatters provide a consistent way to convert between object types and strings, supporting locale-sensitive parsing and printing. This fits well with Spring MVC's data binding and form handling.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="26">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="37:4:4" line-data="public class PetTypeFormatter implements Formatter&lt;PetType&gt; {">`PetTypeFormatter`</SwmToken> is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="36:0:1" line-data="@Component">`@Component`</SwmToken> so Spring can detect and register it automatically, enabling it to participate in the conversion process without manual configuration.

```java
/**
 * Instructs Spring MVC on how to parse and print elements of type 'PetType'. Starting
 * from Spring 3.0, Formatters have come as an improvement in comparison to legacy
 * PropertyEditors. See the following links for more details: - The Spring ref doc:
 * https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#format
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Michael Isvy
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {
```

---

</SwmSnippet>

# how printing works

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="46">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:5:5" line-data="	public String print(PetType petType, Locale locale) {">`print`</SwmToken> method defines how a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> instance is converted to a string for display purposes, such as rendering in a form field or UI. Here, it simply returns the pet type’s name. This straightforward approach ensures that the UI shows meaningful names instead of object references or IDs.

```java
	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}
```

---

</SwmSnippet>

# how parsing works

Parsing is the reverse operation: converting a string back into a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken> object. The formatter uses the injected <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="39:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> to retrieve all available pet types. It then searches for a pet type whose name matches the input string exactly. If found, it returns that <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken> instance; otherwise, it throws a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="52:19:19" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`ParseException`</SwmToken>.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="39">

---

This approach ensures that only valid pet types recognized by the system are accepted during form submission or data binding. It also centralizes pet type retrieval in the repository, avoiding duplication of pet type data.

```java
	private final OwnerRepository owners;

	@Autowired
	public PetTypeFormatter(OwnerRepository owners) {
		this.owners = owners;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="51">

---

&nbsp;

```java
	@Override
	public PetType parse(String text, Locale locale) throws ParseException {
		Collection<PetType> findPetTypes = this.owners.findPetTypes();
		for (PetType type : findPetTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
