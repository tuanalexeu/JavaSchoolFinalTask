package com.alekseytyan.logiweb.config.security;

import com.alekseytyan.logiweb.config.DataSourceConfig;
import com.alekseytyan.logiweb.config.security.handler.CustomLogoutSuccessHandler;
import com.alekseytyan.logiweb.config.security.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(value = {"com.alekseytyan.logiweb"})
@Import(value = {
        DataSourceConfig.class
})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private AuthenticationProvider authenticationProvider;

    private AuthenticationSuccessHandler successHandler;
    private AuthenticationFailureHandler failureHandler;

    private DataSource dataSource;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setAuthenticationProvider(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Autowired
    public void setSuccessHandler(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Autowired
    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select EMAIL, PASSWORD, ENABLED"
                        + " from USER_LOGIWEB where EMAIL = ?")
                .authoritiesByUsernameQuery("select EMAIL, ROLE "
                        + "from USER_LOGIWEB where EMAIL = ?")
                .passwordEncoder(passwordEncoder());

//        auth.userDetailsService(userDetailsService);
//        auth.authenticationProvider(authenticationProvider);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .expressionHandler(webExpressionHandler())
                .antMatchers("/info-table/**").anonymous()
                .antMatchers("/driver/**").hasRole("DRIVER")
                .antMatchers("/employee/**").hasAnyRole("EMPLOYEE", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login*", "/register*", "/forgotPassword*", "/*", "/homePage*", "/welcome*", "/assets/**").permitAll()
                .antMatchers("/profile*").hasAnyRole("DRIVER", "EMPLOYEE", "ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .defaultSuccessUrl("/homePage", true)
                .and()
                .logout()
                .logoutUrl("/performLogOut")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler())
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl r = new RoleHierarchyImpl();
        r.setHierarchy("ROLE_ADMIN > ROLE_EMPLOYEE and ROLE_ADMIN > ROLE_DRIVER");
        return r;
    }

    private SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {
        DefaultWebSecurityExpressionHandler defaultWebSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
        defaultWebSecurityExpressionHandler.setRoleHierarchy(roleHierarchy());
        return defaultWebSecurityExpressionHandler;
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}