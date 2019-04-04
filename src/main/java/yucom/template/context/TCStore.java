package yucom.template.context;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TCStore {
    public static ThreadLocal<Map<String, Object>> state = ThreadLocal.withInitial(ConcurrentHashMap::new);

    public static void save(String key, String value) {
        MDC.put(key, value);
        state.get().put(key, value);
    }

    public static void save(String key, Object value) {
        state.get().put(key, value);
    }

    public static Map<String, Object> get() {
        return state.get();
    }

    public static void set(Map<String, Object> value) {
        state.set(value);
        MDC.setContextMap(
                value.entrySet()
                        .stream()
                        .filter(emtry -> String.class.isAssignableFrom(emtry.getValue().getClass()))
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().toString())));
    }

    public static void clear() {
        MDC.clear();
        state.remove();
    }
}
