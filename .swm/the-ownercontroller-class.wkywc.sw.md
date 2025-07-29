---
title: The OwnerController class
---
# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="53:3:3" line-data="	public OwnerController(OwnerRepository owners) {">`OwnerController`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="53:3:3" line-data="	public OwnerController(OwnerRepository owners) {">`OwnerController`</SwmToken> class in <SwmPath>[src/…/owner/OwnerController.java](src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java)</SwmPath> is a Spring MVC controller responsible for handling HTTP requests related to owner entities in the PetClinic application. It facilitates operations such as creating, updating, finding, and displaying owner information.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="53">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="53:3:3" line-data="	public OwnerController(OwnerRepository owners) {">`OwnerController`</SwmToken> is the constructor of the class, which initializes the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="53:7:7" line-data="	public OwnerController(OwnerRepository owners) {">`owners`</SwmToken> field with an instance of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="53:5:5" line-data="	public OwnerController(OwnerRepository owners) {">`OwnerRepository`</SwmToken>. This repository is used to perform CRUD operations on owner data.

```java
	public OwnerController(OwnerRepository owners) {
		this.owners = owners;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="57">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="58:5:5" line-data="	public void setAllowedFields(WebDataBinder dataBinder) {">`setAllowedFields`</SwmToken> configures the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="58:7:7" line-data="	public void setAllowedFields(WebDataBinder dataBinder) {">`WebDataBinder`</SwmToken> to disallow binding of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="59:6:6" line-data="		dataBinder.setDisallowedFields(&quot;id&quot;);">`id`</SwmToken> field, preventing clients from modifying the owner's ID during form submissions.

```java
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="62">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="63:5:5" line-data="	public Owner findOwner(@PathVariable(name = &quot;ownerId&quot;, required = false) Integer ownerId) {">`findOwner`</SwmToken> retrieves an owner by their ID from the database. If the ID is not provided, it returns a new <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="63:3:3" line-data="	public Owner findOwner(@PathVariable(name = &quot;ownerId&quot;, required = false) Integer ownerId) {">`Owner`</SwmToken> instance. If the owner is not found, it throws an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="66:11:11" line-data="					.orElseThrow(() -&gt; new IllegalArgumentException(&quot;Owner not found with id: &quot; + ownerId">`IllegalArgumentException`</SwmToken>.

```java
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable(name = "ownerId", required = false) Integer ownerId) {
		return ownerId == null ? new Owner()
				: this.owners.findById(ownerId)
					.orElseThrow(() -> new IllegalArgumentException("Owner not found with id: " + ownerId
							+ ". Please ensure the ID is correct " + "and the owner exists in the database."));
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="70">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="71:5:5" line-data="	public String initCreationForm() {">`initCreationForm`</SwmToken> returns the view name for the owner creation form, allowing users to input new owner details.

```java
	@GetMapping("/owners/new")
	public String initCreationForm() {
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="75">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="76:5:5" line-data="	public String processCreationForm(@Valid Owner owner, BindingResult result, RedirectAttributes redirectAttributes) {">`processCreationForm`</SwmToken> handles the submission of the owner creation form. It validates the input, saves the owner if valid, and redirects to the owner's detail page.

```java
	@PostMapping("/owners/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "There was an error in creating the owner.");
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}

		this.owners.save(owner);
		redirectAttributes.addFlashAttribute("message", "New Owner Created");
		return "redirect:/owners/" + owner.getId();
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="87">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="88:5:5" line-data="	public String initFindForm() {">`initFindForm`</SwmToken> returns the view name for the owner search form, allowing users to search for owners by last name.

```java
	@GetMapping("/owners/find")
	public String initFindForm() {
		return "owners/findOwners";
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="92">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="93:5:5" line-data="	public String processFindForm(@RequestParam(defaultValue = &quot;1&quot;) int page, Owner owner, BindingResult result,">`processFindForm`</SwmToken> processes the owner search form submission, performs a search based on the last name, and handles the results by redirecting or displaying a list.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="118">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="118:5:5" line-data="	private String addPaginationModel(int page, Model model, Page&lt;Owner&gt; paginated) {">`addPaginationModel`</SwmToken> adds pagination attributes to the model for displaying a paginated list of owners.

```java
	private String addPaginationModel(int page, Model model, Page<Owner> paginated) {
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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="127">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="127:8:8" line-data="	private Page&lt;Owner&gt; findPaginatedForOwnersLastName(int page, String lastname) {">`findPaginatedForOwnersLastName`</SwmToken> retrieves a paginated list of owners whose last names start with the given string.

```java
	private Page<Owner> findPaginatedForOwnersLastName(int page, String lastname) {
		int pageSize = 7;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return owners.findByLastNameStartingWith(lastname, pageable);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="133">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="134:5:5" line-data="	public String initUpdateOwnerForm() {">`initUpdateOwnerForm`</SwmToken> returns the view name for the owner update form, allowing users to modify existing owner details.

```java
	@GetMapping("/owners/{ownerId}/edit")
	public String initUpdateOwnerForm() {
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="138">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="139:5:5" line-data="	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable(&quot;ownerId&quot;) int ownerId,">`processUpdateOwnerForm`</SwmToken> handles the submission of the owner update form, validates the input, updates the owner if valid, and redirects to the owner's detail page.

```java
	@PostMapping("/owners/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "There was an error in updating the owner.");
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}

		if (owner.getId() != ownerId) {
			result.rejectValue("id", "mismatch", "The owner ID in the form does not match the URL.");
			redirectAttributes.addFlashAttribute("error", "Owner ID mismatch. Please try again.");
			return "redirect:/owners/{ownerId}/edit";
		}

		owner.setId(ownerId);
		this.owners.save(owner);
		redirectAttributes.addFlashAttribute("message", "Owner Values Updated");
		return "redirect:/owners/{ownerId}";
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="163">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="164:5:5" line-data="	public ModelAndView showOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`showOwner`</SwmToken> displays the details of a specific owner by their ID, returning a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="164:3:3" line-data="	public ModelAndView showOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`ModelAndView`</SwmToken> with the owner's information.

```java
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Optional<Owner> optionalOwner = this.owners.findById(ownerId);
		Owner owner = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Owner not found with id: " + ownerId + ". Please ensure the ID is correct "));
		mav.addObject(owner);
		return mav;
	}
```

---

</SwmSnippet>

# Usage

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
