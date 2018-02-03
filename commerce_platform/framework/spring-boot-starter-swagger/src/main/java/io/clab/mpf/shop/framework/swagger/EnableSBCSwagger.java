package io.clab.mpf.shop.framework.swagger;

import org.springframework.context.annotation.Import;

import io.clab.mpf.shop.framework.swagger.configuration.SwaggerConfiguration;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Inherited
@Import({Swagger2DocumentationConfiguration.class, SwaggerConfiguration.class})
public @interface EnableSBCSwagger {
}
