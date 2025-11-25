---
title: The Vet class
---
This document explains the Vet class. We will cover:

1. What is Vet
2. Variables and functions in Vet

# What is Vet

The Vet class represents a veterinarian in the application domain. It extends the Person class, inheriting common person attributes such as name and contact details. The Vet class is used to model veterinarians who provide medical care to pets, and it manages the specialties that a veterinarian may have. This class is annotated as a JPA entity, mapping to the "vets" table in the database, enabling persistence of veterinarian data.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="53">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="53:8:8" line-data="	protected Set&lt;Specialty&gt; getSpecialtiesInternal() {">`getSpecialtiesInternal`</SwmToken> provides access to the internal set of specialties associated with the veterinarian. It ensures that the specialties set is initialized if it is null, returning a modifiable set of specialties.

```java
	protected Set<Specialty> getSpecialtiesInternal() {
		if (this.specialties == null) {
			this.specialties = new HashSet<>();
		}
		return this.specialties;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="60">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="60:5:5" line-data="	protected void setSpecialtiesInternal(Set&lt;Specialty&gt; specialties) {">`setSpecialtiesInternal`</SwmToken> sets the internal specialties set to the provided set. It is a protected setter used internally to update the specialties collection.

```java
	protected void setSpecialtiesInternal(Set<Specialty> specialties) {
		this.specialties = specialties;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="64">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="65:8:8" line-data="	public List&lt;Specialty&gt; getSpecialties() {">`getSpecialties`</SwmToken> returns a sorted, unmodifiable list of the veterinarian's specialties. It retrieves the internal specialties set, sorts it by the specialty name in ascending order, and returns it as an unmodifiable list to prevent external modification.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="71">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="71:5:5" line-data="	public int getNrOfSpecialties() {">`getNrOfSpecialties`</SwmToken> returns the number of specialties the veterinarian has. It simply returns the size of the internal specialties set.

```java
	public int getNrOfSpecialties() {
		return getSpecialtiesInternal().size();
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" line="75">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="75:5:5" line-data="	public void addSpecialty(Specialty specialty) {">`addSpecialty`</SwmToken> adds a new specialty to the veterinarian's internal specialties set. It allows dynamically associating a new specialty with the veterinarian.

```java
	public void addSpecialty(Specialty specialty) {
		getSpecialtiesInternal().add(specialty);
	}
```

---

</SwmSnippet>

# Usage

## Vet in Vets Wrapper

The class Vet is used within the Vets class, which acts as a container for a list of Vet instances. This wrapper class simplifies XML serialization and deserialization by encapsulating the list of vets, making it easier to handle collections of Vet objects in data transfer scenarios.

## Vet as a JPA Entity

Vet is defined as a JPA entity representing a veterinarian, extending from the Person class. It is mapped to the 'vets' table in the database and includes a many-to-many relationship with specialties, which are eagerly fetched. This setup allows Vet instances to be persisted and retrieved from the database with their associated specialties.

## Vet in VetController for Pagination

In the VetController, Vet instances are retrieved in a paginated manner from the repository. The controller wraps the paginated list of Vet objects into a Vets instance before adding it to the model. This approach supports efficient pagination of vet data on the web interface, providing attributes such as current page, total pages, and total items to the view layer.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
