package app.items;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
public class TypeStorage {
    private final Map<String, TypeUnit> types = new HashMap<>();

    public void addType(TypeUnit type){
        types.put(type.getName(), type);
    }

    public TypeUnit getType(String name){
        return types.get(name);
    }

}
