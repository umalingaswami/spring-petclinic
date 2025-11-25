---
title: Vet Entity Class
---
# Introduction

This document explains the design and implementation choices behind the Vet entity class in the application. We will cover:

1. Why Vet extends Person and how it models a veterinarian.
2. How specialties are represented and managed within Vet.
3. The rationale for using internal and external accessors for specialties.
4. How sorting and immutability are handled when exposing specialties.

# Vet as a domain entity extending Person

The Vet class is a JPA entity mapped to the "vets" table. It extends the Person class, inheriting common attributes like name and contact details. This inheritance models the domain concept that a veterinarian is a specialized type of person, reusing shared properties and behavior.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="36">

---

This design keeps the domain model clean and avoids duplication of common fields. The @Entity and @Table annotations declare Vet as a persistent entity linked to the vets table.

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

# managing specialties with a many-to-many relationship

A veterinarian can have multiple specialties, and each specialty can belong to multiple vets. This is modeled as a many-to-many relationship using JPA annotations. The specialties field is a Set to avoid duplicates and is eagerly fetched to ensure specialties are available whenever a Vet is loaded.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="48">

---

The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="49:2:2" line-data="	@JoinTable(name = &quot;vet_specialties&quot;, joinColumns = @JoinColumn(name = &quot;vet_id&quot;),">`JoinTable`</SwmToken> annotation specifies the join table <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="49:9:9" line-data="	@JoinTable(name = &quot;vet_specialties&quot;, joinColumns = @JoinColumn(name = &quot;vet_id&quot;),">`vet_specialties`</SwmToken> and the foreign keys linking vets and specialties. This explicit mapping controls the database schema and how the relationship is persisted.

```java
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
			inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Specialty> specialties;
```

---

</SwmSnippet>

# internal vs external accessors for specialties

The class uses protected internal getters and setters for the specialties Set. These internal methods lazily initialize the Set if null and provide direct mutable access to the underlying collection.

External access to specialties is provided through a public getter that returns a sorted, unmodifiable List. This separation encapsulates the internal mutable state while exposing a safe, read-only view to clients.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="53">

---

This pattern prevents external code from accidentally modifying the internal Set directly, enforcing better encapsulation and immutability guarantees.

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

# sorting and immutability in the public specialties getter

The public <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="65:8:8" line-data="	public List&lt;Specialty&gt; getSpecialties() {">`getSpecialties`</SwmToken> method converts the internal Set to a List, sorts it by specialty name using Spring's <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="67:1:1" line-data="		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition(&quot;name&quot;, true, true));">`PropertyComparator`</SwmToken>, and returns an unmodifiable list. This ensures that consumers always see specialties in a consistent order and cannot alter the collection.

The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="64:2:2" line-data="	@XmlElement">`XmlElement`</SwmToken> annotation indicates this method is used for XML serialization, so the sorted, immutable list is what gets exposed in API responses or data transfers.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="64">

---

This approach balances the need for a mutable internal model with a stable, safe external API.

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

Additional convenience methods include <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="71:5:5" line-data="	public int getNrOfSpecialties() {">`getNrOfSpecialties`</SwmToken>, which returns the count of specialties, and <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="75:5:5" line-data="	public void addSpecialty(Specialty specialty) {">`addSpecialty`</SwmToken>, which adds a new specialty to the internal Set.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="71">

---

These methods simplify client code by encapsulating common operations on the specialties collection.

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
