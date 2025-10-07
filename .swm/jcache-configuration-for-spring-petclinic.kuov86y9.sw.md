---
title: JCache Configuration for Spring Petclinic
---
# introduction

This document explains the cache setup in the Spring Petclinic application using <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken>. It answers:

1. Why use <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken> and how is it enabled in the app?
2. How is the cache created and configured?
3. What configuration options are set and why?

# enabling caching and defining configuration class

Caching is enabled by annotating the class with <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="32:0:1" line-data="@EnableCaching">`@EnableCaching`</SwmToken>. This activates Spring’s cache abstraction, allowing the app to use cache annotations elsewhere.

The class is marked with <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="31:0:8" line-data="@Configuration(proxyBeanMethods = false)">`@Configuration(proxyBeanMethods = false)`</SwmToken> to declare it as a source of bean definitions without proxy overhead. This keeps the configuration lightweight and straightforward.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="26">

---

This setup ensures the app is ready to manage caches via Spring and <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken> APIs.

```java
/**
 * Cache configuration intended for caches providing the JCache API. This configuration
 * creates the used cache for the application and enables statistics that become
 * accessible via JMX.
 */
@Configuration(proxyBeanMethods = false)
@EnableCaching
class CacheConfiguration {
```

---

</SwmSnippet>

# creating and customizing the cache

The cache manager customizer bean defines the actual cache named <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="37:12:12" line-data="		return cm -&gt; cm.createCache(&quot;vets&quot;, cacheConfiguration());">`vets`</SwmToken>. It uses a lambda to create this cache on the cache manager with a specific configuration.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="35">

---

This approach centralizes cache creation and ties it to the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken> manager lifecycle, so the cache is ready when the app starts.

```java
	@Bean
	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {
		return cm -> cm.createCache("vets", cacheConfiguration());
	}
```

---

</SwmSnippet>

# configuring cache properties

The cache configuration method returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="50:5:5" line-data="		return new MutableConfiguration&lt;&gt;().setStatisticsEnabled(true);">`MutableConfiguration`</SwmToken> with statistics enabled. This means the cache will collect usage data accessible via JMX, useful for monitoring cache performance.

The comment clarifies that <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken>’s standard API offers limited config options. More detailed settings like size limits must be handled by the underlying cache provider’s config files or APIs.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="40">

---

This minimal config focuses on enabling stats while leaving advanced tuning to the cache implementation.

```java
	/**
	 * Create a simple configuration that enable statistics via the JCache programmatic
	 * configuration API.
	 * <p>
	 * Within the configuration object that is provided by the JCache API standard, there
	 * is only a very limited set of configuration options. The really relevant
	 * configuration options (like the size limit) must be set via a configuration
	 * mechanism that is provided by the selected JCache implementation.
	 */
	private javax.cache.configuration.Configuration<Object, Object> cacheConfiguration() {
		return new MutableConfiguration<>().setStatisticsEnabled(true);
	}

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
