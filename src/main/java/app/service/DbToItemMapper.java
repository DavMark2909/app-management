package app.service;

import app.items.DbItem;
import app.items.Item;
import app.items.TypeUnit;
import app.items.Types;
import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DbToItemMapper {

    private final Types typeConverter;

    public Item getItemFromDb(DbItem db){
        TypeUnit type = typeConverter.getType(db.getType());
        if (type == null)
            throw new RuntimeException("No type with name "  + db.getType());
        return Item.builder().id(db.getId()).name(db.getName()).price(db.getPrice()).type(type).build();
    }

}
