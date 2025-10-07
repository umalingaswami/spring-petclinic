---
title: Person Entity Class
---
# introduction

This document explains the design and implementation choices behind the Person entity class in the model layer of the application.

We will cover:

1. Why Person is a @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken> instead of a regular entity.
2. How <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken> fields are defined and validated.
3. The role of getter and setter methods for these fields.

# why use @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken> for Person

Person is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken>, which means it is not a standalone entity but a base class for other entities. This design allows sharing common properties like <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken> across multiple subclasses without duplicating code or database mappings. It also keeps the domain model clean by centralizing person-related attributes in one place.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="18">

---

This approach fits well when you want to reuse common fields but don't want Person itself to be persisted directly as a separate table.

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

# defining and validating name fields

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken> fields are mapped to database columns named <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="30:9:9" line-data="	@Column(name = &quot;first_name&quot;)">`first_name`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="34:9:9" line-data="	@Column(name = &quot;last_name&quot;)">`last_name`</SwmToken> respectively. Both fields are annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="20:8:8" line-data="import jakarta.validation.constraints.NotEmpty;">`NotEmpty`</SwmToken>, enforcing that these values cannot be null or empty strings. This validation ensures data integrity at the model level before persistence or business logic processing.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="30">

---

Using @Column explicitly sets the column names, which can be important for aligning with existing database schemas or naming conventions.

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

The class provides standard getter and setter methods for <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken>. These methods allow controlled access and modification of the fields, which is a common <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="23:5:5" line-data=" * Simple JavaBean domain object representing an person.">`JavaBean`</SwmToken> pattern. This encapsulation supports frameworks and libraries that rely on property accessors for data binding, serialization, or ORM operations.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="38">

---

The getters simply return the current value, while the setters assign new values to the fields.

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
