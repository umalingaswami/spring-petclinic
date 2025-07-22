---
title: The WebConfiguration class
---
This document will cover the class <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken> in the Spring PetClinic project. We will explore:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken> is and its purpose.
2. The variables and functions defined within <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken>.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken> is a class in the Spring PetClinic project that implements the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="7:14:14" line-data="import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;">`WebMvcConfigurer`</SwmToken> interface. It is responsible for configuring internationalization (<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="8:10:10" line-data="import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;">`i18n`</SwmToken>) support within the application. This includes handling the loading of <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="17:7:9" line-data=" * Handles loading language-specific messages, tracking the user&#39;s language, and allowing">`language-specific`</SwmToken> messages, tracking the user's language preferences, and allowing language changes via URL parameters.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" line="32">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="33:5:5" line-data="	public LocaleResolver localeResolver() {">`localeResolver`</SwmToken> is used to create a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="30:6:8" line-data="	 * @return session-based {@link LocaleResolver}">`session-based`</SwmToken> <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="33:3:3" line-data="	public LocaleResolver localeResolver() {">`LocaleResolver`</SwmToken>. It utilizes session storage to remember the user's language setting across requests and defaults to English if no language is specified.

```java
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" line="44">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="45:5:5" line-data="	public LocaleChangeInterceptor localeChangeInterceptor() {">`localeChangeInterceptor`</SwmToken> allows the application to switch languages using a URL parameter, such as `?`<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="47:6:6" line-data="		interceptor.setParamName(&quot;lang&quot;);">`lang`</SwmToken>`=`<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="41:8:8" line-data="	 * &lt;code&gt;?lang=es&lt;/code&gt;.">`es`</SwmToken>. It returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="45:3:3" line-data="	public LocaleChangeInterceptor localeChangeInterceptor() {">`LocaleChangeInterceptor`</SwmToken> that handles the language change.

```java
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" line="55">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="56:5:5" line-data="	public void addInterceptors(InterceptorRegistry registry) {">`addInterceptors`</SwmToken> registers the locale change interceptor so that it can run on each request. This ensures that language changes are applied consistently across the application.

```java
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken> is used to manage session storage for remembering the user's language setting across requests. This functionality ensures that the user's language preference is consistently applied throughout their session, enhancing the user experience by maintaining language consistency.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
