---
title: 'VisitController: Handling Pet Visit Creation and Form Submission'
---
# Introduction

This document explains how pet visit creation and form submission are handled in the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="39:2:2" line-data="class VisitController {">`VisitController`</SwmToken>. We will cover:

1. How the controller prepares the model with pet and visit data before handling requests.
2. How form initialization for a new visit is done.
3. How form submission is processed and validated.
4. Why certain fields are disallowed from binding during form submission.

# preparing pet and visit data before request handling

The method annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:2:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`ModelAttribute`</SwmToken>("visit") runs before every request handler in this controller. It loads the Owner and Pet entities based on the path variables <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:11:11" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`ownerId`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="56:6:6" line-data="	 * @param petId">`petId`</SwmToken>. Then it creates a new Visit instance and associates it with the Pet. This ensures that the form always works with a fresh Visit object linked to the correct Pet and Owner. The Pet and Owner are also added to the model for use in the view.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="52">

---

This approach avoids session scope and keeps the data consistent on each request. It also ensures the Visit has an id indirectly by being linked to the Pet, even though the id is not part of the form fields.

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

# initializing the new visit form

The GET handler for the URL pattern <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="75:5:20" line-data="	@GetMapping(&quot;/owners/{ownerId}/pets/{petId}/visits/new&quot;)">`/owners/{ownerId}/pets/{petId}/visits/new`</SwmToken> simply returns the view name for the visit creation form. Because of the @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:2:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`ModelAttribute`</SwmToken> method, the model already contains the Visit, Pet, and Owner objects needed to populate the form.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="73">

---

This separation keeps the GET handler minimal and delegates data preparation to the @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:2:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`ModelAttribute`</SwmToken> method.

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

# processing the visit form submission

The POST handler for the same URL receives the submitted Visit object, validated with @Valid. It also receives the Owner and <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="56:6:6" line-data="	 * @param petId">`petId`</SwmToken> from the path and model.

If validation errors occur, it returns the form view again to show errors. Otherwise, it adds the Visit to the Owner’s pet visits and saves the Owner entity, which cascades the visit persistence.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="80">

---

Finally, it redirects to the owner’s page to show the updated data.

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

# disallowing binding of the id field

The @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="47:2:2" line-data="	@InitBinder">`InitBinder`</SwmToken> method disables binding of the "id" field from HTTP parameters. This prevents clients from maliciously or accidentally setting the id of the Visit or other bound objects, which should be generated and managed by the system.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="47">

---

This is a security and data integrity measure to avoid tampering with entity identifiers.

```java
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
