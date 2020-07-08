package com.pcbang.order.mvp.item;

import com.pcbang.order.mvp.domain.item.dto.ItemInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ApiOperation(value = "상품 생성", tags = "item")
    @ApiResponses({
            @ApiResponse(code = 200, message = "생성 성공"),
    })
    public ResponseEntity createItem(@ModelAttribute ItemInfo itemInfo){
        Long id = itemService.save(itemInfo);
        return ResponseEntity.created(URI.create("/items/" + id)).build();
    }

    @GetMapping
    @ApiOperation(value = "전체 상품 목록 가져오기", tags = "item")
    @ApiResponses({
            @ApiResponse(code = 200, message = "가져오기 성공"),
    })
    public ResponseEntity<List<ItemInfo>> showAllItemInfo() {
        List<ItemInfo> itemInfos = itemService.findAll();
        return ResponseEntity.ok(itemInfos);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "상품 상세 정보 가져오기", tags = "item")
    @ApiResponses({
            @ApiResponse(code = 200, message = "가져오기 성공"),
    })
    public ResponseEntity<ItemInfo> showItemInfo(@PathVariable Long id){
        ItemInfo itemInfo = itemService.findById(id);
        return ResponseEntity.ok(itemInfo);
    }

    @PostMapping("/{id}")
    @ApiOperation(value = "상품 수정", tags = "item")
    public ResponseEntity updateItem(@PathVariable Long id, @ModelAttribute ItemInfo itemInfo){
        itemService.updateItem(id, itemInfo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "상품 삭제", tags = "item")
    public ResponseEntity deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler
    public ResponseEntity eventErrorHandler(NotFoundItemException exception){
        return ResponseEntity.notFound().build();
    }
}
