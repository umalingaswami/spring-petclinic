---
title: Vets Domain Object
---
# introduction

This document explains the design and purpose of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="31:4:4" line-data="public class Vets {">`Vets`</SwmToken> domain object in the vets package. It answers:

1. Why is there a dedicated class for a list of veterinarians?
2. How does this class support XML marshalling?
3. Why is the getter implemented with lazy initialization?

# why a dedicated vets class

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vets.java" line="18">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="31:4:4" line-data="public class Vets {">`Vets`</SwmToken> class wraps a list of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="33:5:5" line-data="	private List&lt;Vet&gt; vets;">`Vet`</SwmToken> objects instead of using a raw list directly. This is important because it provides a domain-specific type that can be referenced in views and controllers. It also allows attaching annotations and behavior relevant to the collection as a whole, rather than individual vets. This design improves clarity and type safety in the codebase.

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

# xml marshalling support

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vets.java" line="18">

---

The class is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="30:0:1" line-data="@XmlRootElement">`@XmlRootElement`</SwmToken> and the getter with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="35:1:2" line-data="	@XmlElement">`@XmlElement`</SwmToken>. These JAXB annotations enable the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="31:4:4" line-data="public class Vets {">`Vets`</SwmToken> object to be marshalled into XML easily. This is specifically useful for the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="26:25:25" line-data=" * the &#39;vets&#39; {@link org.springframework.web.servlet.view.xml.MarshallingView}.">`MarshallingView`</SwmToken> in Spring MVC, which can convert the vets list into XML responses for clients that expect that format.

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

# lazy initialization of the vets list

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vets.java" line="33">

---

The getter method <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="36:8:10" line-data="	public List&lt;Vet&gt; getVetList() {">`getVetList()`</SwmToken> checks if the internal list is null and initializes it if needed. This lazy initialization avoids null pointer exceptions and ensures the list is always ready for use. It also keeps the internal state encapsulated and controlled, preventing external code from accidentally setting the list to null.

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
