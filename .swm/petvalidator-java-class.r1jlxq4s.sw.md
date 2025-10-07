---
title: PetValidator Java Class
---
# Introduction

This document explains the reasoning behind the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> class implementation. We will cover:

1. Why validation is done programmatically instead of using annotations.
2. How the validator checks the pet's name, type, and birth date.
3. How the validator ensures it only processes Pet instances.

# why use a custom validator instead of bean validation annotations

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="18">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> class opts for manual validation in Java rather than using Bean Validation annotations. This choice allows more control and flexibility over validation logic, making it easier to implement rules that might be cumbersome or impossible with annotations alone. It also keeps validation logic centralized and explicit.

```java
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * <code>Validator</code> for <code>Pet</code> forms.
 * <p>
 * We're not using Bean Validation annotations here because it is easier to define such
 * validation rule in Java.
 * </p>
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */
public class PetValidator implements Validator {
```

---

</SwmSnippet>

# how pet properties are validated

The validate method checks key Pet properties:

- Name: It must have length; empty or null names are rejected.
- Type: For new pets, the type must be specified.
- Birth date: It must not be null.

Each missing or invalid property triggers an error recorded in the Errors object, using a consistent "required" error code.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="34">

---

This approach ensures that essential pet data is present before proceeding with business logic or persistence.

```java
	private static final String REQUIRED = "required";

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

# how the validator restricts its target class

The supports method restricts this validator to only Pet instances or subclasses. This prevents accidental application to unrelated objects, which could cause runtime errors or incorrect validation behavior.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="56">

---

This method is a standard part of the Validator interface contract and ensures type safety.

```java
	/**
	 * This Validator validates *just* Pet instances
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Pet.class.isAssignableFrom(clazz);
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
