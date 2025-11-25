---
title: PetValidator class for validating Pet objects
---
# introduction

This document explains the rationale and main points behind the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> class implementation. It answers:

1. Why is a custom validator used instead of standard Bean Validation annotations?
2. How does the validator enforce required fields on Pet objects?
3. How does the validator determine which objects it supports?

# why a custom validator

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="18">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> class implements Spring's Validator interface to validate Pet objects. The choice to avoid Bean Validation annotations is intentional because defining validation rules directly in Java code offers more flexibility and clarity for this domain. This approach lets the developer explicitly control validation logic rather than relying on declarative annotations, which can be less expressive for some rules.

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

# required field checks

The validate method enforces that certain Pet fields must be present:

- The pet's name must have text content.
- If the pet is new (not yet persisted), its type must be specified.
- The birth date must be provided.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="34">

---

If any of these checks fail, the method registers an error against the corresponding field using a standard "required" error code. This ensures that incomplete Pet objects are caught early before further processing.

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

# supported classes

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="56">

---

The supports method restricts this validator to only Pet instances or subclasses. This prevents it from being accidentally applied to unrelated objects, which could cause runtime errors or incorrect validation behavior.

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
