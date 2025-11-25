---
title: Pets Template Overview
---
# Overview of the Pets Template

The Pets template is designed to render a form that facilitates creating or updating a pet's information within the application. It binds directly to a pet object, which allows the form fields to be either pre-populated with existing pet data or left empty when adding a new pet.

# Purpose of Pets in the Application

Pets represent the animals owned by users and are central to managing veterinary records. They store critical details such as the pet's name, birth date, and type. These attributes are essential for tracking the pet's health history and veterinary visits.

# Structure and Fields of the Pets Form

The form includes fields for the pet's name, birth date, and type. Each of these fields is rendered using reusable input and select fragments, which promotes consistency and modularity across the user interface. Additionally, the owner's full name is displayed as a read-only field to provide context about the pet's association.

# Dynamic Behavior of the Form

The submit button within the pet form dynamically adjusts its label based on the pet's state. If the pet is new, the button displays 'Add Pet'; if the pet already exists, it shows 'Update Pet'. This dynamic labeling enhances user clarity and interaction with the form.

# How Pets are Used in the Codebase

Pets are managed through forms that bind to pet objects. When editing an existing pet, the form fields are populated with the current pet data. For new pets, the fields are blank, allowing users to enter new information. This binding is typically done using model attributes in the form.

# Example Usage of the Pets Template

In practice, the pet creation or update form binds a pet object to the form using a model attribute. The form contains hidden fields for the pet ID, input fields for the pet's name and birth date, and a select field for the pet type. Above these fields, the owner's name is displayed as a read-only field. The submit button label adapts dynamically based on whether the pet is new or existing.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
