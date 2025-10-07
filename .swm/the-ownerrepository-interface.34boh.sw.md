---
title: The OwnerRepository interface
---
This document explains the interface <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken>. We will cover:

1. What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken>
2. Variables and functions in <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken>

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> is an interface in the package <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="16:2:10" line-data="package org.springframework.samples.petclinic.owner;">`org.springframework.samples.petclinic.owner`</SwmToken> that acts as a repository for Owner domain objects. It extends the Spring Data Repository interface, parameterized with Owner as the domain type and Integer as the ID type. This interface defines methods for retrieving and saving Owner entities and related <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="44:11:11" line-data="	@Query(&quot;SELECT ptype FROM PetType ptype ORDER BY ptype.name&quot;)">`PetType`</SwmToken> entities from and to the data store. It follows Spring Data naming conventions, enabling easy extension and integration with Spring Data JPA for query creation and execution.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="44">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="46:6:6" line-data="	List&lt;PetType&gt; findPetTypes();">`findPetTypes`</SwmToken> retrieves all <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="44:11:11" line-data="	@Query(&quot;SELECT ptype FROM PetType ptype ORDER BY ptype.name&quot;)">`PetType`</SwmToken> entities from the data store, ordered by their name. It is annotated with a JPQL query and marked as a read-only transactional method.

```java
	@Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
	@Transactional(readOnly = true)
	List<PetType> findPetTypes();
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="56">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="58:6:6" line-data="	Page&lt;Owner&gt; findByLastName(@Param(&quot;lastName&quot;) String lastName, Pageable pageable);">`findByLastName`</SwmToken> retrieves a paginated list of Owner entities whose last names start with the given string. It uses a JPQL query with a left join on pets and supports pagination via the Pageable parameter. It is also marked as a read-only transactional method.

```java
	@Query("SELECT DISTINCT owner FROM Owner owner left join  owner.pets WHERE owner.lastName LIKE :lastName% ")
	@Transactional(readOnly = true)
	Page<Owner> findByLastName(@Param("lastName") String lastName, Pageable pageable);
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="65">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="67:3:3" line-data="	Owner findById(@Param(&quot;id&quot;) Integer id);">`findById`</SwmToken> retrieves a single Owner entity by its ID, including its associated pets via a fetch join. It uses a JPQL query and is marked as a read-only transactional method.

```java
	@Query("SELECT owner FROM Owner owner left join fetch owner.pets WHERE owner.id =:id")
	@Transactional(readOnly = true)
	Owner findById(@Param("id") Integer id);
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="73">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="73:3:3" line-data="	void save(Owner owner);">`save`</SwmToken> saves an Owner entity to the data store. It handles both inserting new entities and updating existing ones. This method does not have a query annotation as it relies on Spring Data's implementation.

```java
	void save(Owner owner);
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="78">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="80:6:6" line-data="	Page&lt;Owner&gt; findAll(Pageable pageable);">`findAll`</SwmToken> returns a paginated list of all Owner entities from the data store. It uses a JPQL query to select all owners and is marked as a read-only transactional method.

```java
	@Query("SELECT owner FROM Owner owner")
	@Transactional(readOnly = true)
	Page<Owner> findAll(Pageable pageable);
```

---

</SwmSnippet>

# Usage

## OwnerController

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> is injected into the OwnerController to manage owner-related data operations. It is assigned to a private final field and used to handle requests related to creating or updating owner information.

## PetTypeFormatter

In the PetTypeFormatter component, <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> is used to access owner-related data when formatting <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="44:11:11" line-data="	@Query(&quot;SELECT ptype FROM PetType ptype ORDER BY ptype.name&quot;)">`PetType`</SwmToken> objects. It is injected via the constructor and stored in a private final field for use within the formatter.

## PetController

The PetController also depends on <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> to manage owner data in the context of pet-related operations. The repository is injected through the constructor and used to support pet creation and update workflows.

## VisitController

VisitController uses <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="38:4:4" line-data="public interface OwnerRepository extends Repository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> to access owner data when handling visit-related functionality. The repository is injected and stored in a private final field, enabling the controller to associate visits with the correct owners.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
