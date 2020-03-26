package com.google.shiro.config;

import com.google.shiro.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/","anon");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/ueditor/**","anon");
        filterChainDefinitionMap.put("/upload/**","anon");
        filterChainDefinitionMap.put("/user/register.html","anon");
        filterChainDefinitionMap.put("/user/register","anon");
        filterChainDefinitionMap.put("/user/login.html","anon");
        filterChainDefinitionMap.put("/user/login","anon");
        filterChainDefinitionMap.put("/user/logout","logout");
        filterChainDefinitionMap.put("/user/findPassword.html","anon");
        filterChainDefinitionMap.put("/user/findPassword","anon");
        filterChainDefinitionMap.put("/user/sendEmail","anon");
        filterChainDefinitionMap.put("/user/checkYzm","anon");
        filterChainDefinitionMap.put("/commom/header.html","anon");
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


  @Bean
   public Realm  myRealm(){
       return new MyRealm();
   }
  @Bean
    public SecurityManager securityManager(){
      DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
      securityManager.setRealm(myRealm());
      return securityManager;
  }
    @Bean(name="lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}