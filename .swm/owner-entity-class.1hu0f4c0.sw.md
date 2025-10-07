---
title: Owner Entity Class
---
# introduction

This document explains the design and key implementation choices behind the Owner entity class in the petclinic project. We focus on:

1. How the Owner entity models its data and relationships.
2. How pets are managed within the Owner entity.
3. How visits are added to pets through the Owner.

# entity modeling and data fields

The Owner class extends a base Person class and is annotated as a JPA entity mapped to the "owners" table. It stores owner-specific data like address, city, and telephone, all marked as non-empty and with validation constraints where appropriate. This ensures data integrity at the entity level.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="36">

---

The pets owned by an Owner are represented as a list of Pet entities. This relationship is defined as a one-to-many association with cascade-all and eager fetching. The pets are joined by the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="63:9:9" line-data="	@JoinColumn(name = &quot;owner_id&quot;)">`owner_id`</SwmToken> foreign key and ordered by their name. This setup means that whenever an Owner is loaded, their pets are loaded immediately, and any changes to pets are cascaded automatically.

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

# managing pets within the owner

The Owner class provides methods to access and manipulate its pets. The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="91:8:10" line-data="	public List&lt;Pet&gt; getPets() {">`getPets()`</SwmToken> method returns the current list of pets.

Adding a pet is controlled by the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="95:5:5" line-data="	public void addPet(Pet pet) {">`addPet`</SwmToken> method, which only adds pets that are new (not yet persisted). This prevents duplicates or re-adding existing pets.

To retrieve a pet by name, the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="106:5:5" line-data="	public Pet getPet(String name) {">`getPet`</SwmToken>(String name, boolean <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="132:14:14" line-data="	public Pet getPet(String name, boolean ignoreNew) {">`ignoreNew`</SwmToken>) method searches the pet list case-insensitively. The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="132:14:14" line-data="	public Pet getPet(String name, boolean ignoreNew) {">`ignoreNew`</SwmToken> flag allows filtering out pets that are not yet persisted. There is also an overloaded <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="106:5:5" line-data="	public Pet getPet(String name) {">`getPet`</SwmToken>(String name) that defaults to including all pets.

Similarly, <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="106:5:5" line-data="	public Pet getPet(String name) {">`getPet`</SwmToken>(Integer id) retrieves a pet by its identifier, returning null if not found.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="91">

---

These methods encapsulate pet lookup logic inside the Owner, keeping pet management consistent and centralized.

```java
	public List<Pet> getPets() {
		return this.pets;
	}

	public void addPet(Pet pet) {
		if (pet.isNew()) {
			getPets().add(pet);
		}
	}

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

# adding visits to pets through owner

The Owner class also provides a method to add a Visit to one of its pets by pet ID. The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="159:5:5" line-data="	public Owner addVisit(Integer petId, Visit visit) {">`addVisit`</SwmToken>(Integer <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/Owner.java" pos="156:6:6" line-data="	 * @param petId the identifier of the {@link Pet}, must not be {@literal null}.">`petId`</SwmToken>, Visit visit) method first asserts that neither argument is null, then looks up the pet by ID. If the pet is found, it delegates the addition of the visit to the pet itself.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/Owner.java" line="154">

---

This design keeps the Owner as the entry point for modifying pet-related data, enforcing ownership boundaries and simplifying client code that works with owners and their pets.

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
