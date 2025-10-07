---
title: The Owner class
---
This document covers the Owner class in the codebase. We will explain:

1. What the Owner class is and its purpose.
2. The variables and functions defined in the Owner class, with detailed explanations and code citations.

# What is Owner

The Owner class represents a pet owner in the application domain. It extends the Person class, inheriting personal details such as first and last names. The Owner class adds specific attributes related to the owner, such as address, city, telephone number, and a collection of pets owned. It serves as a domain model to manage owner-related data and operations within the system.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="67">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="67:5:5" line-data="	public String getAddress() {">`getAddress`</SwmToken> returns the address of the owner as a string.

```java
	public String getAddress() {
		return this.address;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="71">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="71:5:5" line-data="	public void setAddress(String address) {">`setAddress`</SwmToken> sets the address of the owner with the provided string value.

```java
	public void setAddress(String address) {
		this.address = address;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="75">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="75:5:5" line-data="	public String getCity() {">`getCity`</SwmToken> returns the city where the owner lives as a string.

```java
	public String getCity() {
		return this.city;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="79">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="79:5:5" line-data="	public void setCity(String city) {">`setCity`</SwmToken> sets the city of the owner with the provided string value.

```java
	public void setCity(String city) {
		this.city = city;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="83">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="83:5:5" line-data="	public String getTelephone() {">`getTelephone`</SwmToken> returns the telephone number of the owner as a string.

```java
	public String getTelephone() {
		return this.telephone;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="87">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="87:5:5" line-data="	public void setTelephone(String telephone) {">`setTelephone`</SwmToken> sets the telephone number of the owner with the provided string value.

```java
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="91">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="91:8:8" line-data="	public List&lt;Pet&gt; getPets() {">`getPets`</SwmToken> returns a list of Pet objects owned by the owner. It provides access to all pets associated with this owner.

```java
	public List<Pet> getPets() {
		return this.pets;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="95">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="95:5:5" line-data="	public void addPet(Pet pet) {">`addPet`</SwmToken> adds a new Pet to the owner's list of pets only if the pet is new (not yet persisted). This helps maintain the collection of pets owned.

```java
	public void addPet(Pet pet) {
		if (pet.isNew()) {
			getPets().add(pet);
		}
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="106">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="106:5:10" line-data="	public Pet getPet(String name) {">`getPet(String name)`</SwmToken> returns a Pet object with the given name if it exists among the owner's pets. It returns null if no pet with that name is found.

```java
	public Pet getPet(String name) {
		return getPet(name, false);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="115">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="115:5:10" line-data="	public Pet getPet(Integer id) {">`getPet(Integer id)`</SwmToken> returns a Pet object with the given identifier if it exists among the owner's pets. It returns null if no pet with that id is found.

```java
	public Pet getPet(Integer id) {
		for (Pet pet : getPets()) {
			if (!pet.isNew()) {
				Integer compId = pet.getId();
				if (compId.equals(id)) {
					return pet;
				}
			}
		}
		return null;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="132">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="132:5:15" line-data="	public Pet getPet(String name, boolean ignoreNew) {">`getPet(String name, boolean ignoreNew)`</SwmToken> returns a Pet object with the given name, optionally ignoring pets that are new (not persisted). It returns null if no matching pet is found.

```java
	public Pet getPet(String name, boolean ignoreNew) {
		name = name.toLowerCase();
		for (Pet pet : getPets()) {
			if (!ignoreNew || !pet.isNew()) {
				String compName = pet.getName();
				compName = compName == null ? "" : compName.toLowerCase();
				if (compName.equals(name)) {
					return pet;
				}
			}
		}
		return null;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="146">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="147:5:5" line-data="	public String toString() {">`toString`</SwmToken> overrides the default string representation of the Owner object. It uses <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="148:5:5" line-data="		return new ToStringCreator(this).append(&quot;id&quot;, this.getId()).append(&quot;new&quot;, this.isNew())">`ToStringCreator`</SwmToken> to include the owner's id, new status, last name, first name, address, city, and telephone in the string output.

```java
	@Override
	public String toString() {
		return new ToStringCreator(this).append("id", this.getId()).append("new", this.isNew())
				.append("lastName", this.getLastName()).append("firstName", this.getFirstName())
				.append("address", this.address).append("city", this.city).append("telephone", this.telephone)
				.toString();
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="159">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="159:5:5" line-data="	public Owner addVisit(Integer petId, Visit visit) {">`addVisit`</SwmToken> adds a Visit object to a Pet owned by this owner, identified by the pet's id. It validates that the pet id and visit are not null, retrieves the pet, and adds the visit to it, returning the Owner instance for chaining.

```java
	public Owner addVisit(Integer petId, Visit visit) {

		Assert.notNull(petId, "Pet identifier must not be null!");
		Assert.notNull(visit, "Visit must not be null!");

		Pet pet = getPet(petId);

		Assert.notNull(pet, "Invalid Pet identifier!");

		pet.addVisit(visit);

		return this;
	}
```

---

</SwmSnippet>

# Usage

## OwnerController

The OwnerController handles web requests related to owners. It uses the Owner class to create new owner instances, find existing owners by their ID, and manage form submissions for creating or updating owner information. For example, it initializes a new Owner object when rendering the creation form and processes the submitted Owner data, validating it before saving.

## OwnerRepository

OwnerRepository provides data access methods for Owner entities. It includes queries to find owners by last name and retrieve pet types. This interface abstracts the database operations related to Owner objects, enabling retrieval and persistence of owner data.

## PetController

PetController interacts with Owner instances to manage pets associated with owners. It retrieves an Owner by ID to add new pets, check for duplicate pet names, and update existing pets. The Owner class methods are used to add pets and fetch pets by their ID or name, linking pets to their respective owners.

## VisitController

VisitController uses the Owner class to load an owner and their pet before handling visit-related operations. It retrieves the Owner by ID, then accesses the pet through the Owner to prepare the model for visit creation or update. The Owner class facilitates the association between pets and their visits.

## Owner Methods

The Owner class includes methods such as <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="159:5:5" line-data="	public Owner addVisit(Integer petId, Visit visit) {">`addVisit`</SwmToken>, which adds a visit to a pet owned by the owner. This method ensures that the pet ID and visit are not null before associating the visit with the pet, demonstrating how Owner manages the relationship between pets and their visits.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
