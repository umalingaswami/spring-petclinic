---
title: 'VetController: Spring MVC Controller for Vet Management'
---
# Introduction

This document explains the main design decisions behind the vet management controller in the Spring Petclinic app. We will cover:

1. Why the controller returns a wrapper object <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:20:20" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vets`</SwmToken> instead of a raw list of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:33:33" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vet`</SwmToken>.
2. How pagination is implemented for the vet list view.
3. How the controller supports both HTML and JSON responses for vets.

# why use a wrapper object for vets

The controller methods return an instance of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:20:20" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vets`</SwmToken> class, which wraps a list of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:33:33" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vet`</SwmToken> objects. This is done to simplify object-to-XML and object-to-JSON mapping. Returning a raw collection can complicate serialization frameworks, so wrapping the list in a dedicated container makes the data structure explicit and easier to handle in views and REST clients.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

This design is visible in both the HTML and JSON endpoints, where the controller creates a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:20:20" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vets`</SwmToken> instance and populates it with vet data from the repository.

```java
	@GetMapping("/vets.html")
	public String showVetList(@RequestParam(defaultValue = "1") int page, Model model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		Page<Vet> paginated = findPaginated(page);
		vets.getVetList().addAll(paginated.toList());
		return addPaginationModel(page, paginated, model);

	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="70">

---

&nbsp;

```java
	@GetMapping({ "/vets" })
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetRepository.findAll());
		return vets;
	}

}
```

---

</SwmSnippet>

# pagination for the vet list view

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="44:5:8" line-data="	@GetMapping(&quot;/vets.html&quot;)">`/vets.html`</SwmToken> endpoint supports pagination to avoid loading all vets at once. The controller accepts a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="45:21:21" line-data="	public String showVetList(@RequestParam(defaultValue = &quot;1&quot;) int page, Model model) {">`page`</SwmToken> request parameter with a default of 1. It then calls a helper method to fetch a page of vets from the repository using Spring Data's <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="66:1:1" line-data="		Pageable pageable = PageRequest.of(page - 1, pageSize);">`Pageable`</SwmToken> abstraction.

The page size is fixed at 5 vets per page. The paginated result is unpacked and added to the model along with pagination metadata like current page, total pages, and total items. This metadata is used by the view template to render navigation controls.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

This approach keeps the UI responsive and avoids overwhelming the client with too much data.

```java
	@GetMapping("/vets.html")
	public String showVetList(@RequestParam(defaultValue = "1") int page, Model model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		Page<Vet> paginated = findPaginated(page);
		vets.getVetList().addAll(paginated.toList());
		return addPaginationModel(page, paginated, model);

	}

	private String addPaginationModel(int page, Page<Vet> paginated, Model model) {
		List<Vet> listVets = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listVets", listVets);
		return "vets/vetList";
	}

	private Page<Vet> findPaginated(int page) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return vetRepository.findAll(pageable);
	}
```

---

</SwmSnippet>

# supporting both HTML and JSON responses

The controller exposes two endpoints for vets:

- <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="44:5:8" line-data="	@GetMapping(&quot;/vets.html&quot;)">`/vets.html`</SwmToken> returns an HTML view with paginated vets.
- <SwmPath>[src/…/templates/vets/](src/main/resources/templates/vets/)</SwmPath> returns a JSON response with all vets.

The JSON endpoint is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="71:3:4" line-data="	public @ResponseBody Vets showResourcesVetList() {">`@ResponseBody`</SwmToken> to serialize the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:20:20" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vets`</SwmToken> wrapper directly to JSON. It returns all vets without pagination, which is suitable for API clients that want the full dataset.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

This dual approach allows the same controller to serve both web UI and REST clients efficiently.

```java
	@GetMapping("/vets.html")
	public String showVetList(@RequestParam(defaultValue = "1") int page, Model model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		Page<Vet> paginated = findPaginated(page);
		vets.getVetList().addAll(paginated.toList());
		return addPaginationModel(page, paginated, model);

	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="70">

---

&nbsp;

```java
	@GetMapping({ "/vets" })
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetRepository.findAll());
		return vets;
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
