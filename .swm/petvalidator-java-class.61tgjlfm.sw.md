---
title: PetValidator Java Class
---
# Introduction

This document explains the reasoning behind the implementation of the <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> class in <SwmPath>[src/…/owner/PetValidator.java](src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java)</SwmPath>. We will cover:

1. Why validation is done programmatically instead of using Bean Validation annotations.
2. How the validator checks the essential fields of a Pet object.
3. How the validator restricts itself to Pet instances only.

# why use a custom validator instead of bean validation annotations

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="18">

---

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="32:4:4" line-data="public class PetValidator implements Validator {">`PetValidator`</SwmToken> class implements Spring’s Validator interface to perform validation on Pet objects. The comment in the code clarifies that the choice to avoid Bean Validation annotations was made because defining validation rules directly in Java is simpler and more flexible for this case. This approach allows explicit control over validation logic without relying on annotation processing or external constraints.

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

# what fields are validated and how

The validate method enforces three key rules:

- The pet’s name must not be empty or null. This is checked using Spring’s <SwmToken path="src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" pos="41:5:7" line-data="		if (!StringUtils.hasLength(name)) {">`StringUtils.hasLength`</SwmToken> method. If the name is missing, an error is registered against the "name" field.
- If the pet is new (not yet persisted), its type must be specified. This prevents saving pets without a defined type.
- The birth date must be provided for all pets.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="34">

---

Each missing or invalid field triggers an error with the code "required", which can be used by the UI to display appropriate messages.

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

# how the validator restricts validation to pet instances

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/owner/PetValidator.java" line="56">

---

The supports method ensures that this validator only applies to Pet objects or subclasses. This prevents accidental use on unrelated types and keeps validation logic focused.

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
