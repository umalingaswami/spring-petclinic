---
title: Visit Entity Class
---
# Introduction

This document explains the design and implementation choices behind the Visit entity class in the petclinic project. We will cover:

1. Why Visit extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="36:8:8" line-data="public class Visit extends BaseEntity {">`BaseEntity`</SwmToken> and how it maps to the database.
2. How the date field is handled and why it defaults to the current date.
3. The role of the description field and its validation.

# entity mapping and inheritance

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="28">

---

Visit is marked as a JPA entity with @Entity and mapped to the "visits" table using @Table. Extending <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="36:8:8" line-data="public class Visit extends BaseEntity {">`BaseEntity`</SwmToken> means Visit inherits an id property and common persistence behavior, which avoids repeating boilerplate code for entity identity.

```java
/**
 * Simple JavaBean domain object representing a visit.
 *
 * @author Ken Krebs
 * @author Dave Syer
 */
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {
```

---

</SwmSnippet>

# date field handling

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="38">

---

The date field stores when the visit occurred. It uses <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="40:3:3" line-data="	private LocalDate date;">`LocalDate`</SwmToken> for date-only values without time. The @Column annotation maps it to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="38:9:9" line-data="	@Column(name = &quot;visit_date&quot;)">`visit_date`</SwmToken> column in the database. The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="39:2:2" line-data="	@DateTimeFormat(pattern = &quot;yyyy-MM-dd&quot;)">`DateTimeFormat`</SwmToken> annotation specifies the expected date pattern for formatting and parsing, which helps with data binding in the UI or REST layers. The constructor initializes date to the current date by default, so new Visit instances represent visits happening "now" unless explicitly set otherwise.

```java
	@Column(name = "visit_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@NotEmpty
	private String description;

	/**
	 * Creates a new instance of Visit for the current date
	 */
	public Visit() {
		this.date = LocalDate.now();
	}
```

---

</SwmSnippet>

# description field and validation

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="42">

---

The description field holds details about the visit. It is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="42:2:2" line-data="	@NotEmpty">`NotEmpty`</SwmToken> to enforce that every visit must have a non-empty description. This validation ensures meaningful data is stored and prevents empty visit records.

```java
	@NotEmpty
	private String description;

	/**
	 * Creates a new instance of Visit for the current date
	 */
	public Visit() {
		this.date = LocalDate.now();
	}
```

---

</SwmSnippet>

# getters and setters

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="52">

---

Standard getters and setters provide access to the date and description fields. These methods allow frameworks and other parts of the application to read and modify Visit properties while keeping fields private.

```java
	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
