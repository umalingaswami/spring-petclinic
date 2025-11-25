---
title: PetValidator Class for Pet Form Validation
---
# Introduction

This document explains the rationale behind the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> class and its key implementation points. We will cover:

1. Why a custom validator is used instead of Bean Validation annotations.
2. How the validator enforces required fields on the Pet form.
3. How the validator restricts its applicability to Pet objects only.

# why a custom validator class

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="18">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> class implements Spring's Validator interface to handle validation logic for Pet forms. The choice to avoid Bean Validation annotations is intentional because defining validation rules directly in Java code offers more flexibility and clarity for this use case. This approach lets the developer explicitly control validation behavior without relying on annotations scattered across the domain model.

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

# required field validation

The validate method enforces that certain fields must be present for a Pet to be considered valid. It checks three main fields:

- name: must not be empty or null.
- type: must be set if the Pet is new (not yet persisted).
- <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="52:6:6" line-data="			errors.rejectValue(&quot;birthDate&quot;, REQUIRED, REQUIRED);">`birthDate`</SwmToken>: must not be null.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="34">

---

If any of these validations fail, an error is registered with a "required" error code. This ensures that the form cannot be submitted with missing critical information.

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

# restricting validation to Pet instances

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="56">

---

The supports method restricts this validator to only apply to Pet objects or subclasses thereof. This prevents accidental misuse of the validator on unrelated types and clarifies its intended scope.

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
