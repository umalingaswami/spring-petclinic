---
title: Person Entity Class
---
# Introduction

This document explains the design and implementation choices behind the Person entity class in the Petclinic model. We will cover:

1. Why Person is a @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken> and how it fits in the inheritance hierarchy.
2. How the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken> fields are defined and validated.
3. The role of getter and setter methods for these fields.

# why Person is a mapped superclass

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="18">

---

Person extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="28:8:8" line-data="public class Person extends BaseEntity {">`BaseEntity`</SwmToken> and is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken>. This means Person itself is not a standalone entity but provides common persistent state and behavior for its subclasses. This design avoids duplicating <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken> fields in multiple entity classes that represent different types of people (e.g., Owner, Vet). It centralizes shared attributes and enforces consistent mapping and validation rules across subclasses.

```java
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;

/**
 * Simple JavaBean domain object representing an person.
 *
 * @author Ken Krebs
 */
@MappedSuperclass
public class Person extends BaseEntity {
```

---

</SwmSnippet>

# defining and validating person attributes

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="30">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken> fields are mapped to database columns <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="30:9:9" line-data="	@Column(name = &quot;first_name&quot;)">`first_name`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="34:9:9" line-data="	@Column(name = &quot;last_name&quot;)">`last_name`</SwmToken> respectively. Both fields are annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="31:2:2" line-data="	@NotEmpty">`NotEmpty`</SwmToken>, enforcing that these values must be provided and cannot be blank. This validation happens before persistence, ensuring data integrity at the model level rather than relying solely on database constraints.

```java
	@Column(name = "first_name")
	@NotEmpty
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty
	private String lastName;
```

---

</SwmSnippet>

# encapsulating fields with getters and setters

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="38">

---

Person exposes standard getter and setter methods for <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="39:5:5" line-data="		return this.firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="47:5:5" line-data="		return this.lastName;">`lastName`</SwmToken>. These methods provide controlled access to the fields, allowing frameworks and other code to interact with the entity properties while preserving encapsulation. This pattern supports proxying, lazy loading, and other JPA features that rely on property access.

```java
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
