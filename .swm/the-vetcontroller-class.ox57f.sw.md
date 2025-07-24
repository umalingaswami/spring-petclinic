---
title: The VetController class
---
This document will cover the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository vetRepository) {">`VetController`</SwmToken> class in the Spring PetClinic project. We'll explore:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository vetRepository) {">`VetController`</SwmToken> is and its purpose.
2. Key variables and functions within <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository vetRepository) {">`VetController`</SwmToken>.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository vetRepository) {">`VetController`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository vetRepository) {">`VetController`</SwmToken> class is a Spring MVC controller in the PetClinic application responsible for handling requests related to veterinarians. It manages the display of veterinarian information and provides endpoints for accessing this data in different formats.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="40">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository vetRepository) {">`VetController`</SwmToken> is the constructor for the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository vetRepository) {">`VetController`</SwmToken> class. It initializes the controller with a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:5:5" line-data="	public VetController(VetRepository vetRepository) {">`VetRepository`</SwmToken> instance, which is used to access veterinarian data.

```java
	public VetController(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="44">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="45:5:5" line-data="	public String showVetList(@RequestParam(defaultValue = &quot;1&quot;) int page, Model model) {">`showVetList`</SwmToken> handles GET requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="44:5:8" line-data="	@GetMapping(&quot;/vets.html&quot;)">`/vets.html`</SwmToken>. It retrieves a paginated list of veterinarians and adds pagination details to the model for rendering in the view.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="54">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="54:5:5" line-data="	private String addPaginationModel(int page, Page&lt;Vet&gt; paginated, Model model) {">`addPaginationModel`</SwmToken> adds pagination attributes to the model, such as current page, total pages, total items, and the list of veterinarians, for display in the view.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="63">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="63:8:8" line-data="	private Page&lt;Vet&gt; findPaginated(int page) {">`findPaginated`</SwmToken> retrieves a paginated list of veterinarians from the repository based on the specified page number.

```java
	private Page<Vet> findPaginated(int page) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return vetRepository.findAll(pageable);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/vet/VetController.java" line="69">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="70:8:8" line-data="	public @ResponseBody Vets showResourcesVetList() {">`showResourcesVetList`</SwmToken> handles GET requests to <SwmPath>[src/…/templates/vets/](src/main/resources/templates/vets/)</SwmPath>. It returns a list of all veterinarians in JSON format, simplifying JSON/Object mapping.

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

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository vetRepository) {">`VetController`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository vetRepository) {">`VetController`</SwmToken> class is a Spring MVC controller responsible for handling requests related to veterinarians. It is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="35:0:1" line-data="@Controller">`@Controller`</SwmToken>, indicating that it is a web controller in the Spring framework. The class interacts with the <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:5:5" line-data="	public VetController(VetRepository vetRepository) {">`VetRepository`</SwmToken> to retrieve veterinarian data, which is then used to populate the model for the view layer.

## Constructor

The constructor of <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:3:3" line-data="	public VetController(VetRepository vetRepository) {">`VetController`</SwmToken> takes a <SwmToken path="src/main/java/org/springframework/samples/petclinic/vet/VetController.java" pos="40:5:5" line-data="	public VetController(VetRepository vetRepository) {">`VetRepository`</SwmToken> as a parameter, which is injected by Spring. This repository is used to access veterinarian data from the database, ensuring that the controller has the necessary data access capabilities.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
