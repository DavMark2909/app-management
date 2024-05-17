package app.service;

import app.items.DbItem;
import app.items.Item;
import app.items.TypeUnit;

public class ItemToDbConverter {

    public static DbItem updateItemToDb(Item item, DbItem db){
        db.setName(item.getName());
        db.setPrice(item.getPrice());
        TypeUnit type = item.getType();
        db.setDouble(type.isDouble());
        db.setUnit(type.getShortName());
        db.setType(type.getName());
        return db;
    }

    public static DbItem convertItemToDb(Item item){
        DbItem db = new DbItem();
        TypeUnit type = item.getType();
        db.setPrice(item.getPrice());
        db.setStorageQuantity(0);
        db.setUnit(type.getShortName());
        db.setType(type.getName());
        db.setShopQuantity(0);
        db.setDouble(item.isDouble());
        db.setName(item.getName());
        return db;
    }
}
