package com.alekseytyan.logiweb.config.security;

import com.alekseytyan.logiweb.config.security.handler.CustomAuthenticationFailureHandler;
import com.alekseytyan.logiweb.config.security.handler.CustomLogoutSuccessHandler;
import com.alekseytyan.logiweb.config.DataSourceConfig;
import com.alekseytyan.logiweb.config.security.handler.CustomAccessDeniedHandler;
import com.alekseytyan.logiweb.config.security.handler.CustomSuccessLoginHandler;
import com.alekseytyan.logiweb.service.api.LoginAttemptService;
import com.alekseytyan.logiweb.service.implementation.LoginAttemptServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
@Import(value = {
        DataSourceConfig.class,
})
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
//    private final AuthenticationSuccessHandler successHandler;
//    private final AuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select EMAIL, PASSWORD, ENABLED"
                        + " from USER_LOGIWEB where EMAIL = ?")
                .authoritiesByUsernameQuery("select EMAIL, ROLE "
                        + "from USER_LOGIWEB where EMAIL = ?")
                .passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .expressionHandler(webExpressionHandler())
                .antMatchers("/info-table/**").anonymous()
                .antMatchers("/driver/**").hasRole("DRIVER")
                .antMatchers("/employee/**").hasAnyRole("EMPLOYEE")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login*", "/register*", "/forgotPassword*", "/*", "/homePage*", "/welcome*", "/assets/**").permitAll()
                .antMatchers("/profile*").hasAnyRole("DRIVER", "EMPLOYEE", "ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
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
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSuccessLoginHandler(loginAttemptService());
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler(loginAttemptService());
    }

    @Bean
    public LoginAttemptService loginAttemptService() {
        return new LoginAttemptServiceImpl();
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