---
title: The Pet class
---
This document explains the Pet class. We will cover:

1. What is Pet
2. Variables and functions

# What is Pet

The Pet class represents a pet entity in the system. It is a business object used to model pets owned by users in the application. It extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="24:12:12" line-data="import org.springframework.samples.petclinic.model.NamedEntity;">`NamedEntity`</SwmToken>, inheriting an identifier and a name, and adds specific attributes related to pets such as birth date, type, and visits. This class is annotated as a JPA entity mapped to the "pets" table in the database, enabling persistence of pet data.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="60">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="60:5:5" line-data="	public void setBirthDate(LocalDate birthDate) {">`setBirthDate`</SwmToken> sets the birth date of the pet. It takes a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="60:7:7" line-data="	public void setBirthDate(LocalDate birthDate) {">`LocalDate`</SwmToken> object as a parameter and assigns it to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="60:9:9" line-data="	public void setBirthDate(LocalDate birthDate) {">`birthDate`</SwmToken> variable.

```java
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="64">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="64:5:5" line-data="	public LocalDate getBirthDate() {">`getBirthDate`</SwmToken> returns the birth date of the pet as a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="64:3:3" line-data="	public LocalDate getBirthDate() {">`LocalDate`</SwmToken> object. It provides access to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="65:5:5" line-data="		return this.birthDate;">`birthDate`</SwmToken> variable.

```java
	public LocalDate getBirthDate() {
		return this.birthDate;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="68">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="68:5:5" line-data="	public PetType getType() {">`getType`</SwmToken> returns the type of the pet, which is an instance of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="68:3:3" line-data="	public PetType getType() {">`PetType`</SwmToken>. This function provides access to the type variable representing the pet's species or category.

```java
	public PetType getType() {
		return this.type;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="72">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="72:5:5" line-data="	public void setType(PetType type) {">`setType`</SwmToken> sets the type of the pet. It takes a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="72:7:7" line-data="	public void setType(PetType type) {">`PetType`</SwmToken> object as a parameter and assigns it to the type variable, defining the pet's species or category.

```java
	public void setType(PetType type) {
		this.type = type;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="76">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="76:8:8" line-data="	public Collection&lt;Visit&gt; getVisits() {">`getVisits`</SwmToken> returns a collection of Visit objects associated with the pet. This collection represents all the visits or appointments the pet has had, and it is stored as a Set to maintain uniqueness and order.

```java
	public Collection<Visit> getVisits() {
		return this.visits;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="80">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="80:5:5" line-data="	public void addVisit(Visit visit) {">`addVisit`</SwmToken> adds a Visit object to the pet's visits collection. It allows adding new visits to the pet's history, maintaining the relationship between the pet and its visits.

```java
	public void addVisit(Visit visit) {
		getVisits().add(visit);
	}
```

---

</SwmSnippet>

# Usage

## Owner

The Pet class is used within the Owner class to represent the pets owned by a particular owner. The Owner class maintains a list of Pet instances, allowing it to add new pets, retrieve pets by name, and manage the collection of pets associated with the owner. This relationship is managed with JPA annotations to handle database persistence and ordering.

## PetController

In the PetController, Pet instances are created, retrieved, and updated as part of handling web requests. The controller methods initialize forms for creating new pets, process form submissions, and load existing pets for editing. It uses the Owner class to associate pets with their owners and ensures that pet data is correctly managed during HTTP request handling.

## PetValidator

The PetValidator class is responsible for validating Pet instances. It checks properties such as the pet's name to ensure they meet certain criteria before the pet data is processed or persisted. This validation helps maintain data integrity and user input correctness.

## VisitController

The VisitController uses Pet instances to load pet data when managing visits. It ensures that the pet object is fresh and correctly associated with the owner before processing visit-related requests. This integration highlights how Pet objects are central to various aspects of the application, including visit management.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
