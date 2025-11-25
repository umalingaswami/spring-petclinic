---
title: The VetRepository interface
---
This document covers the interface <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="38:4:4" line-data="public interface VetRepository extends Repository&lt;Vet, Integer&gt; {">`VetRepository`</SwmToken>. We will explain:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="38:4:4" line-data="public interface VetRepository extends Repository&lt;Vet, Integer&gt; {">`VetRepository`</SwmToken> is and its purpose.
2. The functions defined in <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="38:4:4" line-data="public interface VetRepository extends Repository&lt;Vet, Integer&gt; {">`VetRepository`</SwmToken>, including their roles and usage.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="38:4:4" line-data="public interface VetRepository extends Repository&lt;Vet, Integer&gt; {">`VetRepository`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="38:4:4" line-data="public interface VetRepository extends Repository&lt;Vet, Integer&gt; {">`VetRepository`</SwmToken> is an interface in the application that acts as a repository for Vet domain objects. It extends the Spring Data Repository interface, parameterized with Vet as the domain type and Integer as the ID type. This interface defines methods to retrieve Vet entities from the underlying data store. It follows Spring Data naming conventions, allowing easy extension and integration with Spring Data JPA for query method creation and data access abstraction.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" line="44">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="46:6:6" line-data="	Collection&lt;Vet&gt; findAll() throws DataAccessException;">`findAll`</SwmToken> retrieves all Vet entities from the data store and returns them as a Collection. It is marked as read-only transactional and cacheable under the "vets" cache to optimize performance by avoiding repeated database hits.

```java
	@Transactional(readOnly = true)
	@Cacheable("vets")
	Collection<Vet> findAll() throws DataAccessException;
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" line="54">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="56:6:11" line-data="	Page&lt;Vet&gt; findAll(Pageable pageable) throws DataAccessException;">`findAll(Pageable pageable)`</SwmToken> retrieves Vet entities from the data store in a paginated fashion, returning a Page of Vet objects. This supports efficient handling of large datasets by fetching data in chunks. Like the other method, it is read-only transactional and cacheable under the "vets" cache.

```java
	@Transactional(readOnly = true)
	@Cacheable("vets")
	Page<Vet> findAll(Pageable pageable) throws DataAccessException;
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="38:4:4" line-data="public interface VetRepository extends Repository&lt;Vet, Integer&gt; {">`VetRepository`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="38:4:4" line-data="public interface VetRepository extends Repository&lt;Vet, Integer&gt; {">`VetRepository`</SwmToken> is used as a dependency in VetController, where it is injected through the constructor. This allows VetController to access and manage vet-related data by delegating calls to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="38:4:4" line-data="public interface VetRepository extends Repository&lt;Vet, Integer&gt; {">`VetRepository`</SwmToken> implementation.

In VetController, <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="38:4:4" line-data="public interface VetRepository extends Repository&lt;Vet, Integer&gt; {">`VetRepository`</SwmToken> is assigned to a private final field, ensuring that the controller has a consistent and immutable reference to the repository throughout its lifecycle. This setup supports the controller's role in handling web requests related to vets by interacting with the repository to retrieve or manipulate vet data.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
