package yucom.template.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TCStore {
    public static ThreadLocal<Map<String, Object>> state = ThreadLocal.withInitial(ConcurrentHashMap::new);

    public static void save(String key, Object value) {
        state.get().put(key, value);
    }

    public static Map<String, Object> get() {
        return state.get();
    }

    public static void set(Map<String, Object> value) {
        state.set(value);
    }

    public static void clear() {
        state.remove();
    }
}
