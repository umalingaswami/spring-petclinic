---
title: NamedEntity Base Class
---
# introduction

This document explains why the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> base class was introduced and how it fits into the domain model.

We will cover:

1. Why <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="22:24:24" line-data=" * Simple JavaBean domain object adds a name property to &lt;code&gt;BaseEntity&lt;/code&gt;. Used as">`BaseEntity`</SwmToken> and is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken>.
2. Why <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> adds a name property and how it is exposed.
3. The purpose of overriding <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="43:5:7" line-data="	public String toString() {">`toString()`</SwmToken> to return the name.

# why extend <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="22:24:24" line-data=" * Simple JavaBean domain object adds a name property to &lt;code&gt;BaseEntity&lt;/code&gt;. Used as">`BaseEntity`</SwmToken> and use @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" line="18">

---

<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="22:24:24" line-data=" * Simple JavaBean domain object adds a name property to &lt;code&gt;BaseEntity&lt;/code&gt;. Used as">`BaseEntity`</SwmToken> to inherit the common identifier property and behavior shared by all entities. Marking it with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken> means it is not a standalone entity but provides persistent state and mapping information to its subclasses. This avoids repeating the name property mapping in every entity that needs it.

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
```

---

</SwmSnippet>

# why add a name property with getter and setter

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" line="31">

---

<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> adds a private String name field mapped to the "name" column in the database. This encapsulates the name attribute common to many domain objects (like PetType or Specialty) in one place. The getter and setter provide controlled access to this field, supporting <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="22:5:5" line-data=" * Simple JavaBean domain object adds a name property to &lt;code&gt;BaseEntity&lt;/code&gt;. Used as">`JavaBean`</SwmToken> conventions and allowing frameworks to interact with it.

```java
	@Column(name = "name")
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
```

---

</SwmSnippet>

# why override <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="43:5:7" line-data="	public String toString() {">`toString()`</SwmToken> to return the name

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" line="42">

---

Overriding <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="43:5:7" line-data="	public String toString() {">`toString()`</SwmToken> to return the name makes debugging and logging more meaningful. When instances of <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> or its subclasses are printed, the output shows the name instead of a default object reference. This helps quickly identify entities by their human-readable name.

```java
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
