---
title: 'NamedEntity: Base Class for Named Domain Objects'
---
# Introduction

This document explains the rationale and main points behind the implementation of a base class for domain objects that have a name property. We will cover:

1. Why a separate base class for named entities was introduced.
2. How the class extends the existing entity hierarchy.
3. How the name property is handled and exposed.
4. The purpose of overriding the string representation method.

# why create a base class for named domain objects

The class is designed to serve as a common superclass for all domain objects that require a name attribute. This avoids duplicating the name property and its accessors across multiple classes. It centralizes the handling of the name, making the codebase cleaner and easier to maintain.

# how the class fits in the entity hierarchy

The class extends a more generic base entity class, inheriting common entity features like an identifier. It is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="28:0:1" line-data="@MappedSuperclass">`@MappedSuperclass`</SwmToken>, which means it is not a standalone entity but its properties are mapped into subclasses that are actual entities. This design allows subclasses to inherit the name property and its database mapping without extra configuration.

# handling the name property

The class declares a private string field for the name, mapped to a database column named "name". It provides standard getter and setter methods to access and modify this property. This encapsulation follows <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="22:5:5" line-data=" * Simple JavaBean domain object adds a name property to &lt;code&gt;BaseEntity&lt;/code&gt;. Used as">`JavaBean`</SwmToken> conventions and supports frameworks that rely on these patterns.

# overriding the string representation

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" line="18">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="43:5:7" line-data="	public String toString() {">`toString()`</SwmToken> method is overridden to return the name of the entity. This makes logging, debugging, and displaying instances more meaningful by showing the name instead of a default or less informative string. It leverages the existing getter to retrieve the name.

```java
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

/**
 * Simple JavaBean domain object adds a name property to <code>BaseEntity</code>. Used as
 * a base class for objects needing these properties.
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
@MappedSuperclass
public class NamedEntity extends BaseEntity {

	@Column(name = "name")
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
