package com.pcbang.order.mvp.item;

import com.pcbang.order.mvp.domain.item.Item;
import com.pcbang.order.mvp.domain.item.dto.ItemInfo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private ModelMapper modelMapper;

    public ItemService(ItemRepository itemRepository, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }

    public Long save(ItemInfo itemInfo) {
        Item item = itemInfo.toEntity();
        return itemRepository.save(item).getId();
    }

//    @Transactional(readOnly = true)
//    public List<ItemInfo> findAll() {
//        return itemRepository.findAll().stream()
//                .map(item -> modelMapper.map(item, ItemInfo.class))
//                .collect(Collectors.toList());
//    }

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll().stream()
                .map(item -> modelMapper.map(item, Item.class))
                .collect(Collectors.toList());
    }

//    @Transactional(readOnly = true)
//    public ItemInfo findById(Long id) {
//        Item item = itemRepository.findById(id).orElseThrow(NotFoundItemException::new);
//        return modelMapper.map(item, ItemInfo.class);
//    }

    @Transactional(readOnly = true)
    public Item findById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(NotFoundItemException::new);
        return modelMapper.map(item, Item.class);
    }

    public void updateItem(Long id, ItemInfo itemInfo) {
        Item item = itemRepository.findById(id).orElseThrow(NotFoundItemException::new);
        item.updateTo(itemInfo);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
