---
title: 'PetController: Managing Pet Creation and Updates for Owners'
---
# Introduction

This document explains how pet creation and update are managed for owners in the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="41:2:2" line-data="class PetController {">`PetController`</SwmToken> class. The main questions answered here are:

1. How does the controller prepare data needed for pet forms?
2. How does it handle pet creation, including validation and duplicate checks?
3. How does it handle pet updates and reuse the form logic?

# preparing data for pet forms

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="51">

---

The controller uses several @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="51:2:2" line-data="	@ModelAttribute(&quot;types&quot;)">`ModelAttribute`</SwmToken> methods to preload data needed by the views. It loads all pet types for dropdowns, the owner by <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="57:11:11" line-data="	public Owner findOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`ownerId`</SwmToken>, and either a new Pet or an existing Pet by <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="63:9:9" line-data="			@PathVariable(name = &quot;petId&quot;, required = false) Integer petId) {">`petId`</SwmToken>. This ensures the form has all necessary data bound before rendering or processing.

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

# binding and validation setup

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="67">

---

Two @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="67:2:2" line-data="	@InitBinder(&quot;owner&quot;)">`InitBinder`</SwmToken> methods configure data binding and validation. The owner binder disallows binding the id field to prevent tampering. The pet binder attaches a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="74:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> instance to enforce pet-specific validation rules during form submission.

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

The GET handler for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="77:5:8" line-data="	@GetMapping(&quot;/pets/new&quot;)">`/pets/new`</SwmToken> initializes a new Pet instance, associates it with the owner, and puts it in the model for the form view. This sets up the form for user input.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="77">

---

The POST handler for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="77:5:8" line-data="	@GetMapping(&quot;/pets/new&quot;)">`/pets/new`</SwmToken> processes the submitted form. It first checks if the pet name is non-empty and if a pet with the same name already exists for the owner, rejecting duplicates explicitly. If validation errors exist, it redisplays the form with errors. Otherwise, it adds the pet to the owner, saves the owner (which cascades to pets), and redirects to the owner's page.

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

The GET handler for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="101:5:12" line-data="	@GetMapping(&quot;/pets/{petId}/edit&quot;)">`/pets/{petId}/edit`</SwmToken> fetches the existing pet from the owner and puts it in the model for the same form view used in creation. This reuses the form for editing.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="101">

---

The POST handler for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="101:5:12" line-data="	@GetMapping(&quot;/pets/{petId}/edit&quot;)">`/pets/{petId}/edit`</SwmToken> validates the submitted pet. If errors exist, it redisplays the form. Otherwise, it adds the pet to the owner (to update the collection), saves the owner, and redirects to the owner's page.

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
