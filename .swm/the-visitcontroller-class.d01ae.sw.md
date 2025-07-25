---
title: The VisitController class
---
# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="46:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="46:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> class in <SwmPath>[src/…/owner/VisitController.java](src/main/java/org/springframework/samples/petclinic/owner/VisitController.java)</SwmPath> is a Spring MVC controller responsible for handling HTTP requests related to pet visits. It manages the creation and processing of new visit forms for pets owned by clinic clients.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="46">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="46:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> constructor initializes the controller with an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="46:5:5" line-data="	public VisitController(OwnerRepository owners) {">`OwnerRepository`</SwmToken> instance, which is used to interact with the database for owner-related operations.

```java
	public VisitController(OwnerRepository owners) {
		this.owners = owners;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="51">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="51:5:5" line-data="	public void setAllowedFields(WebDataBinder dataBinder) {">`setAllowedFields`</SwmToken> configures the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="51:7:7" line-data="	public void setAllowedFields(WebDataBinder dataBinder) {">`WebDataBinder`</SwmToken> to disallow binding of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="52:6:6" line-data="		dataBinder.setDisallowedFields(&quot;id&quot;);">`id`</SwmToken> field, ensuring that the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="52:6:6" line-data="		dataBinder.setDisallowedFields(&quot;id&quot;);">`id`</SwmToken> is not modified during form submissions.

```java
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="62">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="63:5:5" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`loadPetWithVisit`</SwmToken> is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="62:1:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`@ModelAttribute`</SwmToken> and is called before each request mapping method. It loads the pet and owner data, creates a new <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="63:3:3" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`Visit`</SwmToken> instance, and associates it with the pet.

```java
	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("ownerId") int ownerId, @PathVariable("petId") int petId,
			Map<String, Object> model) {
		Optional<Owner> optionalOwner = owners.findById(ownerId);
		Owner owner = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Owner not found with id: " + ownerId + ". Please ensure the ID is correct "));

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="81">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="81:5:5" line-data="	public String initNewVisitForm() {">`initNewVisitForm`</SwmToken> handles GET requests to initialize the form for creating a new visit. It returns the view name for the visit creation form.

```java
	public String initNewVisitForm() {
		return "pets/createOrUpdateVisitForm";
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="88">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="88:5:5" line-data="	public String processNewVisitForm(@ModelAttribute Owner owner, @PathVariable int petId, @Valid Visit visit,">`processNewVisitForm`</SwmToken> processes POST requests for submitting the new visit form. It validates the form data, adds the visit to the owner's pet, saves the owner, and redirects to the owner's page with a success message.

```java
	public String processNewVisitForm(@ModelAttribute Owner owner, @PathVariable int petId, @Valid Visit visit,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}

		owner.addVisit(petId, visit);
		this.owners.save(owner);
		redirectAttributes.addFlashAttribute("message", "Your visit has been booked");
		return "redirect:/owners/{ownerId}";
	}
```

---

</SwmSnippet>

# Usage

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
