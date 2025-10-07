---
title: The Person class
---
This document will cover the class Person. We will cover:

1. What is Person
2. Variables and functions

# What is Person

Person is a simple <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="23:5:5" line-data=" * Simple JavaBean domain object representing an person.">`JavaBean`</SwmToken> domain object representing a person in the system. It serves as a base class for entities that require person-related information such as first and last names. It is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken>, indicating that it is not a complete entity by itself but provides mapping information for its subclasses. Person extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="28:8:8" line-data="public class Person extends BaseEntity {">`BaseEntity`</SwmToken>, inheriting common entity properties like an identifier.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="38">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="38:5:5" line-data="	public String getFirstName() {">`getFirstName`</SwmToken> returns the first name of the person. It provides access to the private field <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="39:5:5" line-data="		return this.firstName;">`firstName`</SwmToken>.

```java
	public String getFirstName() {
		return this.firstName;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="42">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="42:5:5" line-data="	public void setFirstName(String firstName) {">`setFirstName`</SwmToken> sets the first name of the person. It assigns the provided value to the private field <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="42:9:9" line-data="	public void setFirstName(String firstName) {">`firstName`</SwmToken>.

```java
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="46">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="46:5:5" line-data="	public String getLastName() {">`getLastName`</SwmToken> returns the last name of the person. It provides access to the private field <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="47:5:5" line-data="		return this.lastName;">`lastName`</SwmToken>.

```java
	public String getLastName() {
		return this.lastName;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="50">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="50:5:5" line-data="	public void setLastName(String lastName) {">`setLastName`</SwmToken> sets the last name of the person. It assigns the provided value to the private field <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="50:9:9" line-data="	public void setLastName(String lastName) {">`lastName`</SwmToken>.

```java
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="30">

---

The variable <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> stores the person's first name. It is a private string field annotated with @Column to map to the database column <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="30:9:9" line-data="	@Column(name = &quot;first_name&quot;)">`first_name`</SwmToken> and @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="31:2:2" line-data="	@NotEmpty">`NotEmpty`</SwmToken> to enforce that it cannot be empty.

```java
	@Column(name = "first_name")
	@NotEmpty
	private String firstName;
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="34">

---

The variable <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken> stores the person's last name. It is a private string field annotated with @Column to map to the database column <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="34:9:9" line-data="	@Column(name = &quot;last_name&quot;)">`last_name`</SwmToken> and @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="35:2:2" line-data="	@NotEmpty">`NotEmpty`</SwmToken> to enforce that it cannot be empty.

```java
	@Column(name = "last_name")
	@NotEmpty
	private String lastName;
```

---

</SwmSnippet>

# Usage

## Vet

The class Vet extends Person, inheriting its properties and adding specific attributes related to veterinarians. This allows Vet to reuse common person fields such as first name and last name while also defining additional relationships like specialties.

## Owner

The class Owner also extends Person, inheriting the basic person attributes and adding owner-specific details such as address and city. This design enables Owner to represent pet owners with all the common person information plus additional owner-related data.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
