---
title: 'BaseEntity: Common Domain Base Class'
---
# Introduction

This document explains the rationale and main design points behind the implementation of a common base class for domain entities in the project. We will cover:

1. Why a base class for entities is introduced.
2. How the base class manages the entity identifier.
3. How the base class helps determine if an entity is new or already persisted.

# why use a base class for domain entities

The base class provides a shared foundation for all domain objects that require an identifier property. This avoids duplicating the id field and related logic across multiple entity classes. It also centralizes common behavior related to entity identity.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" line="18">

---

The class is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="32:0:1" line-data="@MappedSuperclass">`@MappedSuperclass`</SwmToken>, which means it is not a standalone entity but its properties are inherited by subclasses and mapped to the database tables of those subclasses. This design keeps the domain model clean and consistent.

```java
import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for objects
 * needing this property.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
```

---

</SwmSnippet>

# how the entity id is managed

The base class declares a private Integer field <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="26:15:15" line-data=" * Simple JavaBean domain object with an id property. Used as a base class for objects">`id`</SwmToken> annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="35:1:2" line-data="	@Id">`@Id`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="36:1:11" line-data="	@GeneratedValue(strategy = GenerationType.IDENTITY)">`@GeneratedValue(strategy = GenerationType.IDENTITY)`</SwmToken>. This configures the persistence provider to treat <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="26:15:15" line-data=" * Simple JavaBean domain object with an id property. Used as a base class for objects">`id`</SwmToken> as the primary key and to generate its value automatically using the database identity column feature.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" line="35">

---

This setup ensures that every entity subclass inherits a unique identifier that is automatically assigned when the entity is persisted.

```java
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
```

---

</SwmSnippet>

# determining if an entity is new

The base class provides a method <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="47:5:7" line-data="	public boolean isNew() {">`isNew()`</SwmToken> that returns true if the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="26:15:15" line-data=" * Simple JavaBean domain object with an id property. Used as a base class for objects">`id`</SwmToken> is null. Since the id is assigned only after persistence, a null id indicates the entity has not been saved yet.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" line="47">

---

This method is useful in service or repository layers to decide whether to insert or update an entity.

```java
	public boolean isNew() {
		return this.id == null;
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
