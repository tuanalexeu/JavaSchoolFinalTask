package com.alekseytyan.config.security;

import com.alekseytyan.config.security.handler.CustomAuthenticationFailureHandler;
import com.alekseytyan.config.security.handler.CustomLogoutSuccessHandler;
import com.alekseytyan.config.DataSourceConfig;
import com.alekseytyan.config.security.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(value = {
        DataSourceConfig.class
})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public WebSecurityConfig(DataSource dataSource) {
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
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/driver/**").hasRole("DRIVER")
                .antMatchers("/employee/**").hasAnyRole("EMPLOYEE", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/login*", "/register*", "/forgotPassword*", "/*", "/homePage*", "/welcome*", "/assets/**").permitAll()
                .antMatchers("/profile*").hasAnyRole("DRIVER", "EMPLOYEE", "ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/homePage", true)
                .failureUrl("/login?error=true")
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

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}