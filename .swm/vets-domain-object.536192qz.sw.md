---
title: Vets Domain Object
---
# Introduction

This document explains the design and purpose of the Vets domain object in the application. It answers these questions:

1. Why is there a dedicated Vets class instead of just using a list of Vet objects?
2. How does the Vets class support XML marshalling?
3. Why is the getter method implemented with lazy initialization?

# why a dedicated vets class exists

The Vets class wraps a list of Vet objects. This is not just a container but a domain object representing a collection of veterinarians. It exists mainly to support the XML marshalling view used in the application, which requires a root element to marshal a collection properly. Without this wrapper, the marshalling framework would have trouble converting a raw list into XML.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vets.java" line="18">

---

This design choice keeps the domain model clean and aligns with the requirements of the view layer that expects a root element for XML output.

```java
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Simple domain object representing a list of veterinarians. Mostly here to be used for
 * the 'vets' {@link org.springframework.web.servlet.view.xml.MarshallingView}.
 *
 * @author Arjen Poutsma
 */
@XmlRootElement
public class Vets {
```

---

</SwmSnippet>

# how xml marshalling is enabled

The class is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="22:10:10" line-data="import jakarta.xml.bind.annotation.XmlRootElement;">`XmlRootElement`</SwmToken>, marking it as the root element for XML serialization. The getter method for the list is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="21:10:10" line-data="import jakarta.xml.bind.annotation.XmlElement;">`XmlElement`</SwmToken>, which tells the marshaller to treat the returned list as XML elements nested under the root.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vets.java" line="18">

---

This setup directly supports the 'vets' <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="26:25:25" line-data=" * the &#39;vets&#39; {@link org.springframework.web.servlet.view.xml.MarshallingView}.">`MarshallingView`</SwmToken> in the web layer, enabling the application to produce XML responses for requests that expect a list of veterinarians.

```java
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Simple domain object representing a list of veterinarians. Mostly here to be used for
 * the 'vets' {@link org.springframework.web.servlet.view.xml.MarshallingView}.
 *
 * @author Arjen Poutsma
 */
@XmlRootElement
public class Vets {

	private List<Vet> vets;

	@XmlElement
	public List<Vet> getVetList() {
		if (vets == null) {
			vets = new ArrayList<>();
		}
		return vets;
	}

}
```

---

</SwmSnippet>

# lazy initialization of the vets list

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="36:8:10" line-data="	public List&lt;Vet&gt; getVetList() {">`getVetList()`</SwmToken> method initializes the vets list only when it is first accessed. This avoids unnecessary object creation if the list is never used. It also guarantees that the method never returns null, simplifying client code that consumes this method by removing the need for null checks.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vets.java" line="33">

---

This pattern ensures the Vets object is always in a valid state with a usable list, which is important for serialization and general usage.

```java
	private List<Vet> vets;

	@XmlElement
	public List<Vet> getVetList() {
		if (vets == null) {
			vets = new ArrayList<>();
		}
		return vets;
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
