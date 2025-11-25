---
title: Pet Entity Class Representing a Pet in the Pet Clinic Application
---
# introduction

This document explains the design and implementation choices behind the Pet entity class in the pet clinic application. The Pet class models a pet owned by a client and tracks its essential attributes and visits.

We will cover:

1. How the Pet class represents its core attributes and relationships.
2. Why certain JPA annotations and collections are used.
3. The purpose of key methods managing pet data.

# entity and table mapping

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="36">

---

The Pet class is annotated with @Entity and @Table(name = "pets") to map it to the "pets" table in the database. It extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="45:8:8" line-data="public class Pet extends NamedEntity {">`NamedEntity`</SwmToken>, inheriting an id and a name, which are common to all named domain objects in the app. This inheritance avoids duplicating common fields and aligns with the domain model.

```java
/**
 * Simple business object representing a pet.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
@Entity
@Table(name = "pets")
public class Pet extends NamedEntity {
```

---

</SwmSnippet>

# core attributes and relationships

The Pet class defines three main fields beyond the inherited name:

- <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="49:5:5" line-data="	private LocalDate birthDate;">`birthDate`</SwmToken>: stored as a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="49:3:3" line-data="	private LocalDate birthDate;">`LocalDate`</SwmToken> and formatted with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="48:2:2" line-data="	@DateTimeFormat(pattern = &quot;yyyy-MM-dd&quot;)">`DateTimeFormat`</SwmToken> for consistent date handling.
- type: a many-to-one relationship to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="53:3:3" line-data="	private PetType type;">`PetType`</SwmToken>, representing the species or breed of the pet.
- visits: a one-to-many relationship to Visit entities, representing the medical visits or checkups the pet has had.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="47">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="49:5:5" line-data="	private LocalDate birthDate;">`birthDate`</SwmToken> field is mapped to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="47:9:9" line-data="	@Column(name = &quot;birth_date&quot;)">`birth_date`</SwmToken> column. The type field uses @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="51:2:2" line-data="	@ManyToOne">`ManyToOne`</SwmToken> with a join column <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="52:9:9" line-data="	@JoinColumn(name = &quot;type_id&quot;)">`type_id`</SwmToken> to link to the pet type table. The visits collection is eagerly fetched and cascades all operations, meaning changes to a Pet propagate to its visits. Visits are ordered by <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="57:5:5" line-data="	@OrderBy(&quot;visit_date ASC&quot;)">`visit_date`</SwmToken> ascending to keep them chronologically sorted.

```java
	@Column(name = "birth_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType type;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pet_id")
	@OrderBy("visit_date ASC")
	private Set<Visit> visits = new LinkedHashSet<>();
```

---

</SwmSnippet>

# managing visits collection

The visits field is a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="58:14:14" line-data="	private Set&lt;Visit&gt; visits = new LinkedHashSet&lt;&gt;();">`LinkedHashSet`</SwmToken> to maintain insertion order and avoid duplicates. The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="80:5:5" line-data="	public void addVisit(Visit visit) {">`addVisit`</SwmToken>(Visit visit) method adds a visit to this set, encapsulating the collection modification and ensuring the internal structure is used consistently.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="76">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="76:8:10" line-data="	public Collection&lt;Visit&gt; getVisits() {">`getVisits()`</SwmToken> method returns the visits collection, allowing read access to the pet's visit history.

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

# getters and setters for attributes

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="60">

---

The Pet class provides standard getters and setters for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="60:9:9" line-data="	public void setBirthDate(LocalDate birthDate) {">`birthDate`</SwmToken> and type. These methods allow controlled access and modification of these fields, supporting frameworks and libraries that rely on JavaBeans conventions.

```java
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDate getBirthDate() {
		return this.birthDate;
	}

	public PetType getType() {
		return this.type;
	}

	public void setType(PetType type) {
		this.type = type;
	}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
