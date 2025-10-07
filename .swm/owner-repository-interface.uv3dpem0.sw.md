---
title: Owner Repository Interface
---
# introduction

This document explains the design and implementation of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> interface in the petclinic project. It answers these questions:

1. Why use an interface extending Spring Data's Repository instead of a concrete class?
2. How are queries defined and executed for Owner and related entities?
3. How does pagination and transaction management fit into the repository methods?

# repository interface and its purpose

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> interface extends Spring Data's Repository interface, parameterized with Owner and Integer (the entity type and its ID type). This design allows Spring Data to generate the implementation automatically based on method signatures and annotations. It avoids boilerplate code and leverages Spring Data's powerful query derivation and custom query support.

The interface declares methods for accessing and manipulating Owner entities and related <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="41:11:11" line-data="	 * Retrieve all {@link PetType}s from the data store.">`PetType`</SwmToken> entities. It does not extend CrudRepository or JpaRepository directly, which gives more control over which methods are exposed and how they behave.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="27">

---

This approach fits the domain-driven design by focusing on the Owner aggregate root and its related data access patterns.

```java
/**
 * Repository class for <code>Owner</code> domain objects All method names are compliant
 * with Spring Data naming conventions so this interface can easily be extended for Spring
 * Data. See:
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */
public interface OwnerRepository extends Repository<Owner, Integer> {
```

---

</SwmSnippet>

# query methods and custom queries

The repository defines several methods with custom JPQL queries using the @Query annotation. This allows precise control over the executed SQL and fetching strategies.

- <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="46:6:8" line-data="	List&lt;PetType&gt; findPetTypes();">`findPetTypes()`</SwmToken>: Retrieves all <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="41:11:11" line-data="	 * Retrieve all {@link PetType}s from the data store.">`PetType`</SwmToken> entities ordered by name. This is a read-only query to populate pet type options.
- <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="58:6:6" line-data="	Page&lt;Owner&gt; findByLastName(@Param(&quot;lastName&quot;) String lastName, Pageable pageable);">`findByLastName`</SwmToken>(String <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="56:29:29" line-data="	@Query(&quot;SELECT DISTINCT owner FROM Owner owner left join  owner.pets WHERE owner.lastName LIKE :lastName% &quot;)">`lastName`</SwmToken>, Pageable pageable): Finds owners whose last name starts with the given string. It uses a left join on pets to fetch associated pets eagerly and returns a paged result.
- <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="67:3:3" line-data="	Owner findById(@Param(&quot;id&quot;) Integer id);">`findById`</SwmToken>(Integer id): Retrieves an owner by ID, fetching pets eagerly to avoid lazy loading issues later.
- <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="80:6:6" line-data="	Page&lt;Owner&gt; findAll(Pageable pageable);">`findAll`</SwmToken>(Pageable pageable): Returns all owners in a paged fashion.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="40">

---

These queries use JPQL with joins and parameters to optimize data retrieval and avoid N+1 select problems.

```java
	/**
	 * Retrieve all {@link PetType}s from the data store.
	 * @return a Collection of {@link PetType}s.
	 */
	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
	@Transactional(readOnly = true)
	List<PetType> findPetTypes();
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="56">

---

&nbsp;

```java
	@Query("SELECT DISTINCT owner FROM Owner owner left join  owner.pets WHERE owner.lastName LIKE :lastName% ")
	@Transactional(readOnly = true)
	Page<Owner> findByLastName(@Param("lastName") String lastName, Pageable pageable);

	/**
	 * Retrieve an {@link Owner} from the data store by id.
	 * @param id the id to search for
	 * @return the {@link Owner} if found
	 */
	@Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
	@Transactional(readOnly = true)
	Owner findById(@Param("id") Integer id);
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="75">

---

&nbsp;

```java
	/**
	 * Returnes all the owners from data store
	 **/
	@Query("SELECT owner FROM Owner owner")
	@Transactional(readOnly = true)
	Page<Owner> findAll(Pageable pageable);

}
```

---

</SwmSnippet>

# transaction management and read-only optimization

Each query method is annotated with @Transactional(<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="45:4:4" line-data="	@Transactional(readOnly = true)">`readOnly`</SwmToken> = true). This tells Spring to open a read-only transaction for the method, which can improve performance by avoiding unnecessary locking or flush operations. It also clarifies the intent that these methods do not modify data.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="40">

---

The save(Owner owner) method is not annotated with @Transactional here because it is expected that the caller manages the transaction or that Spring Data handles it implicitly when implemented.

```java
	/**
	 * Retrieve all {@link PetType}s from the data store.
	 * @return a Collection of {@link PetType}s.
	 */
	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
	@Transactional(readOnly = true)
	List<PetType> findPetTypes();
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="56">

---

&nbsp;

```java
	@Query("SELECT DISTINCT owner FROM Owner owner left join  owner.pets WHERE owner.lastName LIKE :lastName% ")
	@Transactional(readOnly = true)
	Page<Owner> findByLastName(@Param("lastName") String lastName, Pageable pageable);

	/**
	 * Retrieve an {@link Owner} from the data store by id.
	 * @param id the id to search for
	 * @return the {@link Owner} if found
	 */
	@Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
	@Transactional(readOnly = true)
	Owner findById(@Param("id") Integer id);
```

---

</SwmSnippet>

# pagination support

Methods returning multiple Owner entities use Spring Data's Pageable and Page abstractions. This enables clients to request specific pages of data with sorting and size parameters, improving scalability when dealing with large datasets.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="56">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="58:6:6" line-data="	Page&lt;Owner&gt; findByLastName(@Param(&quot;lastName&quot;) String lastName, Pageable pageable);">`findByLastName`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="80:6:6" line-data="	Page&lt;Owner&gt; findAll(Pageable pageable);">`findAll`</SwmToken> methods return Page<Owner>, allowing the UI or service layer to navigate through owner records efficiently.

```java
	@Query("SELECT DISTINCT owner FROM Owner owner left join  owner.pets WHERE owner.lastName LIKE :lastName% ")
	@Transactional(readOnly = true)
	Page<Owner> findByLastName(@Param("lastName") String lastName, Pageable pageable);
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="75">

---

&nbsp;

```java
	/**
	 * Returnes all the owners from data store
	 **/
	@Query("SELECT owner FROM Owner owner")
	@Transactional(readOnly = true)
	Page<Owner> findAll(Pageable pageable);

}
```

---

</SwmSnippet>

# save method for persistence

The save(Owner owner) method declares the ability to insert or update an Owner entity. It is part of the repository interface to allow persistence operations. The actual implementation is provided by Spring Data at runtime.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="69">

---

This method is essential for creating new owners or updating existing ones without exposing the underlying EntityManager or session details.

```java
	/**
	 * Save an {@link Owner} to the data store, either inserting or updating it.
	 * @param owner the {@link Owner} to save
	 */
	void save(Owner owner);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
