---
title: 'PetController: Managing Pet Creation and Updates for Owners'
---
# introduction

This document explains how pet creation and update are managed for owners in the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="41:2:2" line-data="class PetController {">`PetController`</SwmToken>. The main questions answered here are:

1. How does the controller prepare data needed for pet forms?
2. How does it handle pet creation, including validation?
3. How does it handle pet updates?

# preparing data for pet forms

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="51">

---

The controller uses several @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="51:2:2" line-data="	@ModelAttribute(&quot;types&quot;)">`ModelAttribute`</SwmToken> methods to prepare data before handling requests. It loads the pet types available in the system, the owner based on the URL path variable, and the pet if a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="63:9:9" line-data="			@PathVariable(name = &quot;petId&quot;, required = false) Integer petId) {">`petId`</SwmToken> is provided. This ensures the form views have all necessary data without extra queries in the handler methods.

```java
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return this.owners.findPetTypes();
	}

	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") int ownerId) {
		return this.owners.findById(ownerId);
	}

	@ModelAttribute("pet")
	public Pet findPet(@PathVariable("ownerId") int ownerId,
			@PathVariable(name = "petId", required = false) Integer petId) {
		return petId == null ? new Pet() : this.owners.findById(ownerId).getPet(petId);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="67">

---

Additionally, the controller configures data binding for owner and pet objects. It disallows binding the owner's id field to prevent tampering and sets a custom validator for pets to enforce business rules.

```java
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("pet")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetValidator());
	}
```

---

</SwmSnippet>

# handling pet creation

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="77">

---

When a user wants to add a new pet, the GET handler initializes a new Pet instance, associates it with the owner, and puts it in the model for the form view.

```java
	@GetMapping("/pets/new")
	public String initCreationForm(Owner owner, ModelMap model) {
		Pet pet = new Pet();
		owner.addPet(pet);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="85">

---

The POST handler for creation performs a key validation step: it checks if the pet name already exists for that owner to avoid duplicates. If validation fails, it returns the form view with errors. Otherwise, it adds the pet to the owner, saves the owner (which cascades to the pet), and redirects to the owner's page.

```java
	@PostMapping("/pets/new")
	public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
		if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}

		owner.addPet(pet);
		if (result.hasErrors()) {
			model.put("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		this.owners.save(owner);
		return "redirect:/owners/{ownerId}";
	}
```

---

</SwmSnippet>

# handling pet updates

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="101">

---

For editing an existing pet, the GET handler fetches the pet from the owner and puts it in the model for the form.

```java
	@GetMapping("/pets/{petId}/edit")
	public String initUpdateForm(Owner owner, @PathVariable("petId") int petId, ModelMap model) {
		Pet pet = owner.getPet(petId);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="108">

---

The POST handler validates the updated pet. If errors exist, it returns the form view again. If not, it adds the pet to the owner (to update the collection), saves the owner, and redirects to the owner's page.

```java
	@PostMapping("/pets/{petId}/edit")
	public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, ModelMap model) {
		if (result.hasErrors()) {
			model.put("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		owner.addPet(pet);
		this.owners.save(owner);
		return "redirect:/owners/{ownerId}";
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
