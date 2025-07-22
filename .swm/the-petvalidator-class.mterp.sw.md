---
title: The PetValidator class
---
This document will cover the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> class in the Spring PetClinic project. We'll explore:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> is and its purpose.
2. The functions and variables defined within <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken>.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> is a class in the Spring PetClinic project that implements the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="20:8:8" line-data="import org.springframework.validation.Validator;">`Validator`</SwmToken> interface. It is specifically designed to validate <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="38:1:1" line-data="		Pet pet = (Pet) obj;">`Pet`</SwmToken> instances, ensuring that the data entered in pet forms is correct and complete. The class provides custom validation logic for pet attributes such as name, type, and birth date, rather than using Bean Validation annotations.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="36">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="37:5:5" line-data="	public void validate(Object obj, Errors errors) {">`validate`</SwmToken> is responsible for performing validation checks on a <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="38:1:1" line-data="		Pet pet = (Pet) obj;">`Pet`</SwmToken> object. It verifies that the pet's name is not empty, the type is specified for new pets, and the birth date is provided.

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

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="59">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="60:5:5" line-data="	public boolean supports(Class&lt;?&gt; clazz) {">`supports`</SwmToken> checks whether the given class is assignable from <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="61:3:3" line-data="		return Pet.class.isAssignableFrom(clazz);">`Pet`</SwmToken>. This ensures that the validator is only applied to instances of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="61:3:3" line-data="		return Pet.class.isAssignableFrom(clazz);">`Pet`</SwmToken>.

```java
	@Override
	public boolean supports(Class<?> clazz) {
		return Pet.class.isAssignableFrom(clazz);
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="34">

---

The constant <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="34:9:9" line-data="	private static final String REQUIRED = &quot;required&quot;;">`REQUIRED`</SwmToken> is used to define the error code for required fields during validation. It is a static final string with the value "required".

```java
	private static final String REQUIRED = "required";
```

---

</SwmSnippet>

# Usage

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetController.java" line="90">

---

In the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="45:2:2" line-data="class PetController {">`PetController`</SwmToken> class, the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> is utilized within the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="91:5:5" line-data="	public void initPetBinder(WebDataBinder dataBinder) {">`initPetBinder`</SwmToken> method. This method is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="90:1:7" line-data="	@InitBinder(&quot;pet&quot;)">`@InitBinder("pet")`</SwmToken>, indicating that it is used to initialize the data binding process for pet objects. The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> is set as the validator for the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="91:7:7" line-data="	public void initPetBinder(WebDataBinder dataBinder) {">`WebDataBinder`</SwmToken>, ensuring that pet data is validated according to the rules defined in the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetController.java" pos="92:7:7" line-data="		dataBinder.setValidator(new PetValidator());">`PetValidator`</SwmToken> class.

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
