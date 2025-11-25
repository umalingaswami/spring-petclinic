---
title: JCache API Cache Configuration
---
# introduction

This document explains the cache setup in the application using the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken> API. It answers these questions:

1. Why use <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken> API for caching here?
2. How is the cache configured and customized?
3. How are cache statistics enabled and why?

# why use <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken> API and enable caching

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="26">

---

The class is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="31:0:1" line-data="@Configuration(proxyBeanMethods = false)">`@Configuration`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="32:0:1" line-data="@EnableCaching">`@EnableCaching`</SwmToken>. This tells Spring to look for cache-related annotations and manage caches accordingly. Using the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken> API standardizes cache management, making it implementation-agnostic and compatible with various cache providers.

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

# how the cache is customized

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="35">

---

The method returning a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="36:3:3" line-data="	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {">`JCacheManagerCustomizer`</SwmToken> bean is the hook to customize caches. It creates a cache named "vets" using a configuration defined in a helper method. This approach centralizes cache creation and customization in one place, making it easier to manage and extend.

```java
	@Bean
	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {
		return cm -> cm.createCache("vets", cacheConfiguration());
	}
```

---

</SwmSnippet>

# enabling cache statistics

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="40">

---

The cache configuration method returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="50:5:5" line-data="		return new MutableConfiguration&lt;&gt;().setStatisticsEnabled(true);">`MutableConfiguration`</SwmToken> with statistics enabled. This is important because the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="41:21:21" line-data="	 * Create a simple configuration that enable statistics via the JCache programmatic">`JCache`</SwmToken> API itself offers limited configuration options, so enabling statistics here allows monitoring cache behavior via JMX. Other settings like size limits must be handled by the underlying cache provider.

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
