package com.github.dtrunk90.sbt;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;

@EnableWebMvc
@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
public class Application extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Autowired
	private MessageSource messageSource;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setCharacterEncoding("UTF-8");
		return resolver;
	}

	private TemplateEngine templateEngine() {
		System.out.println("### " + messageSource);
		System.out.println("###" + messageSource.getMessage("hello", null, Locale.getDefault()));
		SpringTemplateEngine engine = new SpringTemplateEngine();
		//engine.setMessageSource(messageSource);
		//engine.setTemplateEngineMessageSource(messageSource);
		engine.setEnableSpringELCompiler(true);
		engine.setTemplateResolver(templateResolver());
		engine.addDialect(new DataAttributeDialect());
		return engine;
	}

	private ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		return resolver;
	}
}