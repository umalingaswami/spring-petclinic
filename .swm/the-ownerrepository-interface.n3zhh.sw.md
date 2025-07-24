---
title: The OwnerRepository interface
---
This document will cover the interface <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="39:4:4" line-data="public interface OwnerRepository extends JpaRepository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> in the Spring PetClinic project. We'll explore:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="39:4:4" line-data="public interface OwnerRepository extends JpaRepository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> is and its purpose.
2. Key functions defined in <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="39:4:4" line-data="public interface OwnerRepository extends JpaRepository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken>.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="39:4:4" line-data="public interface OwnerRepository extends JpaRepository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="39:4:4" line-data="public interface OwnerRepository extends JpaRepository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> is an interface in the Spring PetClinic project that extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="24:12:12" line-data="import org.springframework.data.jpa.repository.JpaRepository;">`JpaRepository`</SwmToken>. It is designed to manage <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="48:3:3" line-data="	Page&lt;Owner&gt; findByLastNameStartingWith(String lastName, Pageable pageable);">`Owner`</SwmToken> domain objects, providing methods to perform CRUD operations on owner data. The interface follows Spring Data naming conventions, allowing for easy extension and integration with Spring Data JPA.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="48">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="48:6:6" line-data="	Page&lt;Owner&gt; findByLastNameStartingWith(String lastName, Pageable pageable);">`findByLastNameStartingWith`</SwmToken> is used to retrieve <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="48:3:3" line-data="	Page&lt;Owner&gt; findByLastNameStartingWith(String lastName, Pageable pageable);">`Owner`</SwmToken> objects from the data store whose last names start with a specified string. It returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="48:1:1" line-data="	Page&lt;Owner&gt; findByLastNameStartingWith(String lastName, Pageable pageable);">`Page`</SwmToken> of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="48:3:3" line-data="	Page&lt;Owner&gt; findByLastNameStartingWith(String lastName, Pageable pageable);">`Owner`</SwmToken> objects, allowing for pagination of results.

```java
	Page<Owner> findByLastNameStartingWith(String lastName, Pageable pageable);
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" line="63">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="63:6:6" line-data="	Optional&lt;Owner&gt; findById(@Nonnull Integer id);">`findById`</SwmToken> retrieves an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="63:3:3" line-data="	Optional&lt;Owner&gt; findById(@Nonnull Integer id);">`Owner`</SwmToken> object from the data store by its ID. It returns an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="63:1:1" line-data="	Optional&lt;Owner&gt; findById(@Nonnull Integer id);">`Optional`</SwmToken> containing the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="63:3:3" line-data="	Optional&lt;Owner&gt; findById(@Nonnull Integer id);">`Owner`</SwmToken> if found, or an empty <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="63:1:1" line-data="	Optional&lt;Owner&gt; findById(@Nonnull Integer id);">`Optional`</SwmToken> if not found. This method ensures safe handling of null values by using <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="63:1:1" line-data="	Optional&lt;Owner&gt; findById(@Nonnull Integer id);">`Optional`</SwmToken>.

```java
	Optional<Owner> findById(@Nonnull Integer id);
```

---

</SwmSnippet>

# Usage

## VisitController

In the `VisitController`, the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="39:4:4" line-data="public interface OwnerRepository extends JpaRepository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> is used to manage owner-related data. It is injected into the controller, allowing the controller to perform operations related to owners, such as retrieving owner information when handling visits.

## OwnerController

The `OwnerController` utilizes the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="39:4:4" line-data="public interface OwnerRepository extends JpaRepository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> to facilitate the creation and updating of owner records. By injecting the repository, the controller can interact with the data store to manage owner data effectively, including operations like retrieving and saving owner details.

## PetController

Within the `PetController`, the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerRepository.java" pos="39:4:4" line-data="public interface OwnerRepository extends JpaRepository&lt;Owner, Integer&gt; {">`OwnerRepository`</SwmToken> is employed alongside the `PetTypeRepository` to handle operations related to pets and their owners. This setup allows the controller to access owner data, which is crucial for associating pets with their respective owners during pet creation or updates.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
