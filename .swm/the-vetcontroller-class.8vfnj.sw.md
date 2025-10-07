---
title: The VetController class
---
This document explains the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken> class. We will cover:

1. What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken>
2. Variables and functions in <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken>
3. Explanation of key functions: <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken> constructor, <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="45:5:5" line-data="	public String showVetList(@RequestParam(defaultValue = &quot;1&quot;) int page, Model model) {">`showVetList`</SwmToken>, <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="51:3:3" line-data="		return addPaginationModel(page, paginated, model);">`addPaginationModel`</SwmToken>, <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="49:10:10" line-data="		Page&lt;Vet&gt; paginated = findPaginated(page);">`findPaginated`</SwmToken>, <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="71:8:8" line-data="	public @ResponseBody Vets showResourcesVetList() {">`showResourcesVetList`</SwmToken>

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken> is a Spring MVC controller class responsible for handling web requests related to veterinarians in the application. It manages the retrieval and presentation of vet data, supporting both HTML views and RESTful JSON responses. It interacts with the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:5:5" line-data="	public VetController(VetRepository clinicService) {">`VetRepository`</SwmToken> to fetch vet data and prepares it for display or API consumption.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="40">

---

The constructor function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken> initializes the controller with a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:5:5" line-data="	public VetController(VetRepository clinicService) {">`VetRepository`</SwmToken> instance, which it uses to access vet data from the persistence layer. This repository is injected via constructor dependency injection.

```java
	public VetController(VetRepository clinicService) {
		this.vetRepository = clinicService;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="45:5:5" line-data="	public String showVetList(@RequestParam(defaultValue = &quot;1&quot;) int page, Model model) {">`showVetList`</SwmToken> handles GET requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="44:5:8" line-data="	@GetMapping(&quot;/vets.html&quot;)">`/vets.html`</SwmToken> and returns a paginated list of vets for display in an HTML view. It accepts a page number as a request parameter, fetches the corresponding page of vets, wraps them in a Vets container object, and delegates to <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="51:3:3" line-data="		return addPaginationModel(page, paginated, model);">`addPaginationModel`</SwmToken> to prepare the model attributes for the view.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="55">

---

The private function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="55:5:5" line-data="	private String addPaginationModel(int page, Page&lt;Vet&gt; paginated, Model model) {">`addPaginationModel`</SwmToken> adds pagination-related attributes to the Spring MVC model, such as the current page, total pages, total items, and the list of vets for the current page. It returns the logical view name <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="61:4:6" line-data="		return &quot;vets/vetList&quot;;">`vets/vetList`</SwmToken> to render the vet list page.

```java
	private String addPaginationModel(int page, Page<Vet> paginated, Model model) {
		List<Vet> listVets = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listVets", listVets);
		return "vets/vetList";
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="64">

---

The private function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="64:8:8" line-data="	private Page&lt;Vet&gt; findPaginated(int page) {">`findPaginated`</SwmToken> creates a Pageable object with a fixed page size of 5 and retrieves a Page of Vet entities from the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:5:5" line-data="	public VetController(VetRepository clinicService) {">`VetRepository`</SwmToken> for the requested page number. This supports pagination of vet data.

```java
	private Page<Vet> findPaginated(int page) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return vetRepository.findAll(pageable);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="70">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="71:8:8" line-data="	public @ResponseBody Vets showResourcesVetList() {">`showResourcesVetList`</SwmToken> handles GET requests to <SwmPath>[src/…/templates/vets/](src/main/resources/templates/vets/)</SwmPath> and returns a Vets object containing all vets as a JSON response. It is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="71:4:4" line-data="	public @ResponseBody Vets showResourcesVetList() {">`ResponseBody`</SwmToken> to indicate the return value should be serialized directly to the HTTP response body, supporting RESTful API clients.

```java
	@GetMapping({ "/vets" })
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetRepository.findAll());
		return vets;
	}
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken> is a Spring MVC controller class responsible for handling web requests related to veterinarians. It is annotated with @Controller, indicating that it serves as a web controller in the MVC pattern. The class has a dependency on <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:5:5" line-data="	public VetController(VetRepository clinicService) {">`VetRepository`</SwmToken>, which it receives through its constructor, enabling it to access veterinarian data.

The constructor of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken> takes a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:5:5" line-data="	public VetController(VetRepository clinicService) {">`VetRepository`</SwmToken> instance as a parameter and assigns it to a private final field. This setup allows <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository clinicService) {">`VetController`</SwmToken> to delegate data retrieval and persistence operations to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:5:5" line-data="	public VetController(VetRepository clinicService) {">`VetRepository`</SwmToken>, adhering to the principle of separation of concerns.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
