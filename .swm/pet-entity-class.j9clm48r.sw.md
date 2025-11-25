---
title: Pet Entity Class
---
# introduction

This document explains the design and implementation choices behind the Pet entity class in the application. The Pet class models the core attributes and relationships of a pet in the system.

We will cover:

1. How the Pet entity is mapped to the database and its inheritance structure.
2. How pet attributes like birth date and type are represented and managed.
3. How visits related to a pet are stored and accessed.

# entity mapping and inheritance

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="47">

---

The Pet class is annotated as a JPA entity mapped to the "pets" table. It extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="24:12:12" line-data="import org.springframework.samples.petclinic.model.NamedEntity;">`NamedEntity`</SwmToken>, which provides an ID and a name field common to all named domain objects. This inheritance avoids duplication of common properties across entities.

```java
	@Column(name = "birth_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
```

---

</SwmSnippet>

# pet attributes

The birth date is stored as a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="49:3:3" line-data="	private LocalDate birthDate;">`LocalDate`</SwmToken> and mapped to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="47:9:9" line-data="	@Column(name = &quot;birth_date&quot;)">`birth_date`</SwmToken> column. The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="48:2:2" line-data="	@DateTimeFormat(pattern = &quot;yyyy-MM-dd&quot;)">`DateTimeFormat`</SwmToken> annotation ensures consistent date formatting for input and output.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="51">

---

The pet type is a many-to-one relationship to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="53:3:3" line-data="	private PetType type;">`PetType`</SwmToken> entity, linked by the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="52:9:9" line-data="	@JoinColumn(name = &quot;type_id&quot;)">`type_id`</SwmToken> foreign key. This models the classification of pets (e.g., dog, cat) as a separate entity, allowing reuse and easier management.

```java
	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType type;
```

---

</SwmSnippet>

# visits collection

Visits are modeled as a one-to-many relationship with the Visit entity. The visits collection is eagerly fetched and cascades all operations, meaning changes to a pet propagate to its visits automatically.

The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="52:2:2" line-data="	@JoinColumn(name = &quot;type_id&quot;)">`JoinColumn`</SwmToken> on <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="56:9:9" line-data="	@JoinColumn(name = &quot;pet_id&quot;)">`pet_id`</SwmToken> establishes the foreign key in the visits table. The visits are ordered by visit date ascending, which simplifies retrieving visits in chronological order.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="55">

---

The visits are stored in a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="58:14:14" line-data="	private Set&lt;Visit&gt; visits = new LinkedHashSet&lt;&gt;();">`LinkedHashSet`</SwmToken> to maintain insertion order and prevent duplicates.

```java
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pet_id")
	@OrderBy("visit_date ASC")
	private Set<Visit> visits = new LinkedHashSet<>();
```

---

</SwmSnippet>

# managing visits

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="76:8:10" line-data="	public Collection&lt;Visit&gt; getVisits() {">`getVisits()`</SwmToken> method returns the collection of visits for external use.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="76">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="80:5:5" line-data="	public void addVisit(Visit visit) {">`addVisit`</SwmToken>(Visit visit) method encapsulates adding a visit to the pet's visits collection, ensuring the internal collection is used consistently.

```java
	public Collection<Visit> getVisits() {
		return this.visits;
	}

	public void addVisit(Visit visit) {
		getVisits().add(visit);
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
