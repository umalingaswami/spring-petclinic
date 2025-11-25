---
title: Thymeleaf Template for Creating or Updating a Pet
---
# introduction

This document explains the design and implementation of the Thymeleaf template used for creating or updating a pet in the UI.

We will cover:

1. How the template handles both creation and update scenarios.
2. How form data binding and input fields are structured.
3. How the template integrates with layout fragments and dynamic content.

# handling creation and update in one template

<SwmSnippet path="/src/main/resources/templates/pets/createOrUpdatePetForm.html" line="1">

---

The template uses a single form to handle both creating a new pet and updating an existing one. This is done by checking the <SwmToken path="src/main/resources/templates/pets/createOrUpdatePetForm.html" pos="7:11:19" line-data="    &lt;th:block th:if=&quot;${pet[&#39;new&#39;]}&quot;&gt;New &lt;/th:block&gt;">`${pet['new']}`</SwmToken> flag. If true, it shows "New Pet" as the heading and changes the submit button text to "Add Pet". Otherwise, it shows "Pet" and the button text becomes "Update Pet". This avoids duplicating templates for similar forms and keeps the UI consistent.

```html
<html xmlns:th="https://www.thymeleaf.org"
  th:replace="~{fragments/layout :: layout (~{::body},'owners')}">

<body>

  <h2>
    <th:block th:if="${pet['new']}">New </th:block>
    Pet
  </h2>
  <form th:object="${pet}" class="form-horizontal" method="post">
    <input type="hidden" name="id" th:value="*{id}" />
    <div class="form-group has-feedback">
      <div class="form-group">
        <label class="col-sm-2 control-label">Owner</label>
        <div class="col-sm-10">
          <span th:text="${owner?.firstName + ' ' + owner?.lastName}" />
        </div>
      </div>
      <input
        th:replace="~{fragments/inputField :: input ('Name', 'name', 'text')}" />
      <input
        th:replace="~{fragments/inputField :: input ('Birth Date', 'birthDate', 'date')}" />
      <input
        th:replace="~{fragments/selectField :: select ('Type', 'type', ${types})}" />
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button
          th:with="text=${pet['new']} ? 'Add Pet' : 'Update Pet'"
          class="btn btn-primary" type="submit" th:text="${text}">Add
```

---

</SwmSnippet>

# form data binding and input fields

The form binds to the <SwmToken path="src/main/resources/templates/pets/createOrUpdatePetForm.html" pos="7:13:13" line-data="    &lt;th:block th:if=&quot;${pet[&#39;new&#39;]}&quot;&gt;New &lt;/th:block&gt;">`pet`</SwmToken> object using <SwmToken path="src/main/resources/templates/pets/createOrUpdatePetForm.html" pos="10:4:13" line-data="  &lt;form th:object=&quot;${pet}&quot; class=&quot;form-horizontal&quot; method=&quot;post&quot;&gt;">`th:object="${pet}"`</SwmToken>. This enables automatic population of form fields with the pet's current data and easy binding on submission.

The form includes:

- A hidden input for the pet's <SwmToken path="src/main/resources/templates/pets/createOrUpdatePetForm.html" pos="11:13:13" line-data="    &lt;input type=&quot;hidden&quot; name=&quot;id&quot; th:value=&quot;*{id}&quot; /&gt;">`id`</SwmToken> to maintain identity during updates.
- A display-only field showing the owner's full name, which is not editable here.
- Input fields for the pet's name and birth date, included via reusable fragments for consistency and maintainability.
- A select dropdown for the pet's type, also included via a fragment and populated dynamically from <SwmToken path="src/main/resources/templates/pets/createOrUpdatePetForm.html" pos="24:27:30" line-data="        th:replace=&quot;~{fragments/selectField :: select (&#39;Type&#39;, &#39;type&#39;, ${types})}&quot; /&gt;">`${types}`</SwmToken>.

<SwmSnippet path="/src/main/resources/templates/pets/createOrUpdatePetForm.html" line="1">

---

This modular approach using fragments reduces duplication and centralizes input field markup.

```html
<html xmlns:th="https://www.thymeleaf.org"
  th:replace="~{fragments/layout :: layout (~{::body},'owners')}">

<body>

  <h2>
    <th:block th:if="${pet['new']}">New </th:block>
    Pet
  </h2>
  <form th:object="${pet}" class="form-horizontal" method="post">
    <input type="hidden" name="id" th:value="*{id}" />
    <div class="form-group has-feedback">
      <div class="form-group">
        <label class="col-sm-2 control-label">Owner</label>
        <div class="col-sm-10">
          <span th:text="${owner?.firstName + ' ' + owner?.lastName}" />
        </div>
      </div>
      <input
        th:replace="~{fragments/inputField :: input ('Name', 'name', 'text')}" />
      <input
        th:replace="~{fragments/inputField :: input ('Birth Date', 'birthDate', 'date')}" />
      <input
        th:replace="~{fragments/selectField :: select ('Type', 'type', ${types})}" />
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button
          th:with="text=${pet['new']} ? 'Add Pet' : 'Update Pet'"
          class="btn btn-primary" type="submit" th:text="${text}">Add
```

---

</SwmSnippet>

# layout integration and form structure

The template extends a common layout fragment with <SwmToken path="src/main/resources/templates/pets/createOrUpdatePetForm.html" pos="2:1:28" line-data="  th:replace=&quot;~{fragments/layout :: layout (~{::body},&#39;owners&#39;)}&quot;&gt;">`th:replace="~{fragments/layout :: layout (~{::body},'owners')}"`</SwmToken>. This means the form is rendered within the standard page layout, inheriting header, footer, and styling.

The form uses Bootstrap classes (<SwmToken path="src/main/resources/templates/pets/createOrUpdatePetForm.html" pos="10:18:20" line-data="  &lt;form th:object=&quot;${pet}&quot; class=&quot;form-horizontal&quot; method=&quot;post&quot;&gt;">`form-horizontal`</SwmToken>, <SwmToken path="src/main/resources/templates/pets/createOrUpdatePetForm.html" pos="12:7:9" line-data="    &lt;div class=&quot;form-group has-feedback&quot;&gt;">`form-group`</SwmToken>, etc.) for styling and alignment. The submit button is placed inside a form group with offset styling to align it properly.

<SwmSnippet path="/src/main/resources/templates/pets/createOrUpdatePetForm.html" line="1">

---

The form method is POST, indicating it will submit data to the server for processing.

```html
<html xmlns:th="https://www.thymeleaf.org"
  th:replace="~{fragments/layout :: layout (~{::body},'owners')}">

<body>

  <h2>
    <th:block th:if="${pet['new']}">New </th:block>
    Pet
  </h2>
  <form th:object="${pet}" class="form-horizontal" method="post">
    <input type="hidden" name="id" th:value="*{id}" />
    <div class="form-group has-feedback">
      <div class="form-group">
        <label class="col-sm-2 control-label">Owner</label>
        <div class="col-sm-10">
          <span th:text="${owner?.firstName + ' ' + owner?.lastName}" />
        </div>
      </div>
      <input
        th:replace="~{fragments/inputField :: input ('Name', 'name', 'text')}" />
      <input
        th:replace="~{fragments/inputField :: input ('Birth Date', 'birthDate', 'date')}" />
      <input
        th:replace="~{fragments/selectField :: select ('Type', 'type', ${types})}" />
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button
          th:with="text=${pet['new']} ? 'Add Pet' : 'Update Pet'"
          class="btn btn-primary" type="submit" th:text="${text}">Add
          Pet</button>
      </div>
    </div>
  </form>

</body>

</html>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
