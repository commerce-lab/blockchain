package io.clab.mpf.shop.framework.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Druid自动配置
 *
 */

@Configuration
@ConditionalOnClass(DruidDataSource.class)
@EnableConfigurationProperties(DruidMonitorProperties.class)
@ConditionalOnProperty(prefix = "druid.monitor",havingValue = "enabled",value = "enabled",matchIfMissing = false)
@AutoConfigureAfter(DataSource.class)
public class DruidMonitorAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DruidMonitorAutoConfiguration.class);

    @Autowired
    private DruidMonitorProperties properties;

    /**
     * 注册一个StatViewServlet
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),properties.getDruidStatView());

        //添加初始化参数：initParams

        //白名单：
        servletRegistrationBean.addInitParameter("allow",properties.getAllow());
        logger.info("allow ---> " + properties.getAllow());
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny",properties.getDeny());
        logger.info("deny ---> " + properties.getDeny());
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername",properties.getLoginUsername());
        servletRegistrationBean.addInitParameter("loginPassword",properties.getLoginPassword());
        logger.info("loginUsername ---> " + properties.getLoginUsername() + "loginPassword ---> " + properties.getLoginPassword());
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable",properties.getResetEnable());
        logger.info("resetEnable ---> " + properties.getResetEnable());
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns(properties.getDruidWebStatFilter());
        logger.info("urlPatterns ---> " + properties.getDruidWebStatFilter());

        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions",properties.getExclusions());
        logger.info("exclusions ---> " + properties.getExclusions());
        return filterRegistrationBean;
    }
    
}
