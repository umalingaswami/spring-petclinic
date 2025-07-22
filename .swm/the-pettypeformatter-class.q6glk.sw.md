---
title: The PetTypeFormatter class
---
This document will cover the class <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken>. We'll explore:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken> is and its purpose.
2. The variables and functions within <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken>.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken> is a class in the Spring PetClinic application that implements the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="36:8:11" line-data="public class PetTypeFormatter implements Formatter&lt;PetType&gt; {">`Formatter<PetType>`</SwmToken> interface. It is used to instruct Spring MVC on how to parse and print elements of type <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken>. This class provides a more modern approach compared to legacy <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="28:3:3" line-data=" * PropertyEditors. See the following links for more details: - The Spring ref doc:">`PropertyEditors`</SwmToken>, allowing for better formatting capabilities within the application.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="40">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken> function is the constructor of the class. It initializes the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:7:7" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`types`</SwmToken> variable with an instance of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:5:5" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeRepository`</SwmToken>, which is used to access pet type data.

```java
	public PetTypeFormatter(PetTypeRepository types) {
		this.types = types;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="44">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:5:5" line-data="	public String print(PetType petType, Locale locale) {">`print`</SwmToken> function is used to convert a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> object into a string representation, specifically returning the name of the pet type. This function is overridden from the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="18:8:8" line-data="import org.springframework.format.Formatter;">`Formatter`</SwmToken> interface.

```java
	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="49">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="50:5:5" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`parse`</SwmToken> function is responsible for converting a string into a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="50:3:3" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`PetType`</SwmToken> object. It searches through the available pet types and returns the matching type based on the name. If no match is found, it throws a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="50:19:19" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`ParseException`</SwmToken>.

```java
	@Override
	public PetType parse(String text, Locale locale) throws ParseException {
		Collection<PetType> findPetTypes = this.types.findPetTypes();
		for (PetType type : findPetTypes) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
	}
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken> class is a component that implements the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="18:8:8" line-data="import org.springframework.format.Formatter;">`Formatter`</SwmToken> interface for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> objects. It is constructed with a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:5:5" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeRepository`</SwmToken> instance, which it uses to format <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> objects.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
