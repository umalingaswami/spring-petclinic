---
title: The VetRepository interface
---
# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="38:5:5" line-data="	private final VetRepository vetRepository;">`VetRepository`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="38:5:5" line-data="	private final VetRepository vetRepository;">`VetRepository`</SwmToken> is an interface in the Spring PetClinic application, located in <SwmPath>[src/…/vet/VetRepository.java](src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java)</SwmPath>. It serves as a repository for <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="46:3:3" line-data="	Collection&lt;Vet&gt; findAll() throws DataAccessException;">`Vet`</SwmToken> domain objects, allowing for the retrieval of veterinarian data from the data store. This interface is designed to comply with Spring Data naming conventions, making it easily extendable for Spring Data functionalities.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" line="44">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="46:6:6" line-data="	Collection&lt;Vet&gt; findAll() throws DataAccessException;">`findAll`</SwmToken> is used to retrieve all <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="46:3:3" line-data="	Collection&lt;Vet&gt; findAll() throws DataAccessException;">`Vet`</SwmToken> objects from the data store. It returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="46:1:1" line-data="	Collection&lt;Vet&gt; findAll() throws DataAccessException;">`Collection`</SwmToken> of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="46:3:3" line-data="	Collection&lt;Vet&gt; findAll() throws DataAccessException;">`Vet`</SwmToken> objects and is marked with the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="44:1:9" line-data="	@Transactional(readOnly = true)">`@Transactional(readOnly = true)`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="45:1:7" line-data="	@Cacheable(&quot;vets&quot;)">`@Cacheable("vets")`</SwmToken> annotations to ensure efficient data retrieval and caching.

```java
	@Transactional(readOnly = true)
	@Cacheable("vets")
	Collection<Vet> findAll() throws DataAccessException;
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" line="54">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="56:6:6" line-data="	Page&lt;Vet&gt; findAll(Pageable pageable) throws DataAccessException;">`findAll`</SwmToken> with a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="56:8:8" line-data="	Page&lt;Vet&gt; findAll(Pageable pageable) throws DataAccessException;">`Pageable`</SwmToken> parameter retrieves <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="56:3:3" line-data="	Page&lt;Vet&gt; findAll(Pageable pageable) throws DataAccessException;">`Vet`</SwmToken> objects from the data store in a paginated format. It returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="56:1:1" line-data="	Page&lt;Vet&gt; findAll(Pageable pageable) throws DataAccessException;">`Page`</SwmToken> of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="56:3:3" line-data="	Page&lt;Vet&gt; findAll(Pageable pageable) throws DataAccessException;">`Vet`</SwmToken> objects, allowing for efficient handling of large datasets. This function is also annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="54:1:9" line-data="	@Transactional(readOnly = true)">`@Transactional(readOnly = true)`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetRepository.java" pos="55:1:7" line-data="	@Cacheable(&quot;vets&quot;)">`@Cacheable("vets")`</SwmToken> for optimized data access and caching.

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

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="38:5:5" line-data="	private final VetRepository vetRepository;">`VetRepository`</SwmToken> interface is utilized within the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="36:2:2" line-data="class VetController {">`VetController`</SwmToken> class. It is injected into the controller through its constructor, allowing the controller to interact with the data store to retrieve information about veterinarians. This setup enables the controller to handle requests related to veterinarians efficiently by leveraging the repository's methods.

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
