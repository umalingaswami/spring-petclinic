---
title: Owner Entity Class
---
# introduction

This document explains the design and key implementation choices behind the Owner entity class in the petclinic project. The Owner class models a pet owner and manages their personal details and pets.

We will cover:

1. How the Owner entity is structured and mapped to the database.
2. How pets are associated with an owner and managed.
3. How pet retrieval by name or ID is implemented.
4. How visits are added to a pet owned by the owner.

# entity structure and database mapping

The Owner class extends a base Person class, inheriting common person attributes. It is annotated as a JPA entity mapped to the "owners" table. The class adds fields for address, city, and telephone, all marked as not empty to enforce validation constraints. Telephone also has a digit-only constraint with a max length of 10.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="36">

---

Pets are modeled as a one-to-many relationship with cascade-all and eager fetching. The pets list is joined by the foreign key <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="63:9:9" line-data="	@JoinColumn(name = &quot;owner_id&quot;)">`owner_id`</SwmToken> and ordered by pet name. This setup ensures that when an Owner is loaded, their pets are loaded immediately and changes to pets cascade with the owner.

```java
/**
 * Simple JavaBean domain object representing an owner.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Oliver Drotbohm
 */
@Entity
@Table(name = "owners")
public class Owner extends Person {

	@Column(name = "address")
	@NotEmpty
	private String address;

	@Column(name = "city")
	@NotEmpty
	private String city;

	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "owner_id")
	@OrderBy("name")
	private List<Pet> pets = new ArrayList<>();
```

---

</SwmSnippet>

# managing pets collection

The pets list is initialized as an empty <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="65:14:14" line-data="	private List&lt;Pet&gt; pets = new ArrayList&lt;&gt;();">`ArrayList`</SwmToken>. The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="95:5:5" line-data="	public void addPet(Pet pet) {">`addPet`</SwmToken> method only adds a pet if it is new (not yet persisted), preventing duplicates or re-adding existing pets. This guards against inconsistent state in the pets collection.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="91">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="91:8:8" line-data="	public List&lt;Pet&gt; getPets() {">`getPets`</SwmToken> method exposes the list for read access.

```java
	public List<Pet> getPets() {
		return this.pets;
	}

	public void addPet(Pet pet) {
		if (pet.isNew()) {
			getPets().add(pet);
		}
	}
```

---

</SwmSnippet>

# retrieving pets by name or id

Two overloaded <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="106:5:5" line-data="	public Pet getPet(String name) {">`getPet`</SwmToken> methods allow fetching a pet by name or by ID.

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="106:5:5" line-data="	public Pet getPet(String name) {">`getPet`</SwmToken>(String name, boolean <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="132:14:14" line-data="	public Pet getPet(String name, boolean ignoreNew) {">`ignoreNew`</SwmToken>) method searches the pets list case-insensitively. It can optionally ignore pets that are new (not persisted). This is useful to avoid conflicts with pets not yet saved.

The simpler <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="106:5:5" line-data="	public Pet getPet(String name) {">`getPet`</SwmToken>(String name) calls the above with <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="132:14:14" line-data="	public Pet getPet(String name, boolean ignoreNew) {">`ignoreNew`</SwmToken>=false.

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="106:5:5" line-data="	public Pet getPet(String name) {">`getPet`</SwmToken>(Integer id) method iterates pets and returns the one matching the given ID, skipping new pets.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="101">

---

These methods provide flexible ways to find pets owned by this owner.

```java
	/**
	 * Return the Pet with the given name, or null if none found for this Owner.
	 * @param name to test
	 * @return a pet if pet name is already in use
	 */
	public Pet getPet(String name) {
		return getPet(name, false);
	}

	/**
	 * Return the Pet with the given id, or null if none found for this Owner.
	 * @param name to test
	 * @return a pet if pet id is already in use
	 */
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

	/**
	 * Return the Pet with the given name, or null if none found for this Owner.
	 * @param name to test
	 * @return a pet if pet name is already in use
	 */
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

# adding visits to a pet

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="159:5:5" line-data="	public Owner addVisit(Integer petId, Visit visit) {">`addVisit`</SwmToken> method associates a Visit object with a pet owned by this owner. It requires the pet ID and the visit to be non-null, asserting these conditions explicitly.

It retrieves the pet by ID, asserts the pet exists, then delegates to the pet's <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="159:5:5" line-data="	public Owner addVisit(Integer petId, Visit visit) {">`addVisit`</SwmToken> method. Finally, it returns the owner instance for possible chaining.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="154">

---

This method encapsulates the logic of adding visits within the owner context, ensuring data integrity.

```java
	/**
	 * Adds the given {@link Visit} to the {@link Pet} with the given identifier.
	 * @param petId the identifier of the {@link Pet}, must not be {@literal null}.
	 * @param visit the visit to add, must not be {@literal null}.
	 */
	public Owner addVisit(Integer petId, Visit visit) {

		Assert.notNull(petId, "Pet identifier must not be null!");
		Assert.notNull(visit, "Visit must not be null!");

		Pet pet = getPet(petId);

		Assert.notNull(pet, "Invalid Pet identifier!");

		pet.addVisit(visit);

		return this;
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
