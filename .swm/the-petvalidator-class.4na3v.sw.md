---
title: The PetValidator class
---
# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> class is a custom validator for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="38:1:1" line-data="		Pet pet = (Pet) obj;">`Pet`</SwmToken> forms in the Spring PetClinic application. It is designed to validate the properties of a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="38:1:1" line-data="		Pet pet = (Pet) obj;">`Pet`</SwmToken> object, ensuring that required fields are properly filled out. This class implements the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="57:5:5" line-data="	 * This Validator validates *just* Pet instances">`Validator`</SwmToken> interface from the Spring Framework, allowing it to be used in the validation process of web forms.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="36">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="37:5:5" line-data="	public void validate(Object obj, Errors errors) {">`validate`</SwmToken> is responsible for checking the validity of a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="38:1:1" line-data="		Pet pet = (Pet) obj;">`Pet`</SwmToken> object. It performs several checks: it ensures that the pet's name is not empty, verifies that the pet type is specified for new pets, and checks that the birth date is provided. If any of these validations fail, it uses the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="37:12:12" line-data="	public void validate(Object obj, Errors errors) {">`Errors`</SwmToken> object to reject the respective field with a 'required' error message.

```java
	@Override
	public void validate(Object obj, Errors errors) {
		Pet pet = (Pet) obj;
		String name = pet.getName();
		// name validation
		if (!StringUtils.hasText(name)) {
			errors.rejectValue("name", REQUIRED, REQUIRED);
		}

		// type validation
		if (pet.isNew() && pet.getType() == null) {
			errors.rejectValue("type", REQUIRED, REQUIRED);
		}

		// birth date validation
		if (pet.getBirthDate() == null) {
			errors.rejectValue("birthDate", REQUIRED, REQUIRED);
		}
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="56">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="60:5:5" line-data="	public boolean supports(Class&lt;?&gt; clazz) {">`supports`</SwmToken> determines whether the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> can validate instances of a given class. It returns `true` if the class is assignable from <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="57:13:13" line-data="	 * This Validator validates *just* Pet instances">`Pet`</SwmToken>, indicating that this validator is specifically designed for <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="57:13:13" line-data="	 * This Validator validates *just* Pet instances">`Pet`</SwmToken> objects.

```java
	/**
	 * This Validator validates *just* Pet instances
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Pet.class.isAssignableFrom(clazz);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="34">

---

The constant <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="34:9:9" line-data="	private static final String REQUIRED = &quot;required&quot;;">`REQUIRED`</SwmToken> is a string used to denote fields that are mandatory. It is utilized within the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="37:5:5" line-data="	public void validate(Object obj, Errors errors) {">`validate`</SwmToken> method to specify the error code when a required field is missing.

```java
	private static final String REQUIRED = "required";
```

---

</SwmSnippet>

# Usage

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="90">

---

In the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="45:2:2" line-data="class PetController {">`PetController`</SwmToken> class, the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> is utilized within the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="91:5:5" line-data="	public void initPetBinder(WebDataBinder dataBinder) {">`initPetBinder`</SwmToken> method. This method is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="90:1:7" line-data="	@InitBinder(&quot;pet&quot;)">`@InitBinder("pet")`</SwmToken>, indicating that it is used to initialize the data binding process for pet objects. The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="91:7:7" line-data="	public void initPetBinder(WebDataBinder dataBinder) {">`WebDataBinder`</SwmToken> is configured to use <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> as its validator, ensuring that any data bound to pet objects is validated according to the rules defined in <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken>.

```java
	@InitBinder("pet")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetValidator());
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
