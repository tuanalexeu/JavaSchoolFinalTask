package com.alekseytyan.logiweb.config.init;

import com.alekseytyan.logiweb.config.*;
import com.alekseytyan.logiweb.config.AsynchronousSpringEventsConfig;
import com.alekseytyan.logiweb.config.schedule.SchedulingConfig;
import com.alekseytyan.logiweb.config.security.WebSecurityConfig;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

public class MainWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext sc) {

        var ctx = new AnnotationConfigWebApplicationContext();

        ctx.register(AsynchronousSpringEventsConfig.class);
        ctx.register(DataMappingConfig.class);
//        ctx.register(DataSourceConfig.class);
        ctx.register(EmailConfig.class);
//        ctx.register(WebConfig.class);
        ctx.register(WebSecurityConfig.class);
        ctx.register(SchedulingConfig.class);


        ctx.setServletContext(sc);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(ctx);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        var servlet = sc.addServlet("dispatcher", dispatcherServlet);

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");


        sc.addListener(new ContextLoaderListener(ctx));
        sc.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
    }
}