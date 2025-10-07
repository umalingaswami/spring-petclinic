---
title: 'VisitController: Handles Visit Creation and Form Processing for Pets'
---
# Introduction

This document explains the main design decisions behind the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> in the petclinic project. It answers these questions:

1. How does the controller ensure the Visit object is properly initialized and linked to the Pet and Owner?
2. How does the controller prevent unwanted binding of certain fields during form submission?
3. How are the visit creation form and its submission handled?

# initializing visit and linking to pet and owner

The controller uses a method annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:2:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`ModelAttribute`</SwmToken> to prepare a Visit object before any request handling method runs. This method:

- Loads the Owner by <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:11:11" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`ownerId`</SwmToken>.
- Retrieves the Pet by <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="56:6:6" line-data="	 * @param petId">`petId`</SwmToken> from the Owner.
- Adds the Pet and Owner to the model for view rendering.
- Creates a new Visit instance and associates it with the Pet.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="52">

---

This approach guarantees that every request dealing with visits has a fresh Visit object linked to the correct Pet and Owner, without relying on session state.

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

# preventing binding of the id field

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="47">

---

The controller uses an @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="47:2:2" line-data="	@InitBinder">`InitBinder`</SwmToken> method to disallow binding of the "id" field. This is important because the id should not be set or modified by form submissions, which could lead to security issues or data corruption. By explicitly disallowing "id", the controller ensures that only allowed fields are bound from the HTTP request.

```java
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
```

---

</SwmSnippet>

# handling visit creation form display and submission

Two methods handle the visit creation workflow:

- The GET method returns the view name for the visit creation form. It relies on the @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:2:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`ModelAttribute`</SwmToken> method to have already prepared the Visit object and model attributes.

- The POST method processes the submitted form. It validates the Visit object and checks for errors. If errors exist, it redisplays the form. Otherwise, it adds the Visit to the Owner's pet, saves the Owner (which cascades the visit save), and redirects to the owner's page.

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

The controller receives an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="41:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> instance via constructor injection. This repository is used to load and save Owner entities, which is central to managing visits since visits are linked through pets to owners.

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
