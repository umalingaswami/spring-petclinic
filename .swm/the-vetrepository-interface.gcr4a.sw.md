---
title: The VetRepository interface
---
This document will cover the interface <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="38:5:5" line-data="	private final VetRepository vetRepository;">`VetRepository`</SwmToken> in the Spring PetClinic project. We will explore:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="38:5:5" line-data="	private final VetRepository vetRepository;">`VetRepository`</SwmToken> is and its purpose.
2. The variables and functions defined within <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="38:5:5" line-data="	private final VetRepository vetRepository;">`VetRepository`</SwmToken>.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="38:5:5" line-data="	private final VetRepository vetRepository;">`VetRepository`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="38:5:5" line-data="	private final VetRepository vetRepository;">`VetRepository`</SwmToken> is an interface in the Spring PetClinic project located at <SwmPath>[src/…/vet/VetRepository.java](src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java)</SwmPath>. It serves as a repository for Vet domain objects, allowing for the retrieval of Vet data from the data store. The interface extends the Spring Data <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="22:10:10" line-data="import org.springframework.data.repository.Repository;">`Repository`</SwmToken> interface, which provides a foundation for creating data access layers in Spring applications. <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="38:5:5" line-data="	private final VetRepository vetRepository;">`VetRepository`</SwmToken> is designed to comply with Spring Data naming conventions, making it easily extendable for Spring Data functionalities.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" line="44">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="46:6:6" line-data="	Collection&lt;Vet&gt; findAll() throws DataAccessException;">`findAll`</SwmToken> is used to retrieve all Vet objects from the data store. It returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="46:1:1" line-data="	Collection&lt;Vet&gt; findAll() throws DataAccessException;">`Collection`</SwmToken> of Vet objects and is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="44:1:9" line-data="	@Transactional(readOnly = true)">`@Transactional(readOnly = true)`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="45:1:7" line-data="	@Cacheable(&quot;vets&quot;)">`@Cacheable("vets")`</SwmToken>, indicating that the operation is read-only and the results are cacheable.

```java
	@Transactional(readOnly = true)
	@Cacheable("vets")
	Collection<Vet> findAll() throws DataAccessException;
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" line="54">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="56:6:11" line-data="	Page&lt;Vet&gt; findAll(Pageable pageable) throws DataAccessException;">`findAll(Pageable pageable)`</SwmToken> is used to retrieve Vet objects from the data store in a paginated format. It returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="56:1:1" line-data="	Page&lt;Vet&gt; findAll(Pageable pageable) throws DataAccessException;">`Page`</SwmToken> of Vet objects, allowing for pagination support. Similar to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="56:6:6" line-data="	Page&lt;Vet&gt; findAll(Pageable pageable) throws DataAccessException;">`findAll`</SwmToken> method, it is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="54:1:9" line-data="	@Transactional(readOnly = true)">`@Transactional(readOnly = true)`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="55:1:7" line-data="	@Cacheable(&quot;vets&quot;)">`@Cacheable("vets")`</SwmToken>, ensuring read-only access and caching.

```java
	@Transactional(readOnly = true)
	@Cacheable("vets")
	Page<Vet> findAll(Pageable pageable) throws DataAccessException;
```

---

</SwmSnippet>

# Usage

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="35">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="38:5:5" line-data="	private final VetRepository vetRepository;">`VetRepository`</SwmToken> interface is utilized within the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="36:2:2" line-data="class VetController {">`VetController`</SwmToken> class. It is injected into the controller through its constructor, allowing the controller to access and manage veterinary data. This setup enables the controller to retrieve all Vet entities from the data store, facilitating operations related to veterinary information management.

```java
@Controller
class VetController {

	private final VetRepository vetRepository;

	public VetController(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
