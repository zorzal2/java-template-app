package yucom.template.server.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.UUID;

@Component
public class TxIDManager extends HandlerInterceptorAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(TxIDManager.class.getSimpleName());

    private static String TxID = "TxID";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String transactionID = Collections.list(request.getHeaderNames())
                .stream()
                .filter(name -> TxID.equals(name))
                .findAny()
                .orElseGet(() -> {
                    LOGGER.warn("No transaction id header was found -> A random UUID will be set");
                    return UUID.randomUUID().toString();
                });

        MDC.put(TxID, transactionID);

        response.setHeader("TxID", transactionID);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
