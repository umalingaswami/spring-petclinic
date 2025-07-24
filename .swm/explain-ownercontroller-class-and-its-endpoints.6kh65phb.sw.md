---
title: explain ownercontroller class and its endpoints
---
The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="47:2:2" line-data="class OwnerController {" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`OwnerController`</SwmToken> class in the Spring PetClinic application is responsible for managing HTTP requests related to owner entities. It facilitates operations such as creating, updating, finding, and displaying owner information. Let's explore its endpoints and functionalities.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="68" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="71:5:5" line-data="	public String initCreationForm() {" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`initCreationForm`</SwmToken> function handles GET requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="70:5:8" line-data="	@GetMapping(&quot;/owners/new&quot;)" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`/owners/new`</SwmToken>, returning the view for the owner creation form.

```java
	}

	@GetMapping("/owners/new")
	public String initCreationForm() {
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="85" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="76:5:5" line-data="	public String processCreationForm(@Valid Owner owner, BindingResult result, RedirectAttributes redirectAttributes) {" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`processCreationForm`</SwmToken> function handles POST requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="70:5:8" line-data="	@GetMapping(&quot;/owners/new&quot;)" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`/owners/new`</SwmToken>, validating and saving the owner data, then redirecting to the owner's detail page.

```java
	}

	@GetMapping("/owners/find")
	public String initFindForm() {
		return "owners/findOwners";
	}

	@GetMapping("/owners")
	public String processFindForm(@RequestParam(defaultValue = "1") int page, Owner owner, BindingResult result,
			Model model) {
		// allow parameterless GET request for /owners to return all records
```

---

</SwmSnippet>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="47:2:2" line-data="class OwnerController {" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`OwnerController`</SwmToken> class handles owner updates primarily through the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="139:5:5" line-data="	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable(&quot;ownerId&quot;) int ownerId," repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`processUpdateOwnerForm`</SwmToken> function. This function manages the submission of the update form, validates the input, and updates the owner details if valid.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="139" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="139:5:5" line-data="	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable(&quot;ownerId&quot;) int ownerId," repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`processUpdateOwnerForm`</SwmToken> handles POST requests to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="133:5:12" line-data="	@GetMapping(&quot;/owners/{ownerId}/edit&quot;)" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`/owners/{ownerId}/edit`</SwmToken>, validates the input, updates the owner if valid, and redirects to the owner's detail page.

```java
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "There was an error in updating the owner.");
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}

		if (owner.getId() != ownerId) {
			result.rejectValue("id", "mismatch", "The owner ID in the form does not match the URL.");
			redirectAttributes.addFlashAttribute("error", "Owner ID mismatch. Please try again.");
			return "redirect:/owners/{ownerId}/edit";
		}

		owner.setId(ownerId);
		this.owners.save(owner);
		redirectAttributes.addFlashAttribute("message", "Owner Values Updated");
		return "redirect:/owners/{ownerId}";
	}

	/**
	 * Custom handler for displaying an owner.
```

---

</SwmSnippet>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="47:2:2" line-data="class OwnerController {" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`OwnerController`</SwmToken> validates owner updates using the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="139:5:5" line-data="	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable(&quot;ownerId&quot;) int ownerId," repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`processUpdateOwnerForm`</SwmToken> method. Let's explore how validation is handled in this method.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" line="139" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="139:5:5" line-data="	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable(&quot;ownerId&quot;) int ownerId," repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`processUpdateOwnerForm`</SwmToken> method uses the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="139:7:8" line-data="	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable(&quot;ownerId&quot;) int ownerId," repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`@Valid`</SwmToken> annotation to trigger validation on the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="139:10:10" line-data="	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable(&quot;ownerId&quot;) int ownerId," repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`Owner`</SwmToken> object. If validation errors are present, they are captured in the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="139:15:15" line-data="	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable(&quot;ownerId&quot;) int ownerId," repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`BindingResult`</SwmToken> object, and appropriate error handling is performed.

```java
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "There was an error in updating the owner.");
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}

		if (owner.getId() != ownerId) {
			result.rejectValue("id", "mismatch", "The owner ID in the form does not match the URL.");
			redirectAttributes.addFlashAttribute("error", "Owner ID mismatch. Please try again.");
			return "redirect:/owners/{ownerId}/edit";
		}

		owner.setId(ownerId);
		this.owners.save(owner);
		redirectAttributes.addFlashAttribute("message", "Owner Values Updated");
		return "redirect:/owners/{ownerId}";
	}
```

---

</SwmSnippet>

<SwmLink doc-title="The OwnerController class" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic" path="/.swm/the-ownercontroller-class.wkywc.sw.md">[The OwnerController class](https://app.swimm.io/repos/Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk%3D/docs/wkywc)</SwmLink>:\
The method checks for validation errors using <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="141:4:8" line-data="		if (result.hasErrors()) {" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`result.hasErrors()`</SwmToken>. If errors exist, it adds an error message to <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java" pos="140:1:1" line-data="			RedirectAttributes redirectAttributes) {" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic">`RedirectAttributes`</SwmToken> and returns the form view for corrections.

<SwmMeta version="3.0.0"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
