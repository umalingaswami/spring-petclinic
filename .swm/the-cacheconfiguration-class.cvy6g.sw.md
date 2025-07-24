---
title: The CacheConfiguration class
---
# What is <SwmToken path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="37:16:16" line-data="		return cm -&gt; cm.createCache(&quot;vets&quot;, cacheConfiguration());">`cacheConfiguration`</SwmToken>

The <SwmToken path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="37:16:16" line-data="		return cm -&gt; cm.createCache(&quot;vets&quot;, cacheConfiguration());">`cacheConfiguration`</SwmToken> class in <SwmPath>[src/…/system/CacheConfiguration.java](src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java)</SwmPath> is designed to provide cache configuration using the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="41:21:21" line-data="	 * Create a simple configuration that enable statistics via the JCache programmatic">`JCache`</SwmToken> API. It is used to create and configure caches for the application, enabling statistics that can be accessed via JMX.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="35">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="36:5:5" line-data="	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {">`petclinicCacheConfigurationCustomizer`</SwmToken> is a bean that customizes the cache manager by creating a cache named "vets" using the configuration provided by the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="37:16:16" line-data="		return cm -&gt; cm.createCache(&quot;vets&quot;, cacheConfiguration());">`cacheConfiguration`</SwmToken> method.

```java
	@Bean
	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {
		return cm -> cm.createCache("vets", cacheConfiguration());
	}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="40">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="49:17:17" line-data="	private javax.cache.configuration.Configuration&lt;Object, Object&gt; cacheConfiguration() {">`cacheConfiguration`</SwmToken> creates a simple cache configuration that enables statistics via the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="41:21:21" line-data="	 * Create a simple configuration that enable statistics via the JCache programmatic">`JCache`</SwmToken> programmatic configuration API. It returns a <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="50:5:5" line-data="		return new MutableConfiguration&lt;&gt;().setStatisticsEnabled(true);">`MutableConfiguration`</SwmToken> object with statistics enabled.

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
```

---

</SwmSnippet>

# Usage

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
