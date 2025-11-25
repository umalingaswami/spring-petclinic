---
title: NamedEntity Base Class
---
# Introduction

This document explains the rationale behind introducing a <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> base class in the domain model. We will cover:

1. Why <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> extends <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="22:24:24" line-data=" * Simple JavaBean domain object adds a name property to &lt;code&gt;BaseEntity&lt;/code&gt;. Used as">`BaseEntity`</SwmToken> and uses @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken>.
2. The purpose of adding a name property at this level.
3. How the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="43:5:5" line-data="	public String toString() {">`toString`</SwmToken> method is overridden to represent the entity by its name.

# why extend <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="22:24:24" line-data=" * Simple JavaBean domain object adds a name property to &lt;code&gt;BaseEntity&lt;/code&gt;. Used as">`BaseEntity`</SwmToken> and use @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" line="18">

---

<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> inherits from <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="22:24:24" line-data=" * Simple JavaBean domain object adds a name property to &lt;code&gt;BaseEntity&lt;/code&gt;. Used as">`BaseEntity`</SwmToken> to reuse common entity features like id management. Marking it with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="19:6:6" line-data="import jakarta.persistence.MappedSuperclass;">`MappedSuperclass`</SwmToken> means it is not a standalone entity but provides persistent state and mapping information to subclasses. This avoids duplicating the name property and its mapping in multiple entity classes.

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

# why add a name property here

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" line="31">

---

Many domain objects in the application have a name attribute. Placing the name field and its getter/setter in <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="29:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> centralizes this common property. This reduces boilerplate and enforces consistency in how name is handled across entities.

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

# overriding <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="43:5:5" line-data="	public String toString() {">`toString`</SwmToken> to return the name

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" line="42">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/NamedEntity.java" pos="43:5:5" line-data="	public String toString() {">`toString`</SwmToken> method is overridden to return the entity’s name. This makes logging, debugging, and UI display simpler by showing meaningful names instead of default object references or ids.

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
