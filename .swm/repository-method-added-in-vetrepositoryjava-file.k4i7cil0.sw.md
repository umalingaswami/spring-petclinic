---
title: Repository method added in VetRepository.java file
---
# Introduction

This document explains the addition of a new repository method in <SwmPath>[src/…/vet/VetRepository.java](/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java)</SwmPath>. The main points covered are:

1. Why the method to find vets by last name was added.
2. How the method is defined and its transactional behavior.
3. The expected usage and return type of the method.

# purpose of the new repository method

The new method was added to enable searching for vets based on their last name. This is useful for scenarios where users want to filter vets by their surname, such as in search forms or listings filtered by last name.

# method definition and transactional behavior

The method is declared in the <SwmToken path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="38:4:4" line-data="public interface VetRepository extends Repository&lt;Vet, Integer&gt; {">`VetRepository`</SwmToken> interface with the signature:

- It accepts a <SwmToken path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="64:8:8" line-data="	Collection&lt;Vet&gt; findByLastName(String lastName) throws DataAccessException;">`String`</SwmToken> parameter representing the last name to search for.
- It returns a collection of <SwmToken path="/src/main/java/org/springframework/samples/petclinic/vet/Vet.java" pos="45:4:4" line-data="public class Vet extends Person {">`Vet`</SwmToken> objects matching the given last name.
- It is annotated with `@`<SwmToken path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="63:2:2" line-data="	@Transactional(readOnly = true)">`Transactional`</SwmToken>`(`<SwmToken path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="63:4:4" line-data="	@Transactional(readOnly = true)">`readOnly`</SwmToken>` = true)` to indicate that it only reads data and does not modify the database. This optimizes transaction management by avoiding unnecessary locking or write operations.
- It declares that it may throw <SwmToken path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="64:15:15" line-data="	Collection&lt;Vet&gt; findByLastName(String lastName) throws DataAccessException;">`DataAccessException`</SwmToken> to handle any data access issues during execution.

<SwmSnippet path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" line="57">

---

This design keeps the method simple and focused on querying without side effects.

```
	
	/**
	 * Find a vet by last name.
	 * @param lastName the last name to search for
	 * @return a collection of vets with the given last name
	 */
	@Transactional(readOnly = true)
	Collection<Vet> findByLastName(String lastName) throws DataAccessException;

```

---

</SwmSnippet>

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
