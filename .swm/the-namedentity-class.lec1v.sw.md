---
title: The NamedEntity class
---
This document explains the class <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken>. We will cover:

1. What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken>
2. Variables and functions in <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken>

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> is a Java class in the model package that extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="22:24:24" line-data=" * Simple JavaBean domain object adds a name property to &lt;code&gt;BaseEntity&lt;/code&gt;. Used as">`BaseEntity`</SwmToken>. It serves as a base class for domain objects that require a name property. This class is annotated as a @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken>, meaning it provides mapping information for its subclasses but is not itself an entity. <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> encapsulates the common functionality of having a name attribute, which can be inherited by other domain entities in the application.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" line="34">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="34:5:5" line-data="	public String getName() {">`getName`</SwmToken> returns the value of the name property of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> instance. It allows other parts of the application to access the name of the entity.

```java
	public String getName() {
		return this.name;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" line="38">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="38:5:5" line-data="	public void setName(String name) {">`setName`</SwmToken> sets the value of the name property. It allows the name of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> instance to be modified.

```java
	public void setName(String name) {
		this.name = name;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" line="42">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="43:5:5" line-data="	public String toString() {">`toString`</SwmToken> overrides the default Object.toString method to return the name of the entity. This provides a meaningful string representation of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> instance, typically used for logging or debugging.

```java
	@Override
	public String toString() {
		return this.getName();
	}
```

---

</SwmSnippet>

# Usage

## Specialty

The Specialty class extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken>, inheriting its name property. This allows Specialty to represent a veterinary specialty with a name attribute, which is mapped to the 'specialties' table in the database.

## Pet

The Pet class also extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken>, gaining the name property. In addition to the inherited name, Pet defines other attributes such as birth date. It is mapped to the 'pets' table, representing individual pets with a name and other details.

## PetType

PetType extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> to represent different types of pets, such as dog or cat, with a name attribute. It is mapped to the 'types' table, leveraging the inherited name property for the type's designation.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
