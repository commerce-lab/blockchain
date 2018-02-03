package io.clab.mpf.shop.framework.swagger.configuration;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.classmate.TypeResolver;

import io.clab.mpf.shop.framework.swagger.properties.SwaggerConfigurationProperties;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@EnableConfigurationProperties(SwaggerConfigurationProperties.class)
public class SwaggerConfiguration {

	private final SwaggerConfigurationProperties properties;

	public SwaggerConfiguration(final SwaggerConfigurationProperties properties) {
		this.properties = properties;

		log.info("using springfox.swagger2 with properties='{}'", properties);
	}

	@Bean
	public Docket docket(final TypeResolver typeResolver) {
		return new Docket(DocumentationType.SWAGGER_2)
		        .groupName("notuseapi")
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(not(regex("/error.*")))
				.build()
				.pathMapping("/")
				.apiInfo(
						new ApiInfoBuilder().title(properties.getTitle())
				            .contact(new Contact(properties.getContact(),properties.getUrl(),properties.getEmail()))
				             .description(properties.getDescription())
						      .version(properties.getVersion()).build());
	}
	
	@Bean 
	public Docket adminApi(final TypeResolver typeResolver) {
		return new Docket(DocumentationType.SWAGGER_2)
		.groupName("adminapi")
		.select()
		.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
		.apis(RequestHandlerSelectors.basePackage("io.clab.mpf.shop.controller.admin"))
		.paths(not(regex("/error.*")))
		.build()
		.pathMapping("/")
		.apiInfo(
				new ApiInfoBuilder().title(properties.getTitle())
		        .contact(new Contact(properties.getContact(),properties.getUrl(),properties.getEmail()))
		         .description(properties.getDescription())
				  .version(properties.getVersion()).build());
	}
	
	@Bean 
	public Docket consumerApi(final TypeResolver typeResolver) {
		return new Docket(DocumentationType.SWAGGER_2)
		.groupName("consumerapi")
		.select()
		.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
		.apis(RequestHandlerSelectors.basePackage("io.clab.mpf.shop.controller.front"))
		.paths(not(regex("/error.*")))
		.build()
		.pathMapping("/")
		.apiInfo(
				new ApiInfoBuilder().title(properties.getTitle())
				        .contact(new Contact(properties.getContact(),properties.getUrl(),properties.getEmail()))
				         .description(properties.getDescription())
						  .version(properties.getVersion()).build());
	}

	@Bean 
	public Docket businessApi(final TypeResolver typeResolver) {
		return new Docket(DocumentationType.SWAGGER_2)
		.groupName("businessapi")
		.select()
		.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
		.apis(RequestHandlerSelectors.basePackage("io.clab.mpf.shop.business.controller"))
		.paths(not(regex("/error.*")))
		.build()
		.pathMapping("/")
		.apiInfo(
				new ApiInfoBuilder().title(properties.getTitle())
				        .contact(new Contact(properties.getContact(),properties.getUrl(),properties.getEmail()))
				         .description(properties.getDescription())
						  .version(properties.getVersion()).build());
	}

	@Bean
	public WebMvcConfigurerAdapter forwardToIndex() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				if (properties.isRedirect()) {
					registry.addViewController("/").setViewName(
							"redirect:/swagger-ui.html");
				}
			}
		};
	}

}
