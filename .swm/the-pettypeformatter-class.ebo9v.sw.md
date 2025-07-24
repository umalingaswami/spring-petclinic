---
title: The PetTypeFormatter class
---
# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken> class is a component in the Spring PetClinic application that implements the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="18:8:8" line-data="import org.springframework.format.Formatter;">`Formatter`</SwmToken> interface for the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> entity. It is used to instruct Spring MVC on how to parse and print elements of type <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken>, providing a more modern approach compared to legacy <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="28:3:3" line-data=" * PropertyEditors. See the following links for more details: - The Spring ref doc:">`PropertyEditors`</SwmToken>.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="40">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken> constructor initializes the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:7:7" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`types`</SwmToken> variable, which is a reference to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:5:5" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeRepository`</SwmToken>. This repository is used to access pet type data from the data store.

```java
	public PetTypeFormatter(PetTypeRepository types) {
		this.types = types;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="44">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:5:5" line-data="	public String print(PetType petType, Locale locale) {">`print`</SwmToken> function is used to convert a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> object into a string representation, specifically returning the name of the pet type.

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

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="50:5:5" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`parse`</SwmToken> function takes a string and a locale as input and attempts to convert the string into a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="50:3:3" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`PetType`</SwmToken> object. It searches through the collection of pet types retrieved from the repository and matches the name to return the corresponding <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="50:3:3" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`PetType`</SwmToken> object. If no match is found, it throws a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="50:19:19" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`ParseException`</SwmToken>.

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

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken> class is utilized to format <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> objects within the application. It implements the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="18:8:8" line-data="import org.springframework.format.Formatter;">`Formatter`</SwmToken> interface, allowing it to convert <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> objects to and from their string representation. This functionality is essential for handling user input and displaying pet types in a user-friendly manner.

## Usage in <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:5:5" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeRepository`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:3:3" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeFormatter`</SwmToken> class is constructed with a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="40:5:5" line-data="	public PetTypeFormatter(PetTypeRepository types) {">`PetTypeRepository`</SwmToken> instance, which it uses to retrieve pet types from the database. This integration ensures that the formatter has access to the latest pet type data, enabling accurate conversion between <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="45:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> objects and their string representations.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
