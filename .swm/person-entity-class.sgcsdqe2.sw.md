---
title: Person Entity Class
---
# Introduction

This document explains the design and implementation choices behind the Person entity class in the model layer of the application. We will cover:

1. Why Person is a @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken> instead of a regular entity.
2. How the class handles first and last names with validation.
3. The role of getters and setters in this class.

# why Person is a mapped superclass

Person is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken>, which means it is not a standalone entity but a base class for other entities. This design allows sharing common properties like <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken> across multiple subclasses without duplicating code or database mappings. It also inherits from <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="28:8:8" line-data="public class Person extends BaseEntity {">`BaseEntity`</SwmToken>, which likely provides common entity features such as an ID.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="18">

---

This approach keeps the domain model DRY and consistent, as subclasses can extend Person and automatically get these fields mapped to database columns.

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

# handling first and last names with validation

The class defines two fields: <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken>. Both are annotated with @Column to specify their database column names explicitly. They also have @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="20:8:8" line-data="import jakarta.validation.constraints.NotEmpty;">`NotEmpty`</SwmToken> validation annotations to enforce that these fields cannot be empty when persisted or validated.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="30">

---

This ensures data integrity at the model level, preventing invalid Person instances with missing names.

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

# getters and setters for encapsulation

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="38">

---

Person provides standard getter and setter methods for <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="39:5:5" line-data="		return this.firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="47:5:5" line-data="		return this.lastName;">`lastName`</SwmToken>. These methods allow controlled access and modification of the fields, which is a common <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="23:5:5" line-data=" * Simple JavaBean domain object representing an person.">`JavaBean`</SwmToken> pattern. This encapsulation supports frameworks and libraries that rely on property accessors for binding, serialization, or ORM operations.

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
