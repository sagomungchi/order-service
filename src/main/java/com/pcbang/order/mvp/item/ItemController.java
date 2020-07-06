package com.pcbang.order.mvp.item;

import com.pcbang.order.mvp.domain.item.dto.ItemInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity createItem(@ModelAttribute ItemInfo itemInfo){
        Long id = itemService.save(itemInfo);
        return ResponseEntity.created(URI.create("/items/" + id)).build();
    }

    @GetMapping
    public ResponseEntity<List<ItemInfo>> showAllItemInfo() {
        List<ItemInfo> itemInfos = itemService.findAll();
        return ResponseEntity.ok(itemInfos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemInfo> showItemInfo(@PathVariable Long id){
        ItemInfo itemInfo = itemService.findById(id);
        return ResponseEntity.ok(itemInfo);
    }

    @PostMapping("/{id}")
    public ResponseEntity updateItem(@PathVariable Long id, @ModelAttribute ItemInfo itemInfo){
        itemService.updateItem(id, itemInfo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity eventErrorHandler(NotFoundItemException exception){
        return ResponseEntity.notFound().build();
    }
}
