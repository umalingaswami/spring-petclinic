---
title: Vet Entity Class
---
# Introduction

This document explains the design and implementation choices behind the Vet entity class. We will cover:

1. Why Vet extends Person and how it models a veterinarian.
2. How specialties are represented and managed within Vet.
3. The rationale for using internal and external accessors for specialties.
4. How sorting and immutability are handled when exposing specialties.

# Vet as a domain entity extending Person

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="36">

---

Vet is a JPA entity mapped to the "vets" table. It extends the Person class, inheriting common attributes like name and contact details. This inheritance models the domain concept that a veterinarian is a specialized type of person, avoiding duplication of common fields and behavior. The @Entity and @Table annotations enable ORM mapping for persistence.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="48">

---

A vet can have multiple specialties, and each specialty can belong to multiple vets. This is modeled as a many-to-many relationship using a join table <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="49:9:9" line-data="	@JoinTable(name = &quot;vet_specialties&quot;, joinColumns = @JoinColumn(name = &quot;vet_id&quot;),">`vet_specialties`</SwmToken>. The specialties field is a Set to avoid duplicates and is eagerly fetched to ensure specialties are available whenever a Vet is loaded. This design reflects the domain accurately and supports efficient queries.

```java
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
			inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Specialty> specialties;
```

---

</SwmSnippet>

# internal vs external accessors for specialties

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="53">

---

The class uses protected internal getters and setters for the specialties Set. These methods lazily initialize the Set if null, ensuring the collection is always ready for modification internally. This encapsulation prevents external code from directly manipulating the internal Set, which could break invariants or persistence expectations.

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

# exposing specialties as sorted, immutable list

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="64">

---

The public <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="65:8:10" line-data="	public List&lt;Specialty&gt; getSpecialties() {">`getSpecialties()`</SwmToken> method returns a sorted, unmodifiable List of specialties. Sorting by name ensures consistent order for UI or API consumers. Wrapping the list as unmodifiable prevents external code from altering the collection directly, preserving encapsulation and data integrity. The use of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="67:1:1" line-data="		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition(&quot;name&quot;, true, true));">`PropertyComparator`</SwmToken> with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="67:10:10" line-data="		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition(&quot;name&quot;, true, true));">`MutableSortDefinition`</SwmToken> handles sorting by the "name" property.

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

# utility methods for specialties

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="71">

---

Additional convenience methods include <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="71:5:7" line-data="	public int getNrOfSpecialties() {">`getNrOfSpecialties()`</SwmToken>, which returns the count of specialties, and addSpecialty(), which adds a specialty to the internal Set. These methods simplify client code and maintain control over how specialties are modified.

```java
	public int getNrOfSpecialties() {
		return getSpecialtiesInternal().size();
	}

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
