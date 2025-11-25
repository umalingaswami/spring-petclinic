---
title: The Pet class
---
This document explains the Pet class. We will cover:

1. What is Pet
2. Variables and functions

# What is Pet

The Pet class represents a pet entity in the system. It is a business object used to model pets owned by owners in the application. The class extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="24:12:12" line-data="import org.springframework.samples.petclinic.model.NamedEntity;">`NamedEntity`</SwmToken>, inheriting an identifier and a name. It is annotated as a JPA entity mapped to the "pets" table in the database. The Pet class encapsulates information such as the pet's birth date, type, and visits, enabling the application to manage pet-related data effectively.

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

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="64:5:5" line-data="	public LocalDate getBirthDate() {">`getBirthDate`</SwmToken> returns the birth date of the pet as a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="64:3:3" line-data="	public LocalDate getBirthDate() {">`LocalDate`</SwmToken> object.

```java
	public LocalDate getBirthDate() {
		return this.birthDate;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="68">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="68:5:5" line-data="	public PetType getType() {">`getType`</SwmToken> returns the type of the pet, which is an instance of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="68:3:3" line-data="	public PetType getType() {">`PetType`</SwmToken> class representing the species or category of the pet.

```java
	public PetType getType() {
		return this.type;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="72">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="72:5:5" line-data="	public void setType(PetType type) {">`setType`</SwmToken> sets the type of the pet. It takes a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="72:7:7" line-data="	public void setType(PetType type) {">`PetType`</SwmToken> object as a parameter and assigns it to the type variable.

```java
	public void setType(PetType type) {
		this.type = type;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="76">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="76:8:8" line-data="	public Collection&lt;Visit&gt; getVisits() {">`getVisits`</SwmToken> returns a collection of Visit objects associated with the pet. These visits represent appointments or check-ups the pet has had.

```java
	public Collection<Visit> getVisits() {
		return this.visits;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Pet.java" line="80">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Pet.java" pos="80:5:5" line-data="	public void addVisit(Visit visit) {">`addVisit`</SwmToken> adds a Visit object to the pet's visits collection. This allows recording a new visit for the pet.

```java
	public void addVisit(Visit visit) {
		getVisits().add(visit);
	}
```

---

</SwmSnippet>

# Usage

## Owner

The Pet class is used within the Owner class to represent the pets owned by a particular owner. The Owner class maintains a list of Pet objects, allowing it to add new pets, retrieve the list of pets, and find a pet by its name. This relationship is managed with JPA annotations to handle database persistence and fetching strategies.

## PetController

The PetController class uses the Pet class to handle web requests related to pet creation, updating, and retrieval. It provides methods to initialize forms for creating or updating pets, process form submissions, and find pets by their identifiers. The controller interacts with the Owner class to associate pets with their owners and ensures validation such as checking for duplicate pet names.

## VisitController

In the VisitController, the Pet class is used to load pet data along with visits for a given owner and pet ID. This ensures that the pet object is always available and up-to-date when handling visit-related requests, facilitating the management of visits associated with a specific pet.

## PetValidator

The PetValidator class validates Pet instances, focusing on properties such as the pet's name. It ensures that the pet data meets certain criteria before being processed or persisted, helping maintain data integrity and correctness.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
