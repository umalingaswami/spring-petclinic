---
title: The PetValidator class
---
This document explains the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> class. It covers:

1. What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken>
2. Variables and functions

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> is a class in the owner package that implements the Validator interface to provide validation logic specifically for Pet objects. It is used to validate Pet form inputs in the application, ensuring that required fields such as name, type, and birth date are properly filled before processing or persisting the Pet data. This class opts for programmatic validation in Java rather than using Bean Validation annotations, allowing for more explicit and flexible validation rules.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="36">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="37:5:5" line-data="	public void validate(Object obj, Errors errors) {">`validate`</SwmToken> is the core method of <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken>. It takes an object and an Errors instance as parameters. It casts the object to a Pet and performs validation checks on the pet's name, type, and birth date. If the name is empty or missing, it registers an error for the "name" field. If the pet is new and its type is null, it registers an error for the "type" field. If the birth date is null, it registers an error for the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="52:6:6" line-data="			errors.rejectValue(&quot;birthDate&quot;, REQUIRED, REQUIRED);">`birthDate`</SwmToken> field. This method ensures that these essential fields are present and valid before the Pet object is accepted.

```java
	@Override
	public void validate(Object obj, Errors errors) {
		Pet pet = (Pet) obj;
		String name = pet.getName();
		// name validation
		if (!StringUtils.hasLength(name)) {
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

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="60:5:5" line-data="	public boolean supports(Class&lt;?&gt; clazz) {">`supports`</SwmToken> determines whether the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> can validate instances of a given class. It returns true if the class is assignable from Pet, meaning it supports validation of Pet objects or subclasses thereof. This method is used by the validation framework to select the appropriate validator for a given object.

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

The variable <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="34:9:9" line-data="	private static final String REQUIRED = &quot;required&quot;;">`REQUIRED`</SwmToken> is a private static final String constant set to "required". It is used as an error code and default message key when rejecting values in the validation process. This constant helps maintain consistency in error reporting for missing required fields.

```java
	private static final String REQUIRED = "required";
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> Usage in PetController

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> is registered as a validator for Pet objects within the PetController. This is done in the initPetBinder method, which is annotated with @InitBinder("pet"). This method sets the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> as the validator for the WebDataBinder, ensuring that any Pet object bound in the controller will be validated using <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken>.

## Validation Trigger on New Pet Creation

When handling requests to create a new pet (e.g., via the /pets/new endpoint), the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> is automatically applied to validate the Pet data submitted by the user. This integration helps maintain data integrity by enforcing validation rules before the Pet object is processed or persisted.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
