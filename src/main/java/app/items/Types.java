package app.items;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Types {
    public static Map<String, TypeUnit> types = new HashMap<>();

    public void createNewType(String name, boolean isDouble, String shortName){
        types.put(name, new TypeUnit(name, isDouble, shortName));
    }

    public TypeUnit getType(String name){
        return types.get(name);
    }
}
