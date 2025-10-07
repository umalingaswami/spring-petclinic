---
title: 'PetTypeFormatter: Formatter for PetType Entities'
---
# Introduction

This document explains the rationale and key points behind the implementation of a formatter for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken> entities in the application. We will cover:

1. Why a formatter is used instead of legacy <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="29:3:3" line-data=" * PropertyEditors. See the following links for more details: - The Spring ref doc:">`PropertyEditors`</SwmToken>.
2. How the formatter converts <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken> objects to strings for display.
3. How the formatter parses strings back into <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken> objects using the repository.

# why use a formatter for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="26">

---

Spring MVC uses formatters to handle conversion between object types and their string representations, improving on the older PropertyEditor approach. This change aligns with Spring <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="28:7:9" line-data=" * from Spring 3.0, Formatters have come as an improvement in comparison to legacy">`3.0`</SwmToken>+ best practices, providing a cleaner and more flexible way to handle data binding and display formatting in web forms. The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="37:4:4" line-data="public class PetTypeFormatter implements Formatter&lt;PetType&gt; {">`PetTypeFormatter`</SwmToken> class implements the Formatter interface specifically for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken> entities, instructing Spring how to parse and print these objects.

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

# printing pet types as strings

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="46">

---

The print method defines how a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> object is converted to a string for display in the UI. Here, it simply returns the name of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken>. This is straightforward and ensures that wherever a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> needs to be shown as text (e.g., in dropdowns or labels), the user sees the pet type’s name.

```java
	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}
```

---

</SwmSnippet>

# parsing strings into pet types

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="39">

---

The parse method converts a string back into a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken> object. It does this by querying the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="39:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> for all available PetTypes and matching the input text against their names. If a match is found, it returns the corresponding <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken>. If not, it throws a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="52:19:19" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`ParseException`</SwmToken>. This approach ensures that only valid <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="27:28:28" line-data=" * Instructs Spring MVC on how to parse and print elements of type &#39;PetType&#39;. Starting">`PetType`</SwmToken> names are accepted and mapped correctly, relying on the repository as the source of truth.

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
