---
title: Visit Entity Class
---
# introduction

This document explains the design and implementation choices behind the Visit entity class in the petclinic project. The Visit class models a visit event for a pet, capturing essential data about the visit.

We will cover:

1. Why Visit extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="36:8:8" line-data="public class Visit extends BaseEntity {">`BaseEntity`</SwmToken> and how it maps to the database.
2. How the date of the visit is handled and formatted.
3. The role of the description field and its validation.
4. The rationale behind the default constructor setting the visit date.

# entity design and database mapping

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="28">

---

Visit extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="36:8:8" line-data="public class Visit extends BaseEntity {">`BaseEntity`</SwmToken>, which provides a common identifier for all entities in the system. This inheritance ensures Visit objects have a unique ID managed by JPA. The class is annotated with @Entity and @Table(name = "visits") to map it to the "visits" table in the database. This setup integrates Visit into the persistence layer, allowing CRUD operations through JPA repositories.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="38">

---

The visit date is stored as a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="40:3:3" line-data="	private LocalDate date;">`LocalDate`</SwmToken>, which represents a date without time or timezone. This choice fits the domain since visits are recorded by day, not by exact time. The date field is annotated with @Column(name = <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="38:9:9" line-data="	@Column(name = &quot;visit_date&quot;)">`visit_date`</SwmToken>) to map it to the corresponding database column. The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="39:2:2" line-data="	@DateTimeFormat(pattern = &quot;yyyy-MM-dd&quot;)">`DateTimeFormat`</SwmToken>(pattern = <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="39:9:13" line-data="	@DateTimeFormat(pattern = &quot;yyyy-MM-dd&quot;)">`yyyy-MM-dd`</SwmToken>) annotation ensures consistent formatting when binding data in the web layer, especially for forms and JSON serialization.

```java
	@Column(name = "visit_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="42">

---

The default constructor initializes the date to the current date (<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="49:7:11" line-data="		this.date = LocalDate.now();">`LocalDate.now()`</SwmToken>). This design means that when a new Visit instance is created without specifying a date, it automatically assumes the visit is happening today. This reduces boilerplate and prevents null dates.

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

# description field and validation

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="42">

---

The description field holds textual details about the visit. It is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="42:2:2" line-data="	@NotEmpty">`NotEmpty`</SwmToken>, enforcing that every Visit must have a non-empty description. This validation is important to ensure meaningful data is captured and to prevent saving incomplete visit records.

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

The class provides standard getters and setters for the date and description fields. These methods allow other parts of the application to access and modify the visit data while keeping the fields encapsulated.

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
