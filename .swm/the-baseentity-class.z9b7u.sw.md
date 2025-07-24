---
title: The BaseEntity class
---
# Inheritance diagram

This diagram shows the inheritance tree of the class:

```mermaid
graph TD;
  BaseEntity:::currentBaseStyle
BaseEntity --> NamedEntity
NamedEntity --> Pet
NamedEntity --> PetType
NamedEntity --> Specialty
BaseEntity --> Person
Person --> Owner
Person --> Vet
BaseEntity --> Visit

 classDef currentBaseStyle color:#000000,fill:#7CB9F4

%% Swimm:
%% graph TD;
%%   <SwmToken path="src\main\java\org\springframework\samples\petclinic\PetClinicRuntimeHints.java" pos="32:9:9" line-data="		hints.serialization().registerType(BaseEntity.class);">`BaseEntity`</SwmToken>:::currentBaseStyle
%% <SwmToken path="src\main\java\org\springframework\samples\petclinic\PetClinicRuntimeHints.java" pos="32:9:9" line-data="		hints.serialization().registerType(BaseEntity.class);">`BaseEntity`</SwmToken> --> <SwmToken path="src\main\java\org\springframework\samples\petclinic\model\NamedEntity.java" pos="31:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken>
%% <SwmToken path="src\main\java\org\springframework\samples\petclinic\model\NamedEntity.java" pos="31:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> --> Pet
%% <SwmToken path="src\main\java\org\springframework\samples\petclinic\model\NamedEntity.java" pos="31:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> --> PetType
%% <SwmToken path="src\main\java\org\springframework\samples\petclinic\model\NamedEntity.java" pos="31:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> --> Specialty
%% <SwmToken path="src\main\java\org\springframework\samples\petclinic\PetClinicRuntimeHints.java" pos="32:9:9" line-data="		hints.serialization().registerType(BaseEntity.class);">`BaseEntity`</SwmToken> --> Person
%% Person --> Owner
%% Person --> Vet
%% <SwmToken path="src\main\java\org\springframework\samples\petclinic\PetClinicRuntimeHints.java" pos="32:9:9" line-data="		hints.serialization().registerType(BaseEntity.class);">`BaseEntity`</SwmToken> --> Visit
%% 
%%  classDef currentBaseStyle color:#000000,fill:#7CB9F4
```

# What is <SwmToken path="src\main\java\org\springframework\samples\petclinic\PetClinicRuntimeHints.java" pos="32:9:9" line-data="		hints.serialization().registerType(BaseEntity.class);">`BaseEntity`</SwmToken>

The <SwmToken path="src\main\java\org\springframework\samples\petclinic\PetClinicRuntimeHints.java" pos="32:9:9" line-data="		hints.serialization().registerType(BaseEntity.class);">`BaseEntity`</SwmToken> class is a simple <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="26:5:5" line-data=" * Simple JavaBean domain object with an id property. Used as a base class for objects">`JavaBean`</SwmToken> domain object with an <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="40:3:3" line-data="		return id;">`id`</SwmToken> property. It serves as a base class for other objects that require this property, providing a common structure for entities in the application. The class is marked with <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="32:0:1" line-data="@MappedSuperclass">`@MappedSuperclass`</SwmToken>, indicating that it is not a complete entity itself but provides mapping information for its subclasses.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" line="39">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="39:5:5" line-data="	public Integer getId() {">`getId`</SwmToken> is used to retrieve the value of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="40:3:3" line-data="		return id;">`id`</SwmToken> property. It returns the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="40:3:3" line-data="		return id;">`id`</SwmToken> of the entity, which is an <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="39:3:3" line-data="	public Integer getId() {">`Integer`</SwmToken>.

```java
	public Integer getId() {
		return id;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" line="43">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="43:5:5" line-data="	public void setId(Integer id) {">`setId`</SwmToken> is used to set the value of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="43:9:9" line-data="	public void setId(Integer id) {">`id`</SwmToken> property. It takes an <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="43:7:7" line-data="	public void setId(Integer id) {">`Integer`</SwmToken> as a parameter and assigns it to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="43:9:9" line-data="	public void setId(Integer id) {">`id`</SwmToken> field.

```java
	public void setId(Integer id) {
		this.id = id;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" line="47">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="47:5:5" line-data="	public boolean isNew() {">`isNew`</SwmToken> checks whether the entity is new by determining if the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="48:5:5" line-data="		return this.id == null;">`id`</SwmToken> is <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="48:9:9" line-data="		return this.id == null;">`null`</SwmToken>. It returns `true` if the <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="48:5:5" line-data="		return this.id == null;">`id`</SwmToken> is <SwmToken path="src/main/java/org/springframework/samples/petclinic/model/BaseEntity.java" pos="48:9:9" line-data="		return this.id == null;">`null`</SwmToken>, indicating that the entity has not been persisted yet.

```java
	public boolean isNew() {
		return this.id == null;
	}
```

---

</SwmSnippet>

# Usage

<SwmSnippet path="/src\main\java\org\springframework\samples\petclinic\PetClinicRuntimeHints.java" line="32">

---

In <SwmToken path="src\main\java\org\springframework\samples\petclinic\PetClinicRuntimeHints.java" pos="25:4:4" line-data="public class PetClinicRuntimeHints implements RuntimeHintsRegistrar {">`PetClinicRuntimeHints`</SwmToken>, <SwmToken path="src\main\java\org\springframework\samples\petclinic\PetClinicRuntimeHints.java" pos="32:9:9" line-data="		hints.serialization().registerType(BaseEntity.class);">`BaseEntity`</SwmToken> is registered for serialization. This indicates that objects of this class can be serialized, which is essential for converting them into a format that can be easily stored or transmitted.

```java
		hints.serialization().registerType(BaseEntity.class);
```

---

</SwmSnippet>

<SwmSnippet path="/src\main\java\org\springframework\samples\petclinic\model\Person.java" line="28">

---

The <SwmToken path="src\main\java\org\springframework\samples\petclinic\model\Person.java" pos="28:4:4" line-data="public class Person extends BaseEntity {">`Person`</SwmToken> class extends <SwmToken path="src\main\java\org\springframework\samples\petclinic\model\Person.java" pos="28:8:8" line-data="public class Person extends BaseEntity {">`BaseEntity`</SwmToken>, inheriting its properties and behaviors. This allows <SwmToken path="src\main\java\org\springframework\samples\petclinic\model\Person.java" pos="28:4:4" line-data="public class Person extends BaseEntity {">`Person`</SwmToken> to utilize the identity and serialization features provided by <SwmToken path="src\main\java\org\springframework\samples\petclinic\model\Person.java" pos="28:8:8" line-data="public class Person extends BaseEntity {">`BaseEntity`</SwmToken>, facilitating its integration into the application's data model.

```java
public class Person extends BaseEntity {
```

---

</SwmSnippet>

<SwmSnippet path="/src\main\java\org\springframework\samples\petclinic\model\NamedEntity.java" line="31">

---

Similarly, <SwmToken path="src\main\java\org\springframework\samples\petclinic\model\NamedEntity.java" pos="31:4:4" line-data="public class NamedEntity extends BaseEntity {">`NamedEntity`</SwmToken> extends <SwmToken path="src\main\java\org\springframework\samples\petclinic\model\NamedEntity.java" pos="31:8:8" line-data="public class NamedEntity extends BaseEntity {">`BaseEntity`</SwmToken>, which means it inherits the identity management capabilities. This is useful for entities that require a unique identifier within the database.

```java
public class NamedEntity extends BaseEntity {
```

---

</SwmSnippet>

<SwmSnippet path="/src\main\java\org\springframework\samples\petclinic\owner\Visit.java" line="36">

---

The <SwmToken path="src\main\java\org\springframework\samples\petclinic\owner\Visit.java" pos="36:4:4" line-data="public class Visit extends BaseEntity {">`Visit`</SwmToken> class is another example where <SwmToken path="src\main\java\org\springframework\samples\petclinic\owner\Visit.java" pos="36:8:8" line-data="public class Visit extends BaseEntity {">`BaseEntity`</SwmToken> is extended. This allows <SwmToken path="src\main\java\org\springframework\samples\petclinic\owner\Visit.java" pos="36:4:4" line-data="public class Visit extends BaseEntity {">`Visit`</SwmToken> to be treated as a database entity with a unique identifier, supporting operations like persistence and retrieval.

```java
public class Visit extends BaseEntity {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
