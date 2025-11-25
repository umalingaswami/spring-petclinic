---
title: Person Entity Class
---
# Introduction

This document explains the design and implementation choices behind the Person entity class in the model layer of the application. We will cover:

1. Why Person is a @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken> instead of a regular entity.
2. How the class handles common person attributes.
3. The use of validation annotations on fields.
4. The role of getter and setter methods in this class.

# why Person is a mapped superclass

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="18">

---

Person is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken>, which means it is not a standalone entity but a base class for other entities. This design allows sharing common person-related fields and behavior without creating a separate database table for Person itself. Subclasses inherit these fields and map them to their own tables. This avoids duplication and keeps the domain model clean.

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

# common person attributes and validation

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="30">

---

The class defines two main attributes: <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="32:5:5" line-data="	private String firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="36:5:5" line-data="	private String lastName;">`lastName`</SwmToken>. Both are annotated with @Column to specify their database column names explicitly. They also have @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="31:2:2" line-data="	@NotEmpty">`NotEmpty`</SwmToken> validation annotations to enforce that these fields cannot be empty when persisted or validated. This ensures data integrity at the model level.

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

# encapsulation via getters and setters

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/Person.java" line="38">

---

Person provides standard getter and setter methods for <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="39:5:5" line-data="		return this.firstName;">`firstName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/Person.java" pos="47:5:5" line-data="		return this.lastName;">`lastName`</SwmToken>. These methods allow controlled access and modification of the fields. This encapsulation supports frameworks and libraries that rely on JavaBeans conventions for property access, such as JPA and data binding in Spring.

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
