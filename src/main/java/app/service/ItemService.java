package app.service;

import app.dto.ItemCreationDto;
import app.exception.ItemAlreadyExists;
import app.exception.ItemNotFound;
import app.items.DbItem;
import app.items.Item;
import app.items.TypeStorage;
import app.items.TypeUnit;
import app.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;
    private final ItemToDbConverter converter;
    private final TypeStorage typeStorage;

    public DbItem getItemById(long id){
        return repository.findDbItemById(id).orElseThrow(() -> new ItemNotFound("Could not find item with id "  + id));
    }

    public DbItem getItemByName(String name){
        return repository.findDbItemByName(name).orElseThrow(() -> new ItemNotFound("Could not find item with name "  + name));
    }

    public DbItem updateDbItem(long id, Item newInstance){
        DbItem dbItem = repository.findDbItemById(id).orElseThrow(() -> new ItemNotFound("Could not find item with id " + id));
        DbItem updated = converter.updateItemToDb(newInstance, dbItem);
        return repository.save(updated);
    }

    public DbItem increaseQuantity(long id, double change){
        DbItem dbItem = repository.findDbItemById(id).orElseThrow(() -> new ItemNotFound("Could not find item with id " + id));
        dbItem.setStorageQuantity(dbItem.getStorageQuantity() - change);
        return repository.save(dbItem);
    }

    public DbItem decreaseQuantity(long id, double change){
        DbItem dbItem = repository.findDbItemById(id).orElseThrow(() -> new ItemNotFound("Could not find item with id " + id));
        dbItem.setStorageQuantity(dbItem.getStorageQuantity() + change);
        return repository.save(dbItem);
    }

    public List<DbItem> getAllItems(){
        return repository.findAll();
    }

    public List<DbItem> getAllItemsByType(String type){
        return repository.findAllByType(type);
    }

    public void delete(long id){
        repository.deleteDbItemById(id);
    }

    public DbItem createNewItem(ItemCreationDto dto) throws Exception {
        Optional<DbItem> dbItemByName = repository.findDbItemByName(dto.getName());
        if (dbItemByName.isPresent())
            throw new ItemAlreadyExists(dto.getName());
        TypeUnit type = typeStorage.getType(dto.getType());
        if (type == null)
            throw new Exception("Attempt to add a new item with a non-existing type");
        Item item = Item.builder().price(dto.getPrice()).name(dto.getName()).type(type).isDouble(dto.isDouble()).build();
        DbItem dbItem = ItemToDbConverter.convertItemToDb(item);
        return repository.save(dbItem);
    }
}
