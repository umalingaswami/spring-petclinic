---
title: The Visit class
---
This document will cover the Visit class. We will cover:

1. What is Visit
2. Variables and functions

# What is Visit

The Visit class represents a visit to the pet clinic. It is a simple <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="29:5:5" line-data=" * Simple JavaBean domain object representing a visit.">`JavaBean`</SwmToken> domain object used to model the details of a visit, such as the date and description of the visit. It extends from <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="21:12:12" line-data="import org.springframework.samples.petclinic.model.BaseEntity;">`BaseEntity`</SwmToken>, inheriting common entity properties. The class is annotated as an entity mapped to the "visits" table in the database, enabling persistence of visit data. It is primarily used to record and manage information about visits made by pets to the clinic.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="48">

---

The constructor <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="48:3:5" line-data="	public Visit() {">`Visit()`</SwmToken> creates a new instance of Visit and initializes the visit date to the current date.

```java
	public Visit() {
		this.date = LocalDate.now();
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="52">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="52:5:7" line-data="	public LocalDate getDate() {">`getDate()`</SwmToken> returns the date of the visit as a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="52:3:3" line-data="	public LocalDate getDate() {">`LocalDate`</SwmToken> object.

```java
	public LocalDate getDate() {
		return this.date;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="56">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="56:5:10" line-data="	public void setDate(LocalDate date) {">`setDate(LocalDate date)`</SwmToken> sets the date of the visit to the provided <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="56:7:7" line-data="	public void setDate(LocalDate date) {">`LocalDate`</SwmToken> value.

```java
	public void setDate(LocalDate date) {
		this.date = date;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="60">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="60:5:7" line-data="	public String getDescription() {">`getDescription()`</SwmToken> returns the description of the visit, which is a string detailing the purpose or notes of the visit.

```java
	public String getDescription() {
		return this.description;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Visit.java" line="64">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Visit.java" pos="64:5:10" line-data="	public void setDescription(String description) {">`setDescription(String description)`</SwmToken> sets the description of the visit to the provided string value.

```java
	public void setDescription(String description) {
		this.description = description;
	}
```

---

</SwmSnippet>

# Usage

## Visit in Pet

The Visit class is used within the Pet class to maintain a collection of visits related to a specific pet. This is implemented as a set of Visit objects, which are eagerly fetched and ordered by visit date. The Pet class provides methods to retrieve all visits and to add a new visit to this collection, enabling the management of a pet's visit history.

## Visit in VisitController

In the VisitController, the Visit class is used to handle web requests related to visits. A new Visit instance is created and associated with a pet when loading the visit form. The controller also processes form submissions for new visits, validating the Visit object and handling errors if any occur. This demonstrates how Visit objects are integrated into the web layer for user interaction and data input.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
