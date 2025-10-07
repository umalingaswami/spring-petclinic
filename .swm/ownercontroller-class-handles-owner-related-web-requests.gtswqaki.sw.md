---
title: OwnerController Class - Handles Owner-related Web Requests
---
# Introduction

This document explains the main design decisions behind the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="45:2:2" line-data="class OwnerController {">`OwnerController`</SwmToken> class, which handles web requests related to Owner entities in the application.

We will cover:

1. How the controller manages creation and update forms for owners.
2. How owner search and pagination are implemented.
3. How owner details are displayed.

# managing owner creation and update

The controller uses a shared view for both creating and updating owners, referenced by a constant to avoid duplication and ease maintenance. When initializing the creation form, it simply puts a new Owner instance into the model for the form to bind to. The form submission is validated, and if errors exist, the form is redisplayed with error messages. Otherwise, the owner is saved and the user is redirected to the owner's details page.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="65">

---

Updating an owner follows a similar pattern but starts by loading the existing owner by ID and binding it to the form. On submission, the owner ID is explicitly set to ensure the correct entity is updated before saving. This approach keeps creation and update logic consistent and straightforward.

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

# searching owners with pagination

The controller supports searching owners by last name with pagination. If no last name is provided, it defaults to an empty string to perform a broad search. The search results are retrieved as a Page object, which encapsulates pagination details.

If no owners match, an error is registered and the search form is shown again. If exactly one owner matches, the user is redirected directly to that owner's details page. For multiple matches, the controller adds pagination attributes to the model (current page, total pages, total items, and the list of owners) and returns a view that displays the paginated list.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="88">

---

Pagination is implemented by creating a Pageable object with a fixed page size and passing it to the repository method that queries by last name.

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

# displaying owner details

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="149">

---

To display an owner's details, the controller handles a GET request with the owner ID as a path variable. It loads the owner from the repository and returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="155:3:3" line-data="	public ModelAndView showOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`ModelAndView`</SwmToken> pointing to the owner details view, with the owner object added to the model. This keeps the display logic simple and focused on retrieving and presenting the owner data.

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
