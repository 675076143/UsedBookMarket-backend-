package com.robin.usedbookmarketbackend.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
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

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录成功
        shiroFilterFactoryBean.setSuccessUrl("/");
        //没有登录
        shiroFilterFactoryBean.setLoginUrl("/authentication/without_login");
        //没有权限
        shiroFilterFactoryBean.setUnauthorizedUrl("/authentication/unauthorized");
        LinkedHashMap<String,String> fillterMap = new LinkedHashMap<>();
        //开放对登录接口的访问
        fillterMap.put("/authentication/login","anon");
        fillterMap.put("/authentication/without_login","anon");
        fillterMap.put("/authentication/unauthorized","anon");
        //开放对注册接口的访问
        fillterMap.put("/authentication/register","anon");
        //开放对Swagger的访问
        fillterMap.put("/swagger-ui.html","anon");
        fillterMap.put("/webjars/**","anon");
        fillterMap.put("/swagger-resources/**","anon");
        fillterMap.put("/v2/**","anon");
        //登陆前禁用这些端口
        fillterMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(fillterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(@Autowired Realm realm, @Autowired SessionManager sessionManager){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean
    public Realm realm(@Autowired CredentialsMatcher credentialsMatcher){
        ShiroRealm realm = new ShiroRealm();
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    @Bean
    public SessionManager sessionManager(){
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }

    /***
     * 认证方式验证
     * 算法: md5hash
     * 次数: 512
     * @return
     */
    @Bean
    public CredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
        hashedCredentialsMatcher.setHashIterations(512);
        return hashedCredentialsMatcher;
    }


    /**
     * 管理Shiro相关Bean的生命周期。
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 扫描上下文寻找所有的Advisor（通知器），将符合条件的Advisor切入对应的Bean。
     * 需要依赖LifecycleBeanPostProcessor。
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 加入Shiro注解的使用，不加这个AOP注解不生效。
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}