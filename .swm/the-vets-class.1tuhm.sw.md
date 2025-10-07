---
title: The Vets class
---
This document will cover the class Vets. We will explain:

1. What is Vets
2. Variables and functions in Vets

# What is Vets

The class Vets is a simple domain object representing a collection of veterinarians. It is primarily used to facilitate XML marshalling and unmarshalling, especially in the context of the 'vets' <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="26:25:25" line-data=" * the &#39;vets&#39; {@link org.springframework.web.servlet.view.xml.MarshallingView}.">`MarshallingView`</SwmToken> in the Spring MVC framework. This class acts as a container for a list of Vet objects, enabling easy serialization and deserialization of vet data for web views or API responses.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vets.java" line="35">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="36:8:8" line-data="	public List&lt;Vet&gt; getVetList() {">`getVetList`</SwmToken> returns the list of Vet objects contained in the Vets instance. If the list has not been initialized yet, it initializes it as an empty <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="38:7:7" line-data="			vets = new ArrayList&lt;&gt;();">`ArrayList`</SwmToken> before returning it. This ensures that the returned list is never null, simplifying client code that consumes this method.

```java
	@XmlElement
	public List<Vet> getVetList() {
		if (vets == null) {
			vets = new ArrayList<>();
		}
		return vets;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/Vets.java" line="33">

---

The variable <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/Vets.java" pos="33:8:8" line-data="	private List&lt;Vet&gt; vets;">`vets`</SwmToken> is a private List of Vet objects that holds the veterinarians. It is the core data structure within the Vets class, storing all the Vet instances that this container manages.

```java
	private List<Vet> vets;
```

---

</SwmSnippet>

# Usage

## Vets Usage in VetController

The Vets class is used in the VetController to wrap collections of Vet objects. This wrapping simplifies the process of mapping these collections to XML or JSON formats when responding to web requests.

For example, in the method handling the '/vets.html' GET request, an instance of Vets is created and populated with a paginated list of Vet objects. This instance is then used to facilitate object-XML mapping, making it easier to handle the response data.

Similarly, in the method handling the <SwmPath>[src/…/templates/vets/](src/main/resources/templates/vets/)</SwmPath> GET request, the Vets object is populated with all Vet entities retrieved from the repository. This object is returned as a JSON response, again simplifying the mapping process.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
