/**
 * 
 */
package com.srisris.jbc.conf;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * @author schinthalapudi
 * 
 */
@Configuration
@ComponentScan(basePackages = "com.srisris.jbc")
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class JbcApplicationContext extends WebMvcConfigurerAdapter {

	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
	private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

	private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";
	private static final String PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE = "message.source.use.code.as.default.message";

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
	private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

	@Resource
	private Environment environment;

	/**
	 * Configures the location of static resources such as css files.
	 * 
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	/**
	 * Ensures that dispatcher servlet can be mapped to '/' and static resources
	 * are still served by the containers default servlet.
	 * 
	 * @param configurer
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * Configures the exception resolver.
	 * 
	 * @return
	 */
	@Bean
	public SimpleMappingExceptionResolver exceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

		Properties exceptionMappings = new Properties();

		// exceptionMappings.put("com.packtpub.springdata.jpa.service.NotFoundException",
		// "error/404");
		exceptionMappings.put("java.lang.Exception", "error/error");
		exceptionMappings.put("java.lang.RuntimeException", "error/error");

		exceptionResolver.setExceptionMappings(exceptionMappings);

		Properties statusCodes = new Properties();

		statusCodes.put("error/404", "404");
		statusCodes.put("error/error", "500");

		exceptionResolver.setStatusCodes(statusCodes);

		return exceptionResolver;
	}

	/**
	 * Configures the message source bean.
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

		messageSource.setBasename(environment
				.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_BASENAME));
		messageSource
				.setUseCodeAsDefaultMessage(Boolean.parseBoolean(environment
						.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE)));

		return messageSource;
	}

	/**
	 * Configures the view resolver.
	 * 
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

		return viewResolver;
	}

	/**
	 * Configures the data source.
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();

		dataSource.setDriverClass(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		dataSource.setJdbcUrl(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		dataSource.setUsername(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		dataSource.setPassword(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));

		return dataSource;
	}

	/**
	 * Configures the transaction manager.
	 * 
	 * @return
	 */
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());

		return transactionManager;
	}

	/**
	 * Configures the entity manager factory.
	 * 
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean
				.setPackagesToScan(environment
						.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

		Properties jpaProterties = new Properties();
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
		jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

		entityManagerFactoryBean.setJpaProperties(jpaProterties);

		return entityManagerFactoryBean;
	}
}
