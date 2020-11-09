package threads.patterns.immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class MSRouter {

    private static volatile MSRouter instance = new MSRouter();

    private final Map<String, MSInfo> routeMap;

    public MSRouter(){
        this.routeMap = MSRouter.retrieveFromDB();
    }

    private static Map<String, MSInfo> retrieveFromDB() {
        Map<String, MSInfo> map = new HashMap<>();
        //Init route data from db

        return map;
    }

    public MSInfo getMSInfo(String msKey){
        return routeMap.get(msKey);
    }

    public static void setInstance(MSRouter newInstance){
        instance = newInstance;
    }

    private static Map<String, MSInfo> deepCopy(Map<String, MSInfo> map){
        Map<String, MSInfo> newMap = new HashMap<>();
        for(String key : map.keySet()){
            newMap.put(key, new MSInfo(map.get(key)));
        }
        return newMap;
    }

    public Map<String, MSInfo> getRouteMap(){
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }

}
