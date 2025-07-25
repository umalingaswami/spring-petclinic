---
title: The WebConfiguration class
---
# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken> class in <SwmPath>[src/…/system/WebConfiguration.java](src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java)</SwmPath> is responsible for configuring internationalization (<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="8:10:10" line-data="import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;">`i18n`</SwmToken>) support within the Spring PetClinic application. It manages <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="17:7:9" line-data=" * Handles loading language-specific messages, tracking the user&#39;s language, and allowing">`language-specific`</SwmToken> message loading, tracks the user's language preference, and allows language changes via URL parameters.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" line="27">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="33:5:5" line-data="	public LocaleResolver localeResolver() {">`localeResolver`</SwmToken> is used to create a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="30:6:8" line-data="	 * @return session-based {@link LocaleResolver}">`session-based`</SwmToken> <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="30:14:14" line-data="	 * @return session-based {@link LocaleResolver}">`LocaleResolver`</SwmToken> bean. This resolver uses session storage to remember the user's language setting across requests and defaults to English if no language is specified.

```java
	/**
	 * Uses session storage to remember the user’s language setting across requests.
	 * Defaults to English if nothing is specified.
	 * @return session-based {@link LocaleResolver}
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.ENGLISH);
		return resolver;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" line="39">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="45:5:5" line-data="	public LocaleChangeInterceptor localeChangeInterceptor() {">`localeChangeInterceptor`</SwmToken> creates a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="42:12:12" line-data="	 * @return a {@link LocaleChangeInterceptor} that handles the change">`LocaleChangeInterceptor`</SwmToken> bean that allows the application to switch languages using a URL parameter, such as `?`<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="41:6:6" line-data="	 * &lt;code&gt;?lang=es&lt;/code&gt;.">`lang`</SwmToken>`=`<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="41:8:8" line-data="	 * &lt;code&gt;?lang=es&lt;/code&gt;.">`es`</SwmToken>. This interceptor handles the language change requests.

```java
	/**
	 * Allows the app to switch languages using a URL parameter like
	 * <code>?lang=es</code>.
	 * @return a {@link LocaleChangeInterceptor} that handles the change
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("lang");
		return interceptor;
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" line="51">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="56:5:5" line-data="	public void addInterceptors(InterceptorRegistry registry) {">`addInterceptors`</SwmToken> registers the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="57:5:5" line-data="		registry.addInterceptor(localeChangeInterceptor());">`localeChangeInterceptor`</SwmToken> so that it can run on each request. This ensures that language change requests are processed by the interceptor.

```java
	/**
	 * Registers the locale change interceptor so it can run on each request.
	 * @param registry where interceptors are added
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="25:4:4" line-data="public class WebConfiguration implements WebMvcConfigurer {">`WebConfiguration`</SwmToken> class is used to configure web settings for the application. It implements the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/WebConfiguration.java" pos="7:14:14" line-data="import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;">`WebMvcConfigurer`</SwmToken> interface, which allows customization of the default Spring MVC configuration. One of its functionalities includes using session storage to remember the user's language setting across requests, enhancing the user experience by maintaining language preferences throughout their session.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
