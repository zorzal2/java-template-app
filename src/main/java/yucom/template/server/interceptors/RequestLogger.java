package yucom.template.server.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class RequestLogger extends HandlerInterceptorAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(RequestLogger.class.getSimpleName());


    private void logInput(String key, Object value) {
        LOGGER.info("Input - "+ key +" : "+ String.valueOf(value));
    }

    private void logOutput(String key, Object value) {
        LOGGER.info("Output - "+ key +" : "+ String.valueOf(value));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String headers = Collections.list(request.getHeaderNames())
                .stream()
                .map((name) -> "\""+ name +"\":\""+ request.getHeader(name)+"\"")
                .collect(Collectors.joining(","));

        this.logInput("url", request.getRequestURL());
        this.logInput("protocol", request.getProtocol());
        this.logInput("method", request.getMethod());
        this.logInput("uri", request.getRequestURI());
        this.logInput("query", request.getQueryString());
        this.logInput("headers", "{"+ headers +"}");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        String headers = response.getHeaderNames()
                .stream()
                .map((name) -> "\""+ name +"\":\""+ request.getHeader(name)+"\"")
                .collect(Collectors.joining(","));

        this.logOutput("status", response.getStatus());
        this.logOutput("headers", "{"+ headers +"}");
    }
}
