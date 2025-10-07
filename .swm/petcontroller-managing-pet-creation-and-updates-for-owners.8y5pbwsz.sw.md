---
title: 'PetController: Managing Pet Creation and Updates for Owners'
---
# Introduction

This document explains the main design decisions behind managing pet creation and updates for owners in the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="41:2:2" line-data="class PetController {">`PetController`</SwmToken> class. We will cover:

1. How pet types and owners are loaded and made available to views.
2. How data binding and validation are configured for pets and owners.
3. How the controller handles the creation of new pets, including duplicate name checks.
4. How the controller manages updating existing pets.

# loading pet types and owners for views

The controller uses <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="51:1:2" line-data="	@ModelAttribute(&quot;types&quot;)">`@ModelAttribute`</SwmToken> methods to preload data needed by the views. The method that loads pet types fetches all available pet types from the repository, so the UI can show a dropdown or selection list when creating or editing a pet. Similarly, the owner is loaded by their ID from the URL path variable, making the owner object available in the model for all handler methods in this controller.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="51">

---

This approach centralizes data loading and avoids repeating code in each handler method. It also ensures that the model always contains the necessary context objects.

```java
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return this.owners.findPetTypes();
	}

	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") int ownerId) {
		return this.owners.findById(ownerId);
	}
```

---

</SwmSnippet>

# data binding and validation setup

Two <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="67:1:2" line-data="	@InitBinder(&quot;owner&quot;)">`@InitBinder`</SwmToken> methods configure how Spring binds HTTP request parameters to the owner and pet objects.

For the owner, the binder disallows binding to the "id" field. This prevents clients from changing the owner's database ID via form submissions, which is a security and data integrity measure.

For the pet, a custom validator is registered. This validator enforces business rules specific to pets, such as valid birth dates or name constraints, during form submission.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="67">

---

This setup ensures that only allowed fields are bound and that domain-specific validation is applied automatically.

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

# handling new pet creation

The controller provides two methods for creating a new pet: one to initialize the form and one to process the submitted data.

When initializing, a new Pet instance is created and added to the owner's collection. This prepares the form with an empty pet object linked to the owner.

When processing the form submission, the controller first checks if the pet's name is non-empty and if the owner already has a pet with the same name. If so, it rejects the value to prevent duplicate pet names for the same owner.

If validation errors exist, the form is redisplayed with error messages. Otherwise, the pet is added to the owner, and the owner is saved to persist the new pet.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="77">

---

This flow enforces uniqueness of pet names per owner and integrates validation results into the user experience.

```java
	@GetMapping("/pets/new")
	public String initCreationForm(Owner owner, ModelMap model) {
		Pet pet = new Pet();
		owner.addPet(pet);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

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

Updating a pet follows a similar pattern with two methods: one to show the edit form pre-populated with the existing pet data, and one to process the update submission.

The edit form method retrieves the pet by ID from the owner's collection and puts it in the model for the view.

The update processing method validates the pet object. If errors are found, it redisplays the form with errors. If valid, it adds the pet back to the owner (to update the collection) and saves the owner to persist changes.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="101">

---

This approach reuses the same form view for creation and update, simplifying the UI and controller logic.

```java
	@GetMapping("/pets/{petId}/edit")
	public String initUpdateForm(Owner owner, @PathVariable("petId") int petId, ModelMap model) {
		Pet pet = owner.getPet(petId);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

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
