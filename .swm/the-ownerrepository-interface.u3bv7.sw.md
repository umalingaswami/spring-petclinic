---
title: The OwnerRepository interface
---
This document will cover the interface <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> in the Spring PetClinic project. We will explore:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> is and its purpose.
2. The functions defined in <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken>.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> is an interface in the Spring PetClinic project that extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="24:12:12" line-data="import org.springframework.data.jpa.repository.JpaRepository;">`JpaRepository`</SwmToken>. It is designed to manage Owner domain objects, providing methods to perform CRUD operations and queries on the Owner data. The interface adheres to Spring Data naming conventions, allowing for easy extension and integration with Spring Data JPA.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="48">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="48:6:6" line-data="	Page&lt;Owner&gt; findByLastNameStartingWith(String lastName, Pageable pageable);">`findByLastNameStartingWith`</SwmToken> is used to retrieve a collection of Owner objects from the data store whose last names start with a given string. It returns a Page of Owner objects, allowing for pagination of results.

```java
	Page<Owner> findByLastNameStartingWith(String lastName, Pageable pageable);
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="63">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="63:6:6" line-data="	Optional&lt;Owner&gt; findById(@Nonnull Integer id);">`findById`</SwmToken> is used to retrieve an Owner object from the data store by its unique identifier. It returns an Optional containing the Owner if found, or an empty Optional if no Owner is found with the provided ID.

```java
	Optional<Owner> findById(@Nonnull Integer id);
```

---

</SwmSnippet>

# Usage

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="49">

---

In the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="53:3:3" line-data="	public OwnerController(OwnerRepository owners) {">`OwnerController`</SwmToken>, the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> is used to manage owner-related operations. It is injected into the controller through its constructor, allowing the controller to interact with the data store to perform actions such as retrieving and updating owner information.

```java
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

	private final OwnerRepository owners;

	public OwnerController(OwnerRepository owners) {
		this.owners = owners;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="47">

---

In the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:3:3" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetController`</SwmToken>, the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="49:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> is used alongside the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="51:5:5" line-data="	private final PetTypeRepository types;">`PetTypeRepository`</SwmToken> to facilitate operations related to pets. It is injected into the controller, enabling the retrieval of owner data necessary for managing pet records.

```java
	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

	private final OwnerRepository owners;

	private final PetTypeRepository types;

	public PetController(OwnerRepository owners, PetTypeRepository types) {
		this.owners = owners;
		this.types = types;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="41">

---

In the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="42:2:2" line-data="class VisitController {">`VisitController`</SwmToken>, the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="44:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> is utilized to access owner information required for managing visits. It is injected into the controller, allowing seamless interaction with the data store to handle visit-related functionalities.

```java
@Controller
class VisitController {

	private final OwnerRepository owners;

	public VisitController(OwnerRepository owners) {
		this.owners = owners;
	}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
