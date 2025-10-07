---
title: The PetTypeFormatter class
---
This document explains the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:3:3" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`PetTypeFormatter`</SwmToken> class. We will cover:

1. What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:3:3" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`PetTypeFormatter`</SwmToken>
2. Variables and functions in <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:3:3" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`PetTypeFormatter`</SwmToken>

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:3:3" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`PetTypeFormatter`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:3:3" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`PetTypeFormatter`</SwmToken> is a Spring component that implements the Formatter interface for the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> class. It instructs Spring MVC on how to convert <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> objects to and from their String representations, which is useful for binding form inputs and displaying <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> values in the UI.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="41">

---

The constructor function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:3:3" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`PetTypeFormatter`</SwmToken> initializes the formatter with an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:5:5" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`OwnerRepository`</SwmToken> instance, which it uses to access available <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> objects for parsing.

```java
	@Autowired
	public PetTypeFormatter(OwnerRepository owners) {
		this.owners = owners;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="46">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:5:5" line-data="	public String print(PetType petType, Locale locale) {">`print`</SwmToken> converts a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> object to its String representation by returning the pet type's name. This is used when displaying <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> values in the UI.

```java
	@Override
	public String print(PetType petType, Locale locale) {
		return petType.getName();
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" line="51">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="52:5:5" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`parse`</SwmToken> converts a String to a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="52:3:3" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`PetType`</SwmToken> object by searching through all available PetTypes retrieved from the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:5:5" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`OwnerRepository`</SwmToken>. If a matching <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="52:3:3" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`PetType`</SwmToken> name is found, it returns that <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="52:3:3" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`PetType`</SwmToken>; otherwise, it throws a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="52:19:19" line-data="	public PetType parse(String text, Locale locale) throws ParseException {">`ParseException`</SwmToken> indicating the type was not found.

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
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:3:3" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`PetTypeFormatter`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:3:3" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`PetTypeFormatter`</SwmToken> is a Spring component that implements the Formatter interface for the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> class. It is constructed with an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:5:5" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`OwnerRepository`</SwmToken> dependency, which is injected via the constructor. This setup allows <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="42:3:3" line-data="	public PetTypeFormatter(OwnerRepository owners) {">`PetTypeFormatter`</SwmToken> to be used for formatting <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetTypeFormatter.java" pos="47:7:7" line-data="	public String print(PetType petType, Locale locale) {">`PetType`</SwmToken> objects, typically in data binding scenarios within Spring MVC.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
