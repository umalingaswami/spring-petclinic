---
title: The PetController class
---
This document covers the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:3:3" line-data="	public PetController(OwnerRepository owners) {">`PetController`</SwmToken> class. We will explain:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:3:3" line-data="	public PetController(OwnerRepository owners) {">`PetController`</SwmToken> is and its purpose.
2. The variables and functions defined in <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:3:3" line-data="	public PetController(OwnerRepository owners) {">`PetController`</SwmToken>, including their roles and implementations.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:3:3" line-data="	public PetController(OwnerRepository owners) {">`PetController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:3:3" line-data="	public PetController(OwnerRepository owners) {">`PetController`</SwmToken> is a Spring MVC controller class responsible for handling web requests related to pets within the context of their owners. It manages the creation, update, and retrieval of pet data associated with a specific owner. The controller maps requests under the URL pattern '<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="40:4:8" line-data="@RequestMapping(&quot;/owners/{ownerId}&quot;)">`/owners/{ownerId`</SwmToken>}', facilitating operations such as initializing forms for creating or updating pets, processing form submissions, and populating model attributes needed for the views.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="47">

---

The constructor <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:3:3" line-data="	public PetController(OwnerRepository owners) {">`PetController`</SwmToken> initializes the controller with an instance of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:5:5" line-data="	public PetController(OwnerRepository owners) {">`OwnerRepository`</SwmToken>, which is used to access owner and pet data.

```java
	public PetController(OwnerRepository owners) {
		this.owners = owners;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="51">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="52:8:8" line-data="	public Collection&lt;PetType&gt; populatePetTypes() {">`populatePetTypes`</SwmToken> is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="51:2:2" line-data="	@ModelAttribute(&quot;types&quot;)">`ModelAttribute`</SwmToken>("types") and returns a collection of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="52:5:5" line-data="	public Collection&lt;PetType&gt; populatePetTypes() {">`PetType`</SwmToken> objects. It populates the model with all available pet types, which are retrieved from the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:5:5" line-data="	public PetController(OwnerRepository owners) {">`OwnerRepository`</SwmToken>. This data is used to populate selection options in pet-related forms.

```java
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return this.owners.findPetTypes();
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="56">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="57:5:5" line-data="	public Owner findOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`findOwner`</SwmToken> is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="56:2:2" line-data="	@ModelAttribute(&quot;owner&quot;)">`ModelAttribute`</SwmToken>("owner") and retrieves an Owner object based on the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="57:11:11" line-data="	public Owner findOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`ownerId`</SwmToken> path variable. It fetches the owner from the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:5:5" line-data="	public PetController(OwnerRepository owners) {">`OwnerRepository`</SwmToken> and makes it available in the model for request handling methods.

```java
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") int ownerId) {
		return this.owners.findById(ownerId);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="61">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="62:5:5" line-data="	public Pet findPet(@PathVariable(&quot;ownerId&quot;) int ownerId,">`findPet`</SwmToken> is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="61:2:2" line-data="	@ModelAttribute(&quot;pet&quot;)">`ModelAttribute`</SwmToken>("pet") and retrieves a Pet object based on the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="62:11:11" line-data="	public Pet findPet(@PathVariable(&quot;ownerId&quot;) int ownerId,">`ownerId`</SwmToken> and optionally <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="63:9:9" line-data="			@PathVariable(name = &quot;petId&quot;, required = false) Integer petId) {">`petId`</SwmToken> path variables. If <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="63:9:9" line-data="			@PathVariable(name = &quot;petId&quot;, required = false) Integer petId) {">`petId`</SwmToken> is null, it returns a new Pet instance; otherwise, it fetches the existing pet from the owner's collection. This method prepares the pet object for form binding.

```java
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

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="68:5:5" line-data="	public void initOwnerBinder(WebDataBinder dataBinder) {">`initOwnerBinder`</SwmToken> is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="67:2:2" line-data="	@InitBinder(&quot;owner&quot;)">`InitBinder`</SwmToken>("owner") and configures a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="68:7:7" line-data="	public void initOwnerBinder(WebDataBinder dataBinder) {">`WebDataBinder`</SwmToken> for the Owner object. It disallows binding of the 'id' field to prevent clients from modifying the owner's identifier during form submissions.

```java
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="72">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="73:5:5" line-data="	public void initPetBinder(WebDataBinder dataBinder) {">`initPetBinder`</SwmToken> is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="72:2:2" line-data="	@InitBinder(&quot;pet&quot;)">`InitBinder`</SwmToken>("pet") and configures a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="73:7:7" line-data="	public void initPetBinder(WebDataBinder dataBinder) {">`WebDataBinder`</SwmToken> for the Pet object. It sets a custom validator, <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="74:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken>, to perform validation logic on pet data during form submissions.

```java
	@InitBinder("pet")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetValidator());
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="77">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="78:5:5" line-data="	public String initCreationForm(Owner owner, ModelMap model) {">`initCreationForm`</SwmToken> handles GET requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="77:5:8" line-data="	@GetMapping(&quot;/pets/new&quot;)">`/pets/new`</SwmToken>. It initializes a new Pet instance, associates it with the given Owner, adds the pet to the model, and returns the view name for the pet creation or update form.

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

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="86:5:5" line-data="	public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {">`processCreationForm`</SwmToken> handles POST requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="85:5:8" line-data="	@PostMapping(&quot;/pets/new&quot;)">`/pets/new`</SwmToken>. It processes the submitted pet creation form, checks for duplicate pet names for the owner, validates the pet data, and if valid, saves the owner (and associated pet) to the repository. On success, it redirects to the owner's details page; otherwise, it returns the form view with validation errors.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="101">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="102:5:5" line-data="	public String initUpdateForm(Owner owner, @PathVariable(&quot;petId&quot;) int petId, ModelMap model) {">`initUpdateForm`</SwmToken> handles GET requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="101:5:12" line-data="	@GetMapping(&quot;/pets/{petId}/edit&quot;)">`/pets/{petId}/edit`</SwmToken>. It retrieves the existing Pet by <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="101:9:9" line-data="	@GetMapping(&quot;/pets/{petId}/edit&quot;)">`petId`</SwmToken> from the Owner, adds it to the model, and returns the view name for the pet creation or update form to allow editing.

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

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="109:5:5" line-data="	public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, ModelMap model) {">`processUpdateForm`</SwmToken> handles POST requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="108:5:12" line-data="	@PostMapping(&quot;/pets/{petId}/edit&quot;)">`/pets/{petId}/edit`</SwmToken>. It processes the submitted pet update form, validates the pet data, and if valid, updates the pet in the owner's collection and saves the owner. On success, it redirects to the owner's details page; otherwise, it returns the form view with validation errors.

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
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:3:3" line-data="	public PetController(OwnerRepository owners) {">`PetController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:3:3" line-data="	public PetController(OwnerRepository owners) {">`PetController`</SwmToken> is annotated as a Spring MVC Controller and is mapped to handle requests under the URL pattern '<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="40:4:8" line-data="@RequestMapping(&quot;/owners/{ownerId}&quot;)">`/owners/{ownerId`</SwmToken>}'. It manages operations related to pets for a specific owner identified by the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="57:11:11" line-data="	public Owner findOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`ownerId`</SwmToken> path variable. The controller uses an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:5:5" line-data="	public PetController(OwnerRepository owners) {">`OwnerRepository`</SwmToken> to access owner data, which is injected through its constructor. This setup allows <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="47:3:3" line-data="	public PetController(OwnerRepository owners) {">`PetController`</SwmToken> to serve views and handle form submissions related to creating or updating pet information.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
