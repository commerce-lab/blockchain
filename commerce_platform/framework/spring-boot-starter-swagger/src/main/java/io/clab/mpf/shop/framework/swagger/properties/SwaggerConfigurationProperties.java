package io.clab.mpf.shop.framework.swagger.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("springbootcamp.swagger")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwaggerConfigurationProperties {

  private String title = "APPLICATION.NAME";

  private String version = "APPLICATION.VERSION";
  
  private String description = "APPLICATION.DESCRIPTION";
  
  private String contact = "iceray";
  
  private String url = "";
  
  private String email = "";

  boolean redirect = false;

}
