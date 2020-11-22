Spring三大类核心工厂:
BeanFactory:
AbstractBeanFactory
DefaultListableBeanFactory
XmlBeanFactory


ApplicationContext:
AbstractApplicationContext
GenericApplicationContext
ClassPathXmlApplicationContext:XML-centric
FileSystemXmlApplicationContext
GenericGroovyApplicationContext
AnnotationConfigApplicationContext：Java-centric


WebApplicationContext:
GenericWebApplicationContext
GroovyWebApplicationContext
StaticWebApplicationContext
XmlWebApplicationContext
AnnotationConfigWebApplicationContext


configuration metadata(annotation-based,Java-based configuration,xml-based,Groovy-DSL)


<bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
</bean>


Resource
GenericApplicationContext
BeanDefinition
java.beans.Introspector.decapitalize

Container,Bean,

Bean实例化：Constructor，Static Factory Method，Instance Factory Method，FactoryBean

Object->JavaBean(java reusable software components)->Spring Bean BeanWrapper

Dependency Injection:Setter-based,Constructor-based,Method Injection
 
PropertyEditor

Constructor-based or setter-based DI?

Dependency Resolution Process

The Spring container validates the configuration of each bean as the container is created. 
However, the bean properties themselves are not set until the bean is actually created. Beans 
that are singleton-scoped and set to be pre-instantiated (the default) are created when the 
container is created. Scopes are defined in Bean Scopes. Otherwise, the bean is created only 
when it is requested. Creation of a bean potentially causes a graph of beans to be created, as 
the bean’s dependencies and its dependencies' dependencies (and so on) are created and assigned. 
Note that resolution mismatches among those dependencies may show up late — that is, on first creation
 of the affected bean.

Circular dependencies

container load-time:detects configuration problems, such as references to non-existent beans and circular dependencies

why ApplicationContext implementations by default pre-instantiate singleton beans?

A lazy-initialized bean tells the IoC container to create a bean instance when it is first requested, rather than at startup.

Autowiring Collaborators

Limitations and Disadvantages of Autowiring

make bean aware of the container
BeanFactoryAware
ApplicationContextAware

ObjectFactory
Provider

ServiceLocatorFactoryBean

a stateful bean deployed as a prototype (non-singleton) 

MethodReplacer

SimpleThreadScope

org.springframework.beans.factory.config.Scope

As a rule, you should use the prototype scope for all stateful beans and the singleton scope for stateless beans

DispatcherServlet

RequestContextListener

ServletRequestListener

WebApplicationInitializer

RequestContextFilter

org.springframework.beans.factory.config.ConfigurableBeanFactory.registerScope

CustomScopeConfigurer

InitializingBean

DisposableBean

JSR 250：https://en.wikipedia.org/wiki/JSR_250

BeanPostProcessor

对象生命周期，创建，finalize 到spring的Initialization，Destruction

does not couple the code to Spring

Lifecycle，LifecycleProcessor，SmartLifecycle，DefaultLifecycleProcessor

BeanNameAware

Autowiring is another alternative to obtain a reference to the ApplicationContext

@Autowired

ApplicationContextAware :couples the code to Spring and does not follow the Inversion of Control style

org.springframework.beans.factory.Aware

Spring offers a wide range of Aware callback interfaces that let beans indicate to the container
 that they require a certain infrastructure dependency

ChildBeanDefinition

ApplicationContext pre-instantiates all singletons by default

BeanPostProcessor

Customizing Beans by Using a BeanPostProcessor
Customizing Configuration Metadata with a BeanFactoryPostProcessor
Customizing Instantiation Logic with a FactoryBean



in order to apply changes to the configuration metadata that define the container. Spring includes a number 
of predefined bean factory post-processors, such as PropertyOverrideConfigurer and PropertySourcesPlaceholderConfigurer

The FactoryBean concept and interface is used in a number of places within the Spring Framework. 
More than 50 implementations of the FactoryBean interface ship with Spring itself.

ApplicationContext.getBean("myFactoryBean"); //myFactoryBean
ApplicationContext.getBean("&myFactoryBean"); //myFactoryBean创建的bean


AOP auto-proxying is implemented as a BeanPostProcessor

RequiredAnnotationBeanPostProcessor

Are annotations better than XML for configuring Spring?

<context:annotation-config/>

AutowiredAnnotationBeanPostProcessor, CommonAnnotationBeanPostProcessor, PersistenceAnnotationBeanPostProcessor

@Autowired annotation provides the same capabilities as described in Autowiring Collaborators 
but with more fine-grained control and wider applicability

JSR-330

Annotation-based Container Configuration

CustomAutowireConfigurer

CommonAnnotationBeanPostProcessor
 
 SimpleJndiBeanFactory 

ResourceLoader, ApplicationEventPublisher, and MessageSource

@Component and Further Stereotype Annotations

combine meta-annotations to create “composed annotations”. For example, 
the @RestController annotation from Spring MVC is composed of @Controller and @ResponseBody

@ComponentScan
<context:component-scan base-package="org.example"/>

 Java code to configure the Spring container
 
org.springframework.context.annotation.Condition

Environment Abstraction
PropertySource
StandardEnvironment
StandardServletEnvironment
JndiPropertySource

LoadTimeWeaver
LocalContainerEntityManagerFactoryBean

ContextLoader

To enhance BeanFactory functionality in a more framework-oriented style, 
the context package also provides the following functionality

MessageSource
ResourceLoader
ApplicationListener
ApplicationEventPublisher
HierarchicalBeanFactory
HierarchicalMessageSource

Internationalization using MessageSource
ResourceBundleMessageSource
StaticMessageSource
ReloadableResourceBundleMessageSource

ApplicationEvent
ResolvableTypeProvider
ResourceLoaderAware

AbstractApplicationContext
ApplicationStartup
StartupStep

FlightRecorderApplicationStartup
ContextLoaderListener
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/daoContext.xml /WEB-INF/applicationContext.xml</param-value>
</context-param>

<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

TaskExecutor
SpringContextResourceAdapter

DefaultListableBeanFactory implementation is a key delegate within the higher-level GenericApplicationContext container.

BeanFactory and related interfaces (such as BeanFactoryAware, InitializingBean, DisposableBean) are important 
integration points for other framework components.


Note that the core BeanFactory API level and its DefaultListableBeanFactory implementation do
not make assumptions about the configuration format or any component annotations to be used. All 
of these flavors come in through extensions (such as XmlBeanDefinitionReader and AutowiredAnnotationBeanPostProcessor) 
and operate on shared BeanDefinition objects as a core metadata representation. 
**This is the essence of what makes Spring’s container so flexible and extensible.**

BeanFactory or ApplicationContext?

You should use an ApplicationContext unless you have a good reason for not doing so, with GenericApplicationContext and 
its subclass AnnotationConfigApplicationContext as the common implementations for custom bootstrapping. These are the
 primary entry points to Spring’s core container for all common purposes: loading of configuration files, triggering a 
 classpath scan, programmatically registering bean definitions and annotated classes, and (as of 5.0) registering 
 functional bean definitions.

An AnnotationConfigApplicationContext has all common annotation post-processors registered and may bring in 
additional processors underneath the covers through configuration annotations, such as @EnableTransactionManagement. 
At the abstraction level of Spring’s annotation-based configuration model, the notion of bean post-processors becomes
 a mere internal container detail.
 
AnnotationConfigApplicationContext自动配置了很多东西，源码非常值得阅读，比如AnnotatedBeanDefinitionReader

BeanDefinitionReader

Bean核心抽象总结，不全,但是主要两条主线
1 Instantiating the Container(这一步没有出现工厂)
Resource -> BeanDefinitionReader -> BeanDefinition -> BeanDefinitionHolder -> BeanDefinitionRegistry
2 Using the Container
DefaultListableBeanFactory -> BeanFactory -> NamedBeanHolder




2020-11-22 看到这里了：
https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#resources