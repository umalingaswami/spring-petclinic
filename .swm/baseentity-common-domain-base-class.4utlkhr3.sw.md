---
title: 'BaseEntity: Common Domain Base Class'
---
# Introduction

This document explains the rationale behind introducing a common base class for domain entities in the project. We will cover:

1. Why a base class for entities is needed.
2. How the base class manages the entity identifier.
3. The purpose of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="47:5:7" line-data="	public boolean isNew() {">`isNew()`</SwmToken> method and how it helps in entity lifecycle management.

# why a base class for entities

The base class consolidates common properties and behavior shared by all domain entities. Instead of duplicating an <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="26:15:15" line-data=" * Simple JavaBean domain object with an id property. Used as a base class for objects">`id`</SwmToken> property and its accessors in every entity class, this class centralizes that logic. It is marked with <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="32:0:1" line-data="@MappedSuperclass">`@MappedSuperclass`</SwmToken> so that JPA recognizes it as a superclass whose properties are inherited by entity subclasses but which itself is not an entity.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" line="18">

---

This approach reduces boilerplate and enforces consistency across entities.

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

# how the base class manages the entity identifier

The base class declares a private <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="26:15:15" line-data=" * Simple JavaBean domain object with an id property. Used as a base class for objects">`id`</SwmToken> field annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="35:1:2" line-data="	@Id">`@Id`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="36:1:11" line-data="	@GeneratedValue(strategy = GenerationType.IDENTITY)">`@GeneratedValue(strategy = GenerationType.IDENTITY)`</SwmToken>. This means the database will generate the primary key value automatically when the entity is persisted.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" line="35">

---

Providing a getter and setter for <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="37:5:5" line-data="	private Integer id;">`id`</SwmToken> allows subclasses and other parts of the application to access and modify the identifier as needed.

```java
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
```

---

</SwmSnippet>

# the purpose of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="47:5:7" line-data="	public boolean isNew() {">`isNew()`</SwmToken> method

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="47:5:7" line-data="	public boolean isNew() {">`isNew()`</SwmToken> method returns true if the entity has not been persisted yet, which is indicated by a null <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="26:15:15" line-data=" * Simple JavaBean domain object with an id property. Used as a base class for objects">`id`</SwmToken>. This is a simple way to check if the entity is transient or already stored in the database.

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
