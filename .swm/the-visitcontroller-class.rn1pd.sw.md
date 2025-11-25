---
title: The VisitController class
---
This document explains the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> class. We will cover:

1. What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken>
2. Variables and functions in <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken>

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> is a Spring MVC controller class responsible for handling web requests related to visits of pets in the application. It manages the creation of new visits for pets owned by owners. The controller interacts with the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="41:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> to retrieve owner and pet data, prepares the model attributes needed for the visit forms, and processes form submissions to add new visits to pets.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="41">

---

The constructor <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> initializes the controller with an instance of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="41:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken>, which is used to access owner and pet data from the persistence layer. This repository is stored in the private final variable <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="41:7:7" line-data="	private final OwnerRepository owners;">`owners`</SwmToken>.

```java
	private final OwnerRepository owners;

	public VisitController(OwnerRepository owners) {
		this.owners = owners;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="47">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="48:5:5" line-data="	public void setAllowedFields(WebDataBinder dataBinder) {">`setAllowedFields`</SwmToken> is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="47:1:2" line-data="	@InitBinder">`@InitBinder`</SwmToken> and configures the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="48:7:7" line-data="	public void setAllowedFields(WebDataBinder dataBinder) {">`WebDataBinder`</SwmToken> to disallow binding of the 'id' field. This prevents clients from modifying the id property of domain objects during data binding, enhancing security.

```java
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="59">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:5:5" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`loadPetWithVisit`</SwmToken> is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:1:7" line-data="	@ModelAttribute(&quot;visit&quot;)">`@ModelAttribute("visit")`</SwmToken> and is called before each request handling method. It loads the Owner and Pet objects based on the path variables <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:11:11" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`ownerId`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:24:24" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`petId`</SwmToken>, adds them to the model, creates a new Visit instance, associates it with the Pet, and returns the Visit. This ensures fresh data and that the Pet object has an id even though it is not part of the form fields.

```java
	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId,
			Map<String, Object> model) {
		Owner owner = this.owners.findById(ownerId);

		Pet pet = owner.getPet(petId);
		model.put("pet", pet);
		model.put("owner", owner);

		Visit visit = new Visit();
		pet.addVisit(visit);
		return visit;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="75">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="76:5:5" line-data="	public String initNewVisitForm() {">`initNewVisitForm`</SwmToken> handles GET requests to the URL pattern <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="75:5:20" line-data="	@GetMapping(&quot;/owners/{ownerId}/pets/{petId}/visits/new&quot;)">`/owners/{ownerId}/pets/{petId}/visits/new`</SwmToken>. It returns the view name <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="77:4:6" line-data="		return &quot;pets/createOrUpdateVisitForm&quot;;">`pets/createOrUpdateVisitForm`</SwmToken> to display the form for creating a new visit. The method relies on <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:5:5" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`loadPetWithVisit`</SwmToken> to prepare the model before it is called.

```java
	@GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String initNewVisitForm() {
		return "pets/createOrUpdateVisitForm";
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="82">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="83:5:5" line-data="	public String processNewVisitForm(@ModelAttribute Owner owner, @PathVariable int petId, @Valid Visit visit,">`processNewVisitForm`</SwmToken> handles POST requests to the URL pattern <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="82:5:20" line-data="	@PostMapping(&quot;/owners/{ownerId}/pets/{petId}/visits/new&quot;)">`/owners/{ownerId}/pets/{petId}/visits/new`</SwmToken>. It receives the Owner object, <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="82:15:15" line-data="	@PostMapping(&quot;/owners/{ownerId}/pets/{petId}/visits/new&quot;)">`petId`</SwmToken>, and a validated Visit object along with binding results. If there are validation errors, it returns the visit form view again. Otherwise, it adds the new visit to the owner’s pet, saves the owner using the repository, and redirects to the owner's details page.

```java
	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String processNewVisitForm(@ModelAttribute Owner owner, @PathVariable int petId, @Valid Visit visit,
			BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}

		owner.addVisit(petId, visit);
		this.owners.save(owner);
		return "redirect:/owners/{ownerId}";
	}
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> is instantiated with an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="41:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken>, which it uses to manage owner-related data. This setup indicates that <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> handles operations related to visits by interacting with owner data through the repository. The constructor injection of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="41:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> ensures that <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> has access to owner information necessary for its functionality.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
