---
title: The CacheConfiguration class
---
This document explains the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken> class. We will cover:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken> is and its purpose.
2. The functions defined in <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken>, including their roles and implementations.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken> is a class in the system package that configures caching for the application using the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken> API. It is annotated with @Configuration and @<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="20:10:10" line-data="import org.springframework.cache.annotation.EnableCaching;">`EnableCaching`</SwmToken>, indicating it provides Spring configuration and enables caching support. The class creates and customizes caches used in the application, specifically enabling statistics collection for monitoring cache usage via JMX.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="35">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="36:5:5" line-data="	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {">`petclinicCacheConfigurationCustomizer`</SwmToken> is a Spring bean that returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="36:3:3" line-data="	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {">`JCacheManagerCustomizer`</SwmToken>. This customizer creates a cache named "vets" using the configuration provided by the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="37:16:16" line-data="		return cm -&gt; cm.createCache(&quot;vets&quot;, cacheConfiguration());">`cacheConfiguration`</SwmToken> function. It is responsible for setting up the cache when the cache manager is initialized.

```java
	@Bean
	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {
		return cm -> cm.createCache("vets", cacheConfiguration());
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="49">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="49:17:17" line-data="	private javax.cache.configuration.Configuration&lt;Object, Object&gt; cacheConfiguration() {">`cacheConfiguration`</SwmToken> creates and returns a simple cache configuration object using the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="27:17:17" line-data=" * Cache configuration intended for caches providing the JCache API. This configuration">`JCache`</SwmToken> API's <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="50:5:5" line-data="		return new MutableConfiguration&lt;&gt;().setStatisticsEnabled(true);">`MutableConfiguration`</SwmToken>. This configuration enables statistics collection on the cache, which allows monitoring cache performance and usage. The method is private and used internally by the customizer function to define cache behavior.

```java
	private javax.cache.configuration.Configuration<Object, Object> cacheConfiguration() {
		return new MutableConfiguration<>().setStatisticsEnabled(true);
	}
```

---

</SwmSnippet>

# Usage

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken>

<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken> is annotated with @Configuration and @<SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="20:10:10" line-data="import org.springframework.cache.annotation.EnableCaching;">`EnableCaching`</SwmToken>, indicating that it defines beans related to caching and enables Spring's annotation-driven cache management capability. It contains a bean method named <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="36:5:5" line-data="	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {">`petclinicCacheConfigurationCustomizer`</SwmToken> which returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="36:3:3" line-data="	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {">`JCacheManagerCustomizer`</SwmToken>. This customizer is used to configure cache settings specifically for the application, allowing fine-tuning of cache behavior such as cache names, expiration policies, or other cache properties.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
