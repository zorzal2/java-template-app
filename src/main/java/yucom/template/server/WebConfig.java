package yucom.template.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import yucom.template.server.interceptors.ContextManager;
import yucom.template.server.interceptors.RequestLogger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"yucom"})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestLogger requestLogger;

    @Autowired
    private ContextManager contextManager;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.contextManager);
        registry.addInterceptor(this.requestLogger);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}