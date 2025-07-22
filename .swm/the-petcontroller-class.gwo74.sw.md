---
title: The PetController class
---
This document will cover the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:3:3" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetController`</SwmToken> class in the Spring PetClinic project. We'll explore:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:3:3" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetController`</SwmToken> is and its purpose.
2. Key variables and functions within <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:3:3" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetController`</SwmToken>.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:3:3" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetController`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:3:3" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetController`</SwmToken> class in the Spring PetClinic project is responsible for handling HTTP requests related to pet management. It facilitates operations such as creating, updating, and retrieving pet information associated with a specific owner.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="53">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:3:3" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetController`</SwmToken> is the constructor of the class. It initializes the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:7:7" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`owners`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:12:12" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`types`</SwmToken> repositories, which are used to manage owner and pet type data.

```java
	public PetController(OwnerRepository owners, PetTypeRepository types) {
		this.owners = owners;
		this.types = types;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="58">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="59:8:8" line-data="	public Collection&lt;PetType&gt; populatePetTypes() {">`populatePetTypes`</SwmToken> retrieves a collection of pet types from the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="58:5:5" line-data="	@ModelAttribute(&quot;types&quot;)">`types`</SwmToken> repository, making them available for use in the application.

```java
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return this.types.findPetTypes();
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="63">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="64:5:5" line-data="	public Owner findOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`findOwner`</SwmToken> locates an owner by their ID using the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="65:12:12" line-data="		Optional&lt;Owner&gt; optionalOwner = this.owners.findById(ownerId);">`owners`</SwmToken> repository. If the owner is not found, it throws an exception.

```java
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") int ownerId) {
		Optional<Owner> optionalOwner = this.owners.findById(ownerId);
		Owner owner = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Owner not found with id: " + ownerId + ". Please ensure the ID is correct "));
		return owner;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="71">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="72:5:5" line-data="	public Pet findPet(@PathVariable(&quot;ownerId&quot;) int ownerId,">`findPet`</SwmToken> retrieves a pet by its ID from the specified owner. If the pet ID is not provided, it returns a new Pet instance.

```java
	@ModelAttribute("pet")
	public Pet findPet(@PathVariable("ownerId") int ownerId,
			@PathVariable(name = "petId", required = false) Integer petId) {

		if (petId == null) {
			return new Pet();
		}

		Optional<Owner> optionalOwner = this.owners.findById(ownerId);
		Owner owner = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Owner not found with id: " + ownerId + ". Please ensure the ID is correct "));
		return owner.getPet(petId);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="85">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="86:5:5" line-data="	public void initOwnerBinder(WebDataBinder dataBinder) {">`initOwnerBinder`</SwmToken> configures the data binder for owner objects, disallowing the 'id' field from being bound.

```java
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="90">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="91:5:5" line-data="	public void initPetBinder(WebDataBinder dataBinder) {">`initPetBinder`</SwmToken> sets a validator for pet objects to ensure they meet certain criteria before processing.

```java
	@InitBinder("pet")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetValidator());
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="95">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="96:5:5" line-data="	public String initCreationForm(Owner owner, ModelMap model) {">`initCreationForm`</SwmToken> initializes the form for creating a new pet, adding a new Pet instance to the owner.

```java
	@GetMapping("/pets/new")
	public String initCreationForm(Owner owner, ModelMap model) {
		Pet pet = new Pet();
		owner.addPet(pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="102">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="103:5:5" line-data="	public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result,">`processCreationForm`</SwmToken> processes the form submission for creating a new pet, validating the pet's data and saving it if valid.

```java
	@PostMapping("/pets/new")
	public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (StringUtils.hasText(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null)
			result.rejectValue("name", "duplicate", "already exists");

		LocalDate currentDate = LocalDate.now();
		if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(currentDate)) {
			result.rejectValue("birthDate", "typeMismatch.birthDate");
		}

		if (result.hasErrors()) {
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		owner.addPet(pet);
		this.owners.save(owner);
		redirectAttributes.addFlashAttribute("message", "New Pet has been Added");
		return "redirect:/owners/{ownerId}";
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="124">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="125:5:5" line-data="	public String initUpdateForm() {">`initUpdateForm`</SwmToken> initializes the form for updating an existing pet's details.

```java
	@GetMapping("/pets/{petId}/edit")
	public String initUpdateForm() {
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="129">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="130:5:5" line-data="	public String processUpdateForm(Owner owner, @Valid Pet pet, BindingResult result,">`processUpdateForm`</SwmToken> processes the form submission for updating a pet's details, validating the data and updating the pet if valid.

```java
	@PostMapping("/pets/{petId}/edit")
	public String processUpdateForm(Owner owner, @Valid Pet pet, BindingResult result,
			RedirectAttributes redirectAttributes) {

		String petName = pet.getName();

		// checking if the pet name already exists for the owner
		if (StringUtils.hasText(petName)) {
			Pet existingPet = owner.getPet(petName, false);
			if (existingPet != null && !existingPet.getId().equals(pet.getId())) {
				result.rejectValue("name", "duplicate", "already exists");
			}
		}

		LocalDate currentDate = LocalDate.now();
		if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(currentDate)) {
			result.rejectValue("birthDate", "typeMismatch.birthDate");
		}

		if (result.hasErrors()) {
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		updatePetDetails(owner, pet);
		redirectAttributes.addFlashAttribute("message", "Pet details has been edited");
		return "redirect:/owners/{ownerId}";
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="162">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="162:5:5" line-data="	private void updatePetDetails(Owner owner, Pet pet) {">`updatePetDetails`</SwmToken> updates the details of an existing pet or adds a new pet to the owner if it doesn't exist.

```java
	private void updatePetDetails(Owner owner, Pet pet) {
		Pet existingPet = owner.getPet(pet.getId());
		if (existingPet != null) {
			// Update existing pet's properties
			existingPet.setName(pet.getName());
			existingPet.setBirthDate(pet.getBirthDate());
			existingPet.setType(pet.getType());
		}
		else {
			owner.addPet(pet);
		}
		this.owners.save(owner);
	}
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:3:3" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetController`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:3:3" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetController`</SwmToken> class is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="43:0:1" line-data="@Controller">`@Controller`</SwmToken>, indicating that it is a Spring MVC controller responsible for handling web requests related to pets. It is mapped to handle requests with the URL pattern <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="44:4:9" line-data="@RequestMapping(&quot;/owners/{ownerId}&quot;)">`/owners/{ownerId}`</SwmToken> using the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="44:0:1" line-data="@RequestMapping(&quot;/owners/{ownerId}&quot;)">`@RequestMapping`</SwmToken> annotation. This setup allows the controller to manage pet-related operations for a specific owner identified by <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="64:11:11" line-data="	public Owner findOwner(@PathVariable(&quot;ownerId&quot;) int ownerId) {">`ownerId`</SwmToken>.

## Constructor Usage

The constructor of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:3:3" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetController`</SwmToken> takes two repositories as parameters: <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:5:5" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`OwnerRepository`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="53:10:10" line-data="	public PetController(OwnerRepository owners, PetTypeRepository types) {">`PetTypeRepository`</SwmToken>. These repositories are used to access and manipulate owner and pet type data, respectively. By injecting these dependencies, the controller can perform operations such as retrieving owner information and pet types, which are essential for creating or updating pet records.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
