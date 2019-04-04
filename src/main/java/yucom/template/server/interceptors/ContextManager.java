package yucom.template.server.interceptors;

import com.sun.xml.internal.ws.wsdl.writer.document.soap.HeaderFault;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import yucom.template.context.TCStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class ContextManager extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TCStore.clear();

        List xheaders = Collections.list(request.getHeaderNames())
                .stream()
                .filter(name -> name.startsWith("X-"))
                .map(name -> new Pair<>(name, request.getHeader(name)))
                .collect(Collectors.toList());

        TCStore.save("xheaders", xheaders);

        return true;
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        TCStore.clear();
    }
}
