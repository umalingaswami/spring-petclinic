---
title: Visit Entity Class
---
# Introduction

This document explains the design and implementation choices behind the Visit entity class in the petclinic project. We will cover:

1. Why Visit extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="36:8:8" line-data="public class Visit extends BaseEntity {">`BaseEntity`</SwmToken> and how it fits in the domain model.
2. How the date field is handled and why it defaults to the current date.
3. The role of the description field and its validation constraints.

# Visit as a domain entity

Visit is a JPA entity representing a pet's visit to the clinic. It extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="36:8:8" line-data="public class Visit extends BaseEntity {">`BaseEntity`</SwmToken>, which provides a common identifier and shared persistence behavior for all entities in the model. This inheritance keeps the domain model consistent and reduces boilerplate.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="28">

---

The class is annotated with @Entity and @Table(name = "visits") to map it to the visits table in the database. This setup enables ORM to handle CRUD operations automatically.

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

# handling the visit date

The visit date is stored as a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="40:3:3" line-data="	private LocalDate date;">`LocalDate`</SwmToken>, which captures the date without time or timezone details. This is appropriate because visits are scheduled by day, not by exact timestamp.

The date field is annotated with @Column(name = <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="38:9:9" line-data="	@Column(name = &quot;visit_date&quot;)">`visit_date`</SwmToken>) to map it to the corresponding database column, and @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="39:2:2" line-data="	@DateTimeFormat(pattern = &quot;yyyy-MM-dd&quot;)">`DateTimeFormat`</SwmToken>(pattern = <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="39:9:13" line-data="	@DateTimeFormat(pattern = &quot;yyyy-MM-dd&quot;)">`yyyy-MM-dd`</SwmToken>) to ensure consistent formatting when binding data in the UI or REST layers.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="38">

---

The constructor initializes the date to the current date by default. This design choice simplifies creating new visits by assuming they happen today unless specified otherwise.

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

	public LocalDate getDate() {
		return this.date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
```

---

</SwmSnippet>

# description field and validation

The description field holds details about the visit, such as symptoms or procedures. It is marked with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="42:2:2" line-data="	@NotEmpty">`NotEmpty`</SwmToken> to enforce that every visit must have a meaningful description before persisting.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="42">

---

This validation helps maintain data integrity and ensures that visits are not recorded without context.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="60">

---

&nbsp;

```java
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
