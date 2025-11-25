---
title: Vet Entity and Specialty Management
---
# Introduction

This document explains the design and implementation choices behind the Vet entity and its management of specialties in the file <SwmPath>[src/…/vet/Vet.java](src/main/java/org/springframework/samples/petclinic/vet/Vet.java)</SwmPath>.

We will cover:

1. Why Vet extends Person and is annotated as an entity.
2. How specialties are modeled and linked to Vet.
3. How specialties are accessed and managed internally and externally.

# Vet as an entity extending Person

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="36">

---

The Vet class extends the Person class, inheriting common attributes like name and contact details. It is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="44:0:1" line-data="@Entity">`@Entity`</SwmToken> and mapped to the "vets" table, making it a JPA entity that can be persisted in the database. This design keeps Vet focused on veterinarian-specific data while reusing Person's basic properties.

```java
/**
 * Simple JavaBean domain object representing a veterinarian.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Arjen Poutsma
 */
@Entity
@Table(name = "vets")
public class Vet extends Person {
```

---

</SwmSnippet>

# modeling specialties with many-to-many relationship

Specialties are represented as a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="51:3:6" line-data="	private Set&lt;Specialty&gt; specialties;">`Set<Specialty>`</SwmToken> inside Vet. The relationship is many-to-many because a vet can have multiple specialties, and each specialty can belong to multiple vets. This is mapped with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="48:1:11" line-data="	@ManyToMany(fetch = FetchType.EAGER)">`@ManyToMany(fetch = FetchType.EAGER)`</SwmToken> and a join table <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="49:9:9" line-data="	@JoinTable(name = &quot;vet_specialties&quot;, joinColumns = @JoinColumn(name = &quot;vet_id&quot;),">`vet_specialties`</SwmToken> that links vet IDs to specialty IDs.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="48">

---

Eager fetching ensures specialties are loaded immediately with the vet, which is useful since specialties are often needed alongside vet data.

```java
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
			inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Specialty> specialties;
```

---

</SwmSnippet>

# internal management of specialties

The specialties set is encapsulated with internal getter and setter methods (<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="53:8:10" line-data="	protected Set&lt;Specialty&gt; getSpecialtiesInternal() {">`getSpecialtiesInternal()`</SwmToken> and `setSpecialtiesInternal()`). The internal getter lazily initializes the set if null, preventing null pointer issues and ensuring the collection is always ready for use.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="53">

---

This internal encapsulation separates direct data access from the public API, allowing controlled manipulation.

```java
	protected Set<Specialty> getSpecialtiesInternal() {
		if (this.specialties == null) {
			this.specialties = new HashSet<>();
		}
		return this.specialties;
	}

	protected void setSpecialtiesInternal(Set<Specialty> specialties) {
		this.specialties = specialties;
	}
```

---

</SwmSnippet>

# public access and sorting of specialties

The public method <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="65:8:10" line-data="	public List&lt;Specialty&gt; getSpecialties() {">`getSpecialties()`</SwmToken> returns a sorted, unmodifiable list of specialties. It copies the internal set into a list, sorts it by specialty name using Spring's <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="67:1:1" line-data="		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition(&quot;name&quot;, true, true));">`PropertyComparator`</SwmToken>, and wraps it with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="68:3:5" line-data="		return Collections.unmodifiableList(sortedSpecs);">`Collections.unmodifiableList`</SwmToken> to prevent external modification.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="64">

---

This approach provides a stable, read-only view of specialties in a predictable order, which is important for UI display or API responses.

```java
	@XmlElement
	public List<Specialty> getSpecialties() {
		List<Specialty> sortedSpecs = new ArrayList<>(getSpecialtiesInternal());
		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedSpecs);
	}
```

---

</SwmSnippet>

# adding specialties

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="75">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="75:5:10" line-data="	public void addSpecialty(Specialty specialty) {">`addSpecialty(Specialty specialty)`</SwmToken> method adds a specialty to the internal set. This method is the intended way to modify the specialties collection, maintaining encapsulation and ensuring the internal set is properly managed.

```java
	public void addSpecialty(Specialty specialty) {
		getSpecialtiesInternal().add(specialty);
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
