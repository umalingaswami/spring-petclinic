---
title: 'VetController: Spring MVC Controller for Vet Listing and API'
---
# Introduction

This document explains the key design choices and implementation details of the vet listing controller in the Spring Petclinic app. We will cover:

1. Why the controller returns a wrapper object <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:20:20" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vets`</SwmToken> instead of a raw list of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:33:33" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vet`</SwmToken>.
2. How pagination is implemented for the vet list page.
3. How the controller supports both HTML view rendering and JSON API responses.

# why use a wrapper object for vets

The controller methods return an instance of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:20:20" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vets`</SwmToken> rather than a plain list of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:33:33" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vet`</SwmToken>. This applies to both the HTML page and the JSON API endpoint. The reason is to simplify object-to-XML or object-to-JSON mapping frameworks, which often expect a root element or object. Wrapping the list inside a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:20:20" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vets`</SwmToken> object avoids issues with serializing raw collections and provides a consistent structure for clients.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

This design is visible in both the HTML handler and the API handler, where a new <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:20:20" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vets`</SwmToken> instance is created and populated with the list of vets from the repository.

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

# how pagination is handled for the vet list page

The HTML page that lists vets supports pagination to avoid loading all vets at once. The controller method <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="45:5:5" line-data="	public String showVetList(@RequestParam(defaultValue = &quot;1&quot;) int page, Model model) {">`showVetList`</SwmToken> accepts a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="45:21:21" line-data="	public String showVetList(@RequestParam(defaultValue = &quot;1&quot;) int page, Model model) {">`page`</SwmToken> parameter with a default of 1. It delegates to a helper method <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="49:10:10" line-data="		Page&lt;Vet&gt; paginated = findPaginated(page);">`findPaginated`</SwmToken> which creates a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="66:7:7" line-data="		Pageable pageable = PageRequest.of(page - 1, pageSize);">`PageRequest`</SwmToken> with a fixed page size (5 vets per page) and fetches the corresponding page from the repository.

The paginated result is then unpacked and added to the model along with pagination metadata like current page, total pages, and total items. This metadata is used by the view template to render pagination controls.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

This separation of concerns—fetching paginated data and preparing the model—is cleanly split into <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="49:10:10" line-data="		Page&lt;Vet&gt; paginated = findPaginated(page);">`findPaginated`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="51:3:3" line-data="		return addPaginationModel(page, paginated, model);">`addPaginationModel`</SwmToken> methods.

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

The controller is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="35:0:1" line-data="@Controller">`@Controller`</SwmToken> and uses Spring MVC annotations to map requests. It exposes two endpoints:

- <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="44:5:8" line-data="	@GetMapping(&quot;/vets.html&quot;)">`/vets.html`</SwmToken> returns an HTML view with paginated vets.
- <SwmPath>[src/…/templates/vets/](src/main/resources/templates/vets/)</SwmPath> returns a JSON response with all vets.

The JSON endpoint is marked with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="71:3:4" line-data="	public @ResponseBody Vets showResourcesVetList() {">`@ResponseBody`</SwmToken> so Spring serializes the returned <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="46:20:20" line-data="		// Here we are returning an object of type &#39;Vets&#39; rather than a collection of Vet">`Vets`</SwmToken> object directly to JSON. This dual approach allows the same controller to serve both UI and API clients without duplicating logic or repository calls.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

This design keeps the API lightweight and consistent with the UI data structure.

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
