package com.github.aetherwisp.volvox.application.webmvc;

import java.nio.charset.StandardCharsets;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

// @Configuration
public abstract class AbstractWebMvcConfigurer
        implements WebMvcConfigurer, ApplicationContextAware {
    //======================================================================
    // Fields
    @Autowired
    private MessageSource messageSource;

    private ApplicationContext applicationContext;

    private final String htmlPrefix;

    private final String javascriptPrefix;

    //======================================================================
    // Constructors
    public AbstractWebMvcConfigurer(final String _htmlPrefix, final String _javascriptPrefix) {
        this.htmlPrefix = _htmlPrefix;
        this.javascriptPrefix = _javascriptPrefix;
    }

    //======================================================================
    // Methods
    @Override
    public void setApplicationContext(ApplicationContext _applicationContext)
            throws BeansException {
        this.applicationContext = _applicationContext;
    }

    //======================================================================
    // Components
    @Bean
    public ViewResolver htmlViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setContentType(MediaType.TEXT_HTML_VALUE);
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setViewNames(new String[] {"*.html"});
        return resolver;
    }

    @Bean
    public ViewResolver javascriptViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setContentType("text/javascript");
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setViewNames(new String[] {"*.js"});
        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setMessageSource(this.messageSource);
        engine.addTemplateResolver(this.htmlTemplateResolver());
        engine.addTemplateResolver(this.javascriptTemplateResolver());
        return engine;
    }

    private ITemplateResolver htmlTemplateResolver() {
        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setOrder(0);
        resolver.setCheckExistence(true);
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix(this.htmlPrefix);
        resolver.setCacheable(false);
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setSuffix(".html");
        return resolver;
    }

    public ITemplateResolver javascriptTemplateResolver() {
        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setOrder(1);
        resolver.setCheckExistence(true);
        resolver.setPrefix(this.javascriptPrefix);
        resolver.setCacheable(false);
        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
        resolver.setSuffix(".js");
        return resolver;
    }
}
