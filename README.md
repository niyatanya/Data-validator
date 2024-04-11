# Data validator

### Hexlet tests and linter status:
[![Actions Status](https://github.com/niyatanya/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/niyatanya/java-project-78/actions)
[![Java CI](https://github.com/niyatanya/java-project-78/actions/workflows/gradle.yml/badge.svg)](https://github.com/niyatanya/java-project-78/actions/workflows/gradle.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/75c8128e50f77538899a/maintainability)](https://codeclimate.com/github/niyatanya/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/75c8128e50f77538899a/test_coverage)](https://codeclimate.com/github/niyatanya/java-project-78/test_coverage)

Introducing DataValidator, a versatile schema builder designed for dynamic value parsing and validation at runtime. With DataValidator, you can effortlessly define a schema, seamlessly transform values to conform, verify the structure of existing values, or perform both operations simultaneously. Our schemas boast exceptional flexibility, enabling the representation of intricate, interrelated validations. While our library currently offers three built-in schemas, expanding with custom ones is a breeze.

Built-in schema collection:
- String Schema
- Number Schema
- Map Schema

Main features:
- DataValidator presents a succinct yet **effective schema interface**, capable of articulating simple to intricate data models with ease.
- **Easily extendable**: Integrate your own type-safe methods and schemas effortlessly into the framework.

## Usage
Example code below utilizes a custom validator named `Validator` to define a schema for validating a map-like structure, particularly for objects representing persons. It sets up validation rules for the "firstName" and "lastName" properties within the map. The validation criteria include ensuring that both "firstName" and "lastName" are present (`required()`), and that the "lastName" is at least two characters long (`minLength(2)`). After defining the schema, it is applied to different human objects (`human1`, `human2`, `human3`) to check their validity. The `isValid()` method evaluates whether each object adheres to the defined schema, returning `true` if the object passes all validation rules, and `false` otherwise.

```java
    var v = new Validator();
    
    var schema = v.map();
    
    // Creae a set of schemes to check each key of the validated object
    Map<String, BaseSchema<String>> schemas = new HashMap<>();

    // Define validation schemes for "firstName" and "lastName" property values
    schemas.put("firstName", v.string().required());
    schemas.put("lastName", v.string().required().minLength(2));

    // Customize the `MapSchema` scheme
    // Pass the created set of schemes to the shape() method 
    schema.shape(schemas);

    // Validate objects
    Map<String, String> human1 = new HashMap<>();
    human1.put("firstName", "John");
    human1.put("lastName", "Smith");
    schema.isValid(human1); // true
    
    Map<String, String> human2 = new HashMap<>();
    human2.put("firstName", "John");
    human2.put("lastName", null);
    schema.isValid(human2); // false
    
    Map<String, String> human3 = new HashMap<>();
    human3.put("firstName", "Anna");
    human3.put("lastName", "B");
    schema.isValid(human3); // false
```
