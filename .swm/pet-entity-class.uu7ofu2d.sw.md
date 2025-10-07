---
title: Pet Entity Class
---
# Introduction

This document explains the design and implementation choices behind the Pet entity class in the petclinic project. We will cover:

1. Why Pet extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="45:8:8" line-data="public class Pet extends NamedEntity {">`NamedEntity`</SwmToken> and how it models a pet.
2. How Pet's relationships with <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="53:3:3" line-data="	private PetType type;">`PetType`</SwmToken> and Visit are represented.
3. The handling of pet attributes like birth date.
4. The rationale behind the collection and management of visits.

# Pet as a domain entity

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="36">

---

Pet extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="45:8:8" line-data="public class Pet extends NamedEntity {">`NamedEntity`</SwmToken>, which provides an id and a name. This inheritance means each Pet has a unique identifier and a name, which are fundamental for identifying pets in the system. The class is annotated with @Entity and @Table(name = "pets"), linking it to the "pets" table in the database. This setup enables ORM mapping and persistence of Pet instances.

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

# modeling pet attributes

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="47">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="49:5:5" line-data="	private LocalDate birthDate;">`birthDate`</SwmToken> field stores the pet's date of birth. It uses <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="49:3:3" line-data="	private LocalDate birthDate;">`LocalDate`</SwmToken> for date-only values without time or timezone. The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="48:2:2" line-data="	@DateTimeFormat(pattern = &quot;yyyy-MM-dd&quot;)">`DateTimeFormat`</SwmToken> annotation specifies the expected date pattern for formatting and parsing, ensuring consistent input/output handling.

```java
	@Column(name = "birth_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
```

---

</SwmSnippet>

# representing pet type

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="51">

---

The type field models the pet's species or breed category. It is a many-to-one relationship to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="53:3:3" line-data="	private PetType type;">`PetType`</SwmToken>, meaning many pets can share the same type. The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="51:2:2" line-data="	@ManyToOne">`ManyToOne`</SwmToken> and @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="52:2:2" line-data="	@JoinColumn(name = &quot;type_id&quot;)">`JoinColumn`</SwmToken> annotations define this association and the foreign key column <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="52:9:9" line-data="	@JoinColumn(name = &quot;type_id&quot;)">`type_id`</SwmToken> in the "pets" table.

```java
	@ManyToOne
	@JoinColumn(name = "type_id")
	private PetType type;
```

---

</SwmSnippet>

# managing visits collection

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="55">

---

Visits are stored as a Set of Visit objects. The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="55:2:2" line-data="	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)">`OneToMany`</SwmToken> annotation with cascade ALL means that operations on Pet propagate to its visits (e.g., saving or deleting a pet affects its visits). <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="55:17:19" line-data="	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)">`FetchType.EAGER`</SwmToken> ensures visits are loaded immediately with the pet, which is useful when visit data is frequently needed alongside pet data. The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="56:2:2" line-data="	@JoinColumn(name = &quot;pet_id&quot;)">`JoinColumn`</SwmToken>(<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="56:9:9" line-data="	@JoinColumn(name = &quot;pet_id&quot;)">`pet_id`</SwmToken>) sets the foreign key in the visits table, and @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="57:2:2" line-data="	@OrderBy(&quot;visit_date ASC&quot;)">`OrderBy`</SwmToken>("<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="57:5:5" line-data="	@OrderBy(&quot;visit_date ASC&quot;)">`visit_date`</SwmToken> ASC") keeps visits sorted by date ascending.

```java
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pet_id")
	@OrderBy("visit_date ASC")
	private Set<Visit> visits = new LinkedHashSet<>();
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="80">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="80:5:5" line-data="	public void addVisit(Visit visit) {">`addVisit`</SwmToken> method encapsulates adding a visit to the pet's visits collection, ensuring the internal Set is used consistently.

```java
	public void addVisit(Visit visit) {
		getVisits().add(visit);
	}

}
```

---

</SwmSnippet>

# accessors for pet attributes and relationships

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="60">

---

The class provides standard getters and setters for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="60:9:9" line-data="	public void setBirthDate(LocalDate birthDate) {">`birthDate`</SwmToken> and type, allowing controlled access and modification of these fields.

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
