---
title: 'OwnerController: Spring MVC Controller for Owner Management'
---
# Introduction

This document explains the key design decisions and implementation details of the Spring MVC controller responsible for managing owners in the application. We will cover:

1. How owner creation and update forms are initialized and processed.
2. How owner search and pagination are handled.
3. How owner details are displayed.

# owner creation and update handling

The controller uses a shared view for both creating and updating owners, referenced by a constant to avoid duplication and ease maintenance. The creation form is initialized by adding a new owner object to the model, which the form binds to.

When processing the creation form submission, the controller validates the owner object. If validation errors exist, it returns the form view again to show errors. Otherwise, it saves the owner and redirects to the owner's details page.

Similarly, updating an owner involves loading the existing owner by ID, populating the form, and then processing the submitted form with validation. The ID is explicitly set on the owner object before saving to ensure the correct entity is updated.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="65">

---

This approach keeps form handling consistent and leverages Spring's validation and data binding features.

```java
	@GetMapping("/owners/new")
	public String initCreationForm(Map<String, Object> model) {
		Owner owner = new Owner();
		model.put("owner", owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="130">

---

&nbsp;

```java
	@GetMapping("/owners/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
		Owner owner = this.owners.findById(ownerId);
		model.addAttribute(owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

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

# owner search and pagination

The controller supports searching owners by last name with pagination. If no last name is provided, it defaults to an empty string to perform a broad search.

The search results are retrieved as a paginated Page object. If no owners are found, a validation error is added to the form. If exactly one owner is found, the user is redirected directly to that owner's details page. For multiple results, the controller adds pagination metadata and the list of owners to the model, returning a view that displays the paginated list.

Pagination details such as current page, total pages, and total items are explicitly added to the model to support UI controls.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="88">

---

This design balances usability by handling zero, one, or many results appropriately and avoids overwhelming the user with too many results at once.

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

	private String addPaginationModel(int page, Model model, Page<Owner> paginated) {
		model.addAttribute("listOwners", paginated);
		List<Owner> listOwners = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listOwners", listOwners);
		return "owners/ownersList";
	}

	private Page<Owner> findPaginatedForOwnersLastName(int page, String lastname) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return owners.findByLastName(lastname, pageable);
	}
```

---

</SwmSnippet>

# owner details display

To show an individual owner's details, the controller uses a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="155:3:3" line-data="	public ModelAndView showOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`ModelAndView`</SwmToken> object. It loads the owner by ID and adds it to the model, returning the view responsible for rendering the owner's information.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="149">

---

This method cleanly separates the retrieval of data and the view rendering, making it straightforward to customize or extend.

```java
	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Owner owner = this.owners.findById(ownerId);
		mav.addObject(owner);
		return mav;
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
