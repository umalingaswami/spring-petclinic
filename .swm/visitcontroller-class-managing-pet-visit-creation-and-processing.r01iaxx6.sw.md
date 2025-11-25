---
title: VisitController class managing pet visit creation and processing
---
# Introduction

This document explains the main design decisions behind the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> class, which manages creating and processing pet visits. We will cover:

1. How the controller ensures the Visit object is properly initialized and linked to the Pet and Owner.
2. How data binding is controlled to prevent unwanted field modifications.
3. How the controller handles the display and submission of the new visit form.

# initializing visit and loading pet and owner data

The controller uses a method annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:2:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`ModelAttribute`</SwmToken> to prepare the Visit object before any request handling method runs. This method:

- Loads the Owner by <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:11:11" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`ownerId`</SwmToken>.
- Retrieves the Pet by <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="56:6:6" line-data="	 * @param petId">`petId`</SwmToken> from the Owner.
- Adds both Owner and Pet to the model for view rendering.
- Creates a new Visit instance and associates it with the Pet.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="52">

---

This approach guarantees that every request dealing with visits has fresh, consistent domain objects ready, without relying on session state.

```java
	/**
	 * Called before each and every @RequestMapping annotated method. 2 goals: - Make sure
	 * we always have fresh data - Since we do not use the session scope, make sure that
	 * Pet object always has an id (Even though id is not part of the form fields)
	 * @param petId
	 * @return Pet
	 */
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

# restricting data binding for security

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="47">

---

The controller uses an @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="47:2:2" line-data="	@InitBinder">`InitBinder`</SwmToken> method to disallow binding of the "id" field. This prevents clients from maliciously or accidentally setting the Visit's id during form submission, which could lead to data integrity issues.

```java
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
```

---

</SwmSnippet>

# handling visit creation form display and submission

Two methods handle the new visit form lifecycle:

- The GET method returns the view name for the visit creation form. Because of the @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:2:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`ModelAttribute`</SwmToken> method, the model already contains the Visit, Pet, and Owner objects needed by the form.

- The POST method processes the submitted form. It validates the Visit object and, if errors exist, redisplays the form. Otherwise, it adds the Visit to the Owner's pet and saves the Owner, persisting the new Visit. Finally, it redirects to the Owner's detail page.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="73">

---

This separation keeps form initialization and processing clean and leverages Spring MVC's validation and binding features.

```java
	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is
	// called
	@GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String initNewVisitForm() {
		return "pets/createOrUpdateVisitForm";
	}

	// Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is
	// called
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

}
```

---

</SwmSnippet>

# constructor injection of repository

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="41">

---

The controller receives an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="41:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> instance via constructor injection. This repository is used to load and save Owner entities, which cascade changes to associated Pets and Visits. This design keeps persistence concerns encapsulated and testable.

```java
	private final OwnerRepository owners;

	public VisitController(OwnerRepository owners) {
		this.owners = owners;
	}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
