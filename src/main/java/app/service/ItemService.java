package app.service;

import app.exception.ItemNotFound;
import app.items.DbItem;
import app.items.Item;
import app.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;
    private final ItemToDbConverter converter;

    public DbItem getItemById(long id){
        return repository.findDbItemById(id).orElseThrow(() -> new ItemNotFound("Could not find item with id "  + id));
    }

    public DbItem getItemByName(String name){
        return repository.findDbItemByName(name).orElseThrow(() -> new ItemNotFound("Could not find item with name "  + name));
    }

    public DbItem updateDbItem(long id, Item newInstance){
        DbItem dbItem = repository.findDbItemById(id).orElseThrow(() -> new ItemNotFound("Could not find item with id " + id));
        DbItem updated = converter.convertItemToDb(newInstance, dbItem);
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

}
