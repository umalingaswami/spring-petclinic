---
title: The CacheConfiguration class
---
This document will cover the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken> class in the Spring PetClinic project. We will discuss:

1. What <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken> is and its purpose.
2. The main functions defined in <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken>.

# What is <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken> class is part of the Spring PetClinic project, located in <SwmPath>[src/…/system/CacheConfiguration.java](src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java)</SwmPath>. It is designed to provide cache configuration using the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="41:21:21" line-data="	 * Create a simple configuration that enable statistics via the JCache programmatic">`JCache`</SwmToken> API. This configuration enables statistics that can be accessed via JMX, facilitating monitoring and management of cache performance.

<SwmSnippet path="/src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" line="35">

---

The function <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="36:5:5" line-data="	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {">`petclinicCacheConfigurationCustomizer`</SwmToken> is a bean that customizes the cache manager. It creates a cache named 'vets' using the configuration provided by the <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="37:16:16" line-data="		return cm -&gt; cm.createCache(&quot;vets&quot;, cacheConfiguration());">`cacheConfiguration`</SwmToken> method.

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

## <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken>

The <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="33:2:2" line-data="class CacheConfiguration {">`CacheConfiguration`</SwmToken> class is annotated with <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="31:0:1" line-data="@Configuration(proxyBeanMethods = false)">`@Configuration`</SwmToken> and <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="32:0:1" line-data="@EnableCaching">`@EnableCaching`</SwmToken>, indicating that it is used to configure caching within the application. This class defines beans related to cache management, such as <SwmToken path="src/main/java/org/springframework/samples/petclinic/system/CacheConfiguration.java" pos="36:3:3" line-data="	public JCacheManagerCustomizer petclinicCacheConfigurationCustomizer() {">`JCacheManagerCustomizer`</SwmToken>, which customizes the cache manager settings for the application.

&nbsp;

*This is an auto-generated document by Swimm 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nLXBldGNsaW5pYyUzQSUzQXVtYWxpbmdhc3dhbWk=" repo-name="spring-petclinic"><sup>Powered by [Swimm](/)</sup></SwmMeta>
