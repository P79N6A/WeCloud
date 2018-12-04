package com.bat.config;

import com.dearpei.util.CustomerAuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SpringBootCASConfig {
    /**
     * SingleSignOutFilter
     * 必须放在最前面
     */
    @Bean
    public FilterRegistrationBean filterSingleRegistration() {
      FilterRegistrationBean registration = new FilterRegistrationBean();
      registration.setFilter(new SingleSignOutFilter());
      Map<String,String> initParameters = new HashMap<String,String>();
      initParameters.put("casServerUrlPrefix",casServerUrlPrefix );
      registration.setInitParameters(initParameters);
      //set mapping url
        registration.addUrlPatterns("/*");
        //set loading sequence
        registration.setOrder(1);
        return registration;
    }

    /**
      * 添加监听器
      * @return
      */
    @Bean
    public ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> singleSignOutListenerRegistration(){
        ServletListenerRegistrationBean<SingleSignOutHttpSessionListener> registrationBean = new ServletListenerRegistrationBean<SingleSignOutHttpSessionListener>();
        registrationBean.setListener(new SingleSignOutHttpSessionListener());
        registrationBean.setOrder(1); return registrationBean;
     }
    @Bean
    public FilterRegistrationBean authenticationFilterRegistrationBean() {
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new CustomerAuthenticationFilter());
        Map initParameters = new HashMap();
        initParameters.put("casServerLoginUrl", casServerLoginUrl);
        initParameters.put("serverName", serverName);
        initParameters.put("excludePath",excludePath);
        authenticationFilter.setInitParameters(initParameters);
        authenticationFilter.setOrder(1);
        List urlPatterns = new ArrayList();
        urlPatterns.add("/*");
        // 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }
    @Bean
    public FilterRegistrationBean ValidationFilterRegistrationBean(){
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new Cas20ProxyReceivingTicketValidationFilter());
        Map initParameters = new HashMap();
        initParameters.put("casServerUrlPrefix", casServerUrlPrefix);
        initParameters.put("serverName", serverName);
        authenticationFilter.setInitParameters(initParameters);
        authenticationFilter.setOrder(2);
        List urlPatterns = new ArrayList();
        urlPatterns.add("/*");
        // 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }
    @Bean
    public FilterRegistrationBean casHttpServletRequestWrapperFilter(){
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new HttpServletRequestWrapperFilter());
        authenticationFilter.setOrder(3);
        List urlPatterns = new ArrayList();
        urlPatterns.add("/*");
        // 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }
    @Bean
    public FilterRegistrationBean casAssertionThreadLocalFilter(){
        FilterRegistrationBean authenticationFilter = new FilterRegistrationBean();
        authenticationFilter.setFilter(new AssertionThreadLocalFilter());
        authenticationFilter.setOrder(4);
        List urlPatterns = new ArrayList();
        urlPatterns.add("/*");
    // 设置匹配的url
        authenticationFilter.setUrlPatterns(urlPatterns);
        return authenticationFilter;
    }
    @Value("${spring.cas.sign-out-filters}")
    private String signOutFilters;
    @Value("${spring.cas.auth-filters}")
    private String authilters;
    @Value("${spring.cas.validate-filters}")
    private String validateFilters;
    @Value("${spring.cas.request-wrapper-filters}")
    private String requestWrapperFilters;
    @Value("${spring.cas.cas-server-login-url}")
    private String casServerLoginUrl;
    @Value("${spring.cas.cas-server-url-prefix}")
    private String casServerUrlPrefix;
    @Value("${spring.cas.use-session}")
    private String useSession;
    @Value("${spring.cas.server-name}")
    private String serverName;
    @Value("${spring.cas.excludePath}")
    private String excludePath;

    public String getExcludePath() {
        return excludePath;
    }

    public void setExcludePath(String excludePath) {
        this.excludePath = excludePath;
    }

    public String getSignOutFilters() {
        return signOutFilters;
    }

    public void setSignOutFilters(String signOutFilters) {
        this.signOutFilters = signOutFilters;
    }

    public String getAuthilters() {
        return authilters;
    }

    public void setAuthilters(String authilters) {
        this.authilters = authilters;
    }

    public String getValidateFilters() {
        return validateFilters;
    }

    public void setValidateFilters(String validateFilters) {
        this.validateFilters = validateFilters;
    }

    public String getRequestWrapperFilters() {
        return requestWrapperFilters;
    }

    public void setRequestWrapperFilters(String requestWrapperFilters) {
        this.requestWrapperFilters = requestWrapperFilters;
    }

    public String getCasServerLoginUrl() {
        return casServerLoginUrl;
    }

    public void setCasServerLoginUrl(String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public String getCasServerUrlPrefix() {
        return casServerUrlPrefix;
    }

    public void setCasServerUrlPrefix(String casServerUrlPrefix) {
        this.casServerUrlPrefix = casServerUrlPrefix;
    }

    public String getUseSession() {
        return useSession;
    }

    public void setUseSession(String useSession) {
        this.useSession = useSession;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
