---
title: Owner Repository Interface
---
# introduction

This document explains the design and implementation of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> interface in the petclinic project. It covers:

1. Why the repository is defined as an interface extending Spring Data's Repository.
2. How query methods are declared and customized.
3. How pagination and transactional behavior are handled.
4. The rationale behind fetching related entities eagerly in some queries.

# repository interface and its purpose

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> interface defines data access methods for Owner domain objects. It extends Spring Data's Repository interface, which provides a minimal contract for repository implementations. This approach allows Spring Data to generate the actual implementation at runtime based on method signatures and annotations.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="27">

---

The interface is annotated with Javadoc explaining that method names follow Spring Data naming conventions, enabling automatic query creation or customization via annotations. This design keeps the data access layer clean and declarative without manual implementation.

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

The repository declares several query methods to retrieve and manipulate Owner and related <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="41:11:11" line-data="	 * Retrieve all {@link PetType}s from the data store.">`PetType`</SwmToken> entities.

- <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="46:6:8" line-data="	List&lt;PetType&gt; findPetTypes();">`findPetTypes()`</SwmToken> returns all <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="41:11:11" line-data="	 * Retrieve all {@link PetType}s from the data store.">`PetType`</SwmToken> entities ordered by name. It uses a JPQL query annotated with @Query to specify the exact query, and @Transactional(<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="45:4:4" line-data="	@Transactional(readOnly = true)">`readOnly`</SwmToken> = true) to optimize transaction handling for read operations.

- <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="58:6:6" line-data="	Page&lt;Owner&gt; findByLastName(@Param(&quot;lastName&quot;) String lastName, Pageable pageable);">`findByLastName`</SwmToken>(String <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="56:29:29" line-data="	@Query(&quot;SELECT DISTINCT owner FROM Owner owner left join  owner.pets WHERE owner.lastName LIKE :lastName% &quot;)">`lastName`</SwmToken>, Pageable pageable) retrieves Owners whose last name starts with the given parameter. It uses a JPQL query with a left join on pets to fetch related pets eagerly, avoiding lazy loading issues later. The method returns a Page to support pagination.

- <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="67:3:3" line-data="	Owner findById(@Param(&quot;id&quot;) Integer id);">`findById`</SwmToken>(Integer id) fetches an Owner by id, also eagerly loading pets with a left join fetch. This prevents multiple queries when accessing pets of the owner.

- <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="80:6:6" line-data="	Page&lt;Owner&gt; findAll(Pageable pageable);">`findAll`</SwmToken>(Pageable pageable) returns all owners paginated.

- save(Owner owner) persists or updates an Owner entity. This method is declared without annotations because Spring Data handles save operations automatically.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="40">

---

The use of @Query annotations with JPQL allows precise control over fetching strategies and query structure, which is important for performance and avoiding common pitfalls like N+1 selects.

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

	/**
	 * Save an {@link Owner} to the data store, either inserting or updating it.
	 * @param owner the {@link Owner} to save
	 */
	void save(Owner owner);

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

# pagination and transaction management

Pagination is supported by methods returning Page<T> and accepting Pageable parameters. This enables efficient handling of large datasets by loading only requested subsets.

All read operations are marked with @Transactional(<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="45:4:4" line-data="	@Transactional(readOnly = true)">`readOnly`</SwmToken> = true) to optimize transaction management by signaling that no data modifications will occur. This can improve performance and reduce locking overhead.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="40">

---

Write operations like save() are left without explicit transaction annotations here, assuming transactional behavior is managed elsewhere or by Spring Data defaults.

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

# eager fetching of related entities

Queries fetching Owner entities join their pets collections eagerly using JPQL left join fetch or left join. This design avoids lazy loading exceptions and reduces the number of queries executed when accessing pets of an owner.

For example, findById() uses "left join fetch <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="56:21:23" line-data="	@Query(&quot;SELECT DISTINCT owner FROM Owner owner left join  owner.pets WHERE owner.lastName LIKE :lastName% &quot;)">`owner.pets`</SwmToken>" to load pets in the same query as the owner. Similarly, findByLastName() uses a left join to include pets when searching by last name.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="56">

---

This approach balances performance and convenience by loading related data upfront when it is likely needed.

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

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
