---
title: Templates in Resources
---
# What are Templates in Resources

Templates in the resources directory are HTML files that define the structure and layout of the application's user interface. They enable the application to dynamically generate HTML pages by combining static markup with dynamic data, allowing for interactive and responsive user experiences.

# Organization of Templates

These templates are systematically organized into subdirectories that correspond to the domain entities they represent, such as vets, pets, and owners. This organization aligns the templates with the application's domain model, making them easier to manage and maintain.

# Types of Templates

Within these directories, templates serve different purposes: some provide forms for creating or updating entities, others display lists of collections, and some show detailed views of individual records. This variety supports the full range of user interactions with the application's data.

# Reusable Fragments

A special 'fragments' directory contains reusable template parts, such as layout structures and form fields. These fragments can be included or replaced within other templates, promoting consistency across pages and reducing code duplication.

# Template Processing and Usage

Templates are processed by the view layer to produce the final HTML sent to the client. This processing dynamically incorporates the current state and user interactions, enabling the application to present up-to-date and interactive content.

<SwmSnippet path="/src/main/resources/templates/fragments/layout.html" line="64">

---

An illustrative example is found in the layout fragment where a menu item is dynamically replaced to trigger a <SwmToken path="src/main/resources/templates/fragments/layout.html" pos="64:26:26" line-data="            th:replace=&quot;~{::menuItem (&#39;/oups&#39;,&#39;error&#39;,&#39;trigger a RuntimeException to see how it is handled&#39;,&#39;exclamation-triangle&#39;,&#39;Error&#39;)}&quot;&gt;">`RuntimeException`</SwmToken>. This demonstrates how templates can be used not only for standard UI rendering but also to handle error scenarios by dynamically modifying UI elements.

```html
            th:replace="~{::menuItem ('/oups','error','trigger a RuntimeException to see how it is handled','exclamation-triangle','Error')}">
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
