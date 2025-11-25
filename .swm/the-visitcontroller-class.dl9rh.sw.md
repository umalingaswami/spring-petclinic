---
title: The VisitController class
---
This document explains the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> class. We will cover:

1. What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken>
2. Variables and functions in <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken>

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> is a Spring MVC controller class responsible for handling web requests related to visits of pets in the application. It manages the creation of new visits for pets owned by owners. The controller interacts with the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:5:5" line-data="	public VisitController(OwnerRepository owners) {">`OwnerRepository`</SwmToken> to retrieve owner and pet data, prepares model attributes for the views, and processes form submissions for new visits.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="43">

---

The constructor <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> initializes the controller with an instance of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:5:5" line-data="	public VisitController(OwnerRepository owners) {">`OwnerRepository`</SwmToken>, which is used to access owner and pet data from the database.

```java
	public VisitController(OwnerRepository owners) {
		this.owners = owners;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" line="47">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="48:5:5" line-data="	public void setAllowedFields(WebDataBinder dataBinder) {">`setAllowedFields`</SwmToken> is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="47:2:2" line-data="	@InitBinder">`InitBinder`</SwmToken> and configures the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="48:7:7" line-data="	public void setAllowedFields(WebDataBinder dataBinder) {">`WebDataBinder`</SwmToken> to disallow binding of the 'id' field. This prevents clients from modifying the 'id' property of objects during data binding.

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

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:5:5" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`loadPetWithVisit`</SwmToken> is annotated with @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="59:2:2" line-data="	@ModelAttribute(&quot;visit&quot;)">`ModelAttribute`</SwmToken>("visit") and is called before each @<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="53:14:14" line-data="	 * Called before each and every @RequestMapping annotated method. 2 goals: - Make sure">`RequestMapping`</SwmToken> method. It loads the Owner and Pet entities based on the path variables <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:11:11" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`ownerId`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:24:24" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`petId`</SwmToken>, adds them to the model, creates a new Visit instance, associates it with the Pet, and returns the Visit object. This ensures fresh data and that the Pet object has an id even though it is not part of the form fields.

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

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="76:5:5" line-data="	public String initNewVisitForm() {">`initNewVisitForm`</SwmToken> handles GET requests to the URL pattern for creating a new visit for a pet. It returns the view name <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="77:4:6" line-data="		return &quot;pets/createOrUpdateVisitForm&quot;;">`pets/createOrUpdateVisitForm`</SwmToken> to display the form for entering visit details. The method relies on <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="60:5:5" line-data="	public Visit loadPetWithVisit(@PathVariable(&quot;ownerId&quot;) int ownerId, @PathVariable(&quot;petId&quot;) int petId,">`loadPetWithVisit`</SwmToken> to prepare the model before it is called.

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

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="83:5:5" line-data="	public String processNewVisitForm(@ModelAttribute Owner owner, @PathVariable int petId, @Valid Visit visit,">`processNewVisitForm`</SwmToken> handles POST requests for submitting the new visit form. It takes the Owner model attribute, <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="82:15:15" line-data="	@PostMapping(&quot;/owners/{ownerId}/pets/{petId}/visits/new&quot;)">`petId`</SwmToken> path variable, and a validated Visit object. If there are validation errors, it returns the form view again. Otherwise, it adds the visit to the owner’s pet, saves the owner (which cascades the visit save), and redirects to the owner's details page.

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

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> is a Spring MVC controller class that manages web requests related to visits for pets. It is annotated with @Controller, indicating its role in handling HTTP requests and returning views. The class depends on an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:5:5" line-data="	public VisitController(OwnerRepository owners) {">`OwnerRepository`</SwmToken>, which it receives through constructor injection, allowing it to access owner-related data necessary for processing visit information.

## Constructor Usage

The constructor of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:3:3" line-data="	public VisitController(OwnerRepository owners) {">`VisitController`</SwmToken> takes an <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/VisitController.java" pos="43:5:5" line-data="	public VisitController(OwnerRepository owners) {">`OwnerRepository`</SwmToken> as a parameter and assigns it to a private final field. This setup ensures that the controller has access to owner data throughout its lifecycle, enabling it to retrieve and manipulate visit information associated with pet owners.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
