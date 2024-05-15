package app.items;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Types {
    public static Set<TypeUnit> types = new HashSet<>();

    public void createNewType(String name, boolean isDouble, String shortName){
        types.add(new TypeUnit(name, isDouble, shortName));
    }
}
