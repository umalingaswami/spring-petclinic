---
title: The OwnerController class
---
This document covers the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:3:3" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerController`</SwmToken> class. We will explain:

1. What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:3:3" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerController`</SwmToken>
2. Variables and functions in <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:3:3" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerController`</SwmToken>

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:3:3" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:3:3" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerController`</SwmToken> is a Spring MVC controller class responsible for handling web requests related to the Owner entity in the application. It manages the creation, update, retrieval, and searching of Owner records. It interacts with the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerRepository`</SwmToken> to perform database operations and returns views or redirects to appropriate web pages for user interaction.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="51">

---

The constructor <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:3:3" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerController`</SwmToken> initializes the controller with an instance of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerRepository`</SwmToken>, which is used to perform CRUD operations on Owner entities.

```java
	public OwnerController(OwnerRepository clinicService) {
		this.owners = clinicService;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="55">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="56:5:5" line-data="	public void setAllowedFields(WebDataBinder dataBinder) {">`setAllowedFields`</SwmToken> is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="55:2:2" line-data="	@InitBinder">`InitBinder`</SwmToken> and configures the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="56:7:7" line-data="	public void setAllowedFields(WebDataBinder dataBinder) {">`WebDataBinder`</SwmToken> to disallow binding of the 'id' field. This prevents clients from modifying the id field of Owner objects during data binding.

```java
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="60">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="61:5:5" line-data="	public Owner findOwner(@PathVariable(name = &quot;ownerId&quot;, required = false) Integer ownerId) {">`findOwner`</SwmToken> is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="60:2:2" line-data="	@ModelAttribute(&quot;owner&quot;)">`ModelAttribute`</SwmToken> and retrieves an Owner by its id if provided in the path variable <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="61:15:15" line-data="	public Owner findOwner(@PathVariable(name = &quot;ownerId&quot;, required = false) Integer ownerId) {">`ownerId`</SwmToken>. If no id is provided, it returns a new Owner instance. This method prepares the Owner model attribute for request handling methods.

```java
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable(name = "ownerId", required = false) Integer ownerId) {
		return ownerId == null ? new Owner() : this.owners.findById(ownerId);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="65">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="66:5:5" line-data="	public String initCreationForm(Map&lt;String, Object&gt; model) {">`initCreationForm`</SwmToken> handles GET requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="65:5:8" line-data="	@GetMapping(&quot;/owners/new&quot;)">`/owners/new`</SwmToken>. It initializes a new Owner object, adds it to the model, and returns the view for the owner creation or update form.

```java
	@GetMapping("/owners/new")
	public String initCreationForm(Map<String, Object> model) {
		Owner owner = new Owner();
		model.put("owner", owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="72">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="73:5:5" line-data="	public String processCreationForm(@Valid Owner owner, BindingResult result) {">`processCreationForm`</SwmToken> handles POST requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="72:5:8" line-data="	@PostMapping(&quot;/owners/new&quot;)">`/owners/new`</SwmToken>. It validates the submitted Owner object, and if there are errors, it returns the creation form view again. Otherwise, it saves the new Owner and redirects to the details page of the created Owner.

```java
	@PostMapping("/owners/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}

		this.owners.save(owner);
		return "redirect:/owners/" + owner.getId();
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="82">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="83:5:5" line-data="	public String initFindForm(Map&lt;String, Object&gt; model) {">`initFindForm`</SwmToken> handles GET requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="82:5:8" line-data="	@GetMapping(&quot;/owners/find&quot;)">`/owners/find`</SwmToken>. It adds a new Owner object to the model and returns the view for finding owners.

```java
	@GetMapping("/owners/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("owner", new Owner());
		return "owners/findOwners";
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="88">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="89:5:5" line-data="	public String processFindForm(@RequestParam(defaultValue = &quot;1&quot;) int page, Owner owner, BindingResult result,">`processFindForm`</SwmToken> handles GET requests to <SwmPath>[src/…/templates/owners/](src/main/resources/templates/owners/)</SwmPath> with optional search parameters. It searches for owners by last name, supports pagination, and handles cases where no owners, one owner, or multiple owners are found. It returns appropriate views or redirects accordingly.

```java
	@GetMapping("/owners")
	public String processFindForm(@RequestParam(defaultValue = "1") int page, Owner owner, BindingResult result,
			Model model) {
		// allow parameterless GET request for /owners to return all records
		if (owner.getLastName() == null) {
			owner.setLastName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		Page<Owner> ownersResults = findPaginatedForOwnersLastName(page, owner.getLastName());
		if (ownersResults.isEmpty()) {
			// no owners found
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		}

		if (ownersResults.getTotalElements() == 1) {
			// 1 owner found
			owner = ownersResults.iterator().next();
			return "redirect:/owners/" + owner.getId();
		}

		// multiple owners found
		return addPaginationModel(page, model, ownersResults);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="114">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="114:5:5" line-data="	private String addPaginationModel(int page, Model model, Page&lt;Owner&gt; paginated) {">`addPaginationModel`</SwmToken> is a private helper that adds pagination-related attributes to the model for displaying a paginated list of owners. It sets the current page, total pages, total items, and the list of owners in the model, then returns the view for the owners list.

```java
	private String addPaginationModel(int page, Model model, Page<Owner> paginated) {
		model.addAttribute("listOwners", paginated);
		List<Owner> listOwners = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listOwners", listOwners);
		return "owners/ownersList";
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="124">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="124:8:8" line-data="	private Page&lt;Owner&gt; findPaginatedForOwnersLastName(int page, String lastname) {">`findPaginatedForOwnersLastName`</SwmToken> is a private helper that performs a paginated search for owners by last name. It creates a Pageable object with a fixed page size and queries the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerRepository`</SwmToken> for matching owners.

```java
	private Page<Owner> findPaginatedForOwnersLastName(int page, String lastname) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return owners.findByLastName(lastname, pageable);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="130">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="131:5:5" line-data="	public String initUpdateOwnerForm(@PathVariable(&quot;ownerId&quot;) int ownerId, Model model) {">`initUpdateOwnerForm`</SwmToken> handles GET requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="130:5:12" line-data="	@GetMapping(&quot;/owners/{ownerId}/edit&quot;)">`/owners/{ownerId}/edit`</SwmToken>. It retrieves the Owner by id, adds it to the model, and returns the view for the owner creation or update form to allow editing.

```java
	@GetMapping("/owners/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
		Owner owner = this.owners.findById(ownerId);
		model.addAttribute(owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="137">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="138:5:5" line-data="	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,">`processUpdateOwnerForm`</SwmToken> handles POST requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="137:5:12" line-data="	@PostMapping(&quot;/owners/{ownerId}/edit&quot;)">`/owners/{ownerId}/edit`</SwmToken>. It validates the submitted Owner object, and if there are errors, it returns the update form view again. Otherwise, it sets the Owner's id, saves the updated Owner, and redirects to the owner's details page.

```java
	@PostMapping("/owners/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
			@PathVariable("ownerId") int ownerId) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}

		owner.setId(ownerId);
		this.owners.save(owner);
		return "redirect:/owners/{ownerId}";
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="154">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="155:5:5" line-data="	public ModelAndView showOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`showOwner`</SwmToken> handles GET requests to '<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="154:5:9" line-data="	@GetMapping(&quot;/owners/{ownerId}&quot;)">`/owners/{ownerId`</SwmToken>}'. It retrieves the Owner by id, creates a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="155:3:3" line-data="	public ModelAndView showOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`ModelAndView`</SwmToken> for the owner details view, adds the Owner object to the model, and returns the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="155:3:3" line-data="	public ModelAndView showOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`ModelAndView`</SwmToken> for rendering.

```java
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Owner owner = this.owners.findById(ownerId);
		mav.addObject(owner);
		return mav;
	}
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:3:3" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:3:3" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerController`</SwmToken> is instantiated with an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerRepository`</SwmToken>, which it uses to manage owner-related data. This setup allows the controller to delegate data operations to the repository, maintaining a clean separation of concerns between web layer and data access.

## Usage Example

When creating an instance of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:3:3" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerController`</SwmToken>, the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="51:5:5" line-data="	public OwnerController(OwnerRepository clinicService) {">`OwnerRepository`</SwmToken> is passed as a constructor argument. This pattern is typical in Spring MVC applications to enable dependency injection, facilitating easier testing and modular design.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
