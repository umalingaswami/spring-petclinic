---
title: 'VisitController: Handling Pet Visit Creation and Form Submission'
---
# Introduction

This document explains the main design decisions behind handling pet visit creation and form submission in the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken>. We will cover:

1. How the controller prepares the model with pet and visit data before handling requests.
2. Why certain fields are disallowed from binding in form submissions.
3. How the controller initializes the form for creating a new visit.
4. How the controller processes the submitted visit form and persists the data.

# preparing model attributes before request handling

The method annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:2:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`ModelAttribute`</SwmToken>("visit") is called before every request handler in this controller. It loads the Owner and Pet entities based on the path variables <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:11:11" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`ownerId`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="56:6:6" line-data="	 * @param petId">`petId`</SwmToken>. It then creates a new Visit instance and associates it with the Pet. This ensures that the model always contains fresh and consistent data for the visit form, including the pet and owner objects needed for rendering the view or processing the form.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="52">

---

This approach avoids relying on session state and guarantees that the Pet object always has its id set, even though the id is not part of the form fields submitted by the user. This is important for correctly linking the new Visit to the right Pet and Owner.

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

# restricting data binding for security and integrity

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="47">

---

The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="47:2:2" line-data="	@InitBinder">`InitBinder`</SwmToken> method disables binding of the "id" field from HTTP parameters. This prevents clients from maliciously or accidentally overriding the id of entities during form submission. By disallowing the id field, the controller ensures that only server-side logic controls entity identity, preserving data integrity.

```java
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
```

---

</SwmSnippet>

# initializing the new visit form

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="73">

---

When a GET request is made to the URL for creating a new visit, the controller returns the view name for the visit creation form. Because of the @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:2:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`ModelAttribute`</SwmToken> method, the model is already populated with a new Visit instance linked to the correct Pet and Owner. This separation keeps the GET handler simple and focused on returning the form view.

```java
	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is
	// called
	@GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String initNewVisitForm() {
		return "pets/createOrUpdateVisitForm";
	}
```

---

</SwmSnippet>

# processing the submitted visit form

The POST handler receives the submitted Visit object, validated with @Valid, along with the Owner and <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="56:6:6" line-data="	 * @param petId">`petId`</SwmToken>. If validation errors exist, it returns the form view again to show errors. Otherwise, it adds the new Visit to the Owner's pet and saves the Owner entity, which cascades the persistence of the Visit.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="80">

---

This design leverages the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="41:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> to save the entire aggregate, ensuring consistency. The redirect after successful processing sends the user back to the owner's details page.

```java
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

The controller receives the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="41:5:5" line-data="	private final OwnerRepository owners;">`OwnerRepository`</SwmToken> via constructor injection, which is a standard Spring practice for dependency management and testability.

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
