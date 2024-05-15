package app.service;

import app.items.DbItem;
import app.items.Item;
import app.items.TypeUnit;
import org.springframework.stereotype.Service;

@Service
public class ItemToDbConverter {

    public DbItem convertItemToDb(Item item, DbItem db){
        db.setName(item.getName());
        db.setPrice(item.getPrice());
        TypeUnit type = item.getType();
        db.setDouble(type.isDouble());
        db.setUnit(type.getShortName());
        db.setType(type.getName());
        return db;
    }
}
