---
title: Vets Domain Object
---
# introduction

This document explains the design and purpose of the Vets domain object in the application. It answers these questions:

1. Why is there a dedicated Vets class instead of just using a list of Vet objects?
2. How does the Vets class support XML marshalling?
3. Why is the getter method implemented with lazy initialization?

# purpose of the Vets class

The Vets class wraps a list of Vet objects. This is not just a container; it exists primarily to support the XML marshalling process used in the application’s view layer. The class is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="30:0:1" line-data="@XmlRootElement">`@XmlRootElement`</SwmToken>, which marks it as the root element in the XML structure when converting Java objects to XML. This makes it compatible with Spring’s <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="26:25:25" line-data=" * the &#39;vets&#39; {@link org.springframework.web.servlet.view.xml.MarshallingView}.">`MarshallingView`</SwmToken> that renders the vets data as XML for clients that request it.

This design choice separates the domain model (Vet) from the representation model (Vets), allowing the framework to handle collections of vets cleanly in XML without extra boilerplate or custom adapters.

# lazy initialization of the vets list

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="36:8:10" line-data="	public List&lt;Vet&gt; getVetList() {">`getVetList()`</SwmToken> method returns the internal list of Vet objects. It uses lazy initialization: if the list is null, it creates a new empty <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="18:6:6" line-data="import java.util.ArrayList;">`ArrayList`</SwmToken> before returning it. This avoids null pointer exceptions when the list is accessed before being explicitly set.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vets.java" line="18">

---

This approach simplifies usage because callers can always assume the list is non-null and can add or iterate over it without extra null checks. It also keeps the internal state consistent and ready for marshalling or other operations.

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

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
