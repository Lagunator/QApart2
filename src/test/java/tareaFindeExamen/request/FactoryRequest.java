package tareaFindeExamen.request;

import java.util.HashMap;
import java.util.Map;

public class FactoryRequest {
    private static final Map<String, IRequest> requestMap = new HashMap<>();

    static {
        requestMap.put("put", new ReqPut());
        requestMap.put("post", new ReqPost());
        requestMap.put("get", new ReqGet());
        requestMap.put("delete", new ReqDelete());
    }

    public static IRequest make(String type) {
        String lowerCaseType = type.toLowerCase();
        return requestMap.getOrDefault(lowerCaseType, requestMap.get("get"));
    }
}

