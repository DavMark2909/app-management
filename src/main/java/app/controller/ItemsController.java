package app.controller;

import app.dto.ItemCreationDto;
import app.dto.TypeCreationDto;
import app.exception.TypeAlreadyExists;
import app.items.Item;
import app.service.DbToItemMapper;
import app.service.ItemService;
import app.service.TypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ItemsController {
    private final ItemService service;
    private final TypeService typeService;
    private final DbToItemMapper mapper;

    @GetMapping("/getAll")
    public ResponseEntity<List<Item>> getAllItems(){
        List<Item> list = service.getAllItems().stream().map(mapper::getItemFromDb).toList();
        log.info("got the items from the database");
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getItems/{type}")
    public ResponseEntity<List<Item>> getTypeItems(@RequestParam("type") String type){
        List<Item> items = service.getAllItemsByType(type).stream().map(mapper::getItemFromDb).toList();
        log.info("Got the items of the type " + type);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/createType")
    public ResponseEntity<?> createNewType(@RequestBody TypeCreationDto dto){
        try {
            return ResponseEntity.ok(typeService.createNewType(dto));
        } catch (TypeAlreadyExists e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/newItem")
    public ResponseEntity<?> addNewItem(@RequestBody ItemCreationDto dto){
        try{
            return ResponseEntity.ok(service.createNewItem(dto));
        }
    }

}
