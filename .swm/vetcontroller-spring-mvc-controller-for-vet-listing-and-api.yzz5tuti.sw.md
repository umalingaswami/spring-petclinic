---
title: 'VetController: Spring MVC Controller for Vet Listing and API'
---
# Introduction

This document explains the main design decisions behind the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="36:2:2" line-data="class VetController {">`VetController`</SwmToken> in the Spring Petclinic project. We will cover:

1. Why <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="36:2:2" line-data="class VetController {">`VetController`</SwmToken> returns a wrapper object instead of a raw list of vets.
2. How pagination is implemented for the vet listing page.
3. How the controller supports both HTML view rendering and JSON API responses.

# why use a wrapper object for vet collections

Instead of returning a raw list of Vet objects, the controller returns an instance of the Vets class. This applies both to the HTML view and the JSON API. The reason is to simplify object-to-XML or object-to-JSON mapping. Wrapping the list inside a dedicated container class makes serialization more straightforward and consistent.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

This design choice is visible in both the HTML handler and the API handler, where a Vets object is created and populated with Vet instances from the repository.

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

The controller supports paginated vet listings on the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="44:5:8" line-data="	@GetMapping(&quot;/vets.html&quot;)">`/vets.html`</SwmToken> endpoint. Pagination is implemented by:

- Accepting a page number as a request parameter, defaulting to 1.
- Using Spring Data's Pageable and <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="66:7:7" line-data="		Pageable pageable = PageRequest.of(page - 1, pageSize);">`PageRequest`</SwmToken> to fetch a page of vets from the repository.
- Setting a fixed page size of 5 vets per page.
- Adding pagination metadata (current page, total pages, total items) and the current page's vet list to the model.
- Returning the view name <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="61:4:6" line-data="		return &quot;vets/vetList&quot;;">`vets/vetList`</SwmToken> for rendering.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

This approach keeps the controller logic clean by delegating pagination details to helper methods.

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

<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="36:2:2" line-data="class VetController {">`VetController`</SwmToken> is annotated with @Controller and uses @<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="44:2:2" line-data="	@GetMapping(&quot;/vets.html&quot;)">`GetMapping`</SwmToken> to map requests. It handles two types of requests:

- <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="44:5:8" line-data="	@GetMapping(&quot;/vets.html&quot;)">`/vets.html`</SwmToken> returns an HTML page with paginated vets.
- <SwmPath>[src/…/templates/vets/](src/main/resources/templates/vets/)</SwmPath> returns a JSON response with all vets.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

The JSON endpoint uses @<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="71:4:4" line-data="	public @ResponseBody Vets showResourcesVetList() {">`ResponseBody`</SwmToken> to serialize the Vets wrapper object directly to JSON. This dual approach allows the same controller to serve both web UI and API clients without duplicating logic.

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
