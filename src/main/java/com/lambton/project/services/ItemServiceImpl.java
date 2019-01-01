package com.lambton.project.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lambton.project.commands.ItemForm;
import com.lambton.project.converters.ItemFormToItem;
import com.lambton.project.entity.Item;
import com.lambton.project.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    private ItemRepository itemRepository;
    private ItemFormToItem itemFormToItem;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ItemFormToItem itemFormToItem) {
        this.itemRepository = itemRepository;
        this.itemFormToItem = itemFormToItem;
    }


    @Override
    public List<Item> listAll() {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add); 
        return items;
    }

    @Override
    public Item getById(Long id) {
    	if (itemRepository.findOne(id) == null) {
    		logger.warn("Item not found!");
    	}
        return itemRepository.findOne(id);
    }

    @Override
    public Item saveOrUpdate(Item item) {
        itemRepository.save(item);
        return item;
    }

    @Override
    public void delete(Long id) {
        itemRepository.delete(id);

    }

    @Override
    public Item saveOrUpdateItemForm(ItemForm itemForm) {
        Item savedItem = saveOrUpdate(itemFormToItem.convert(itemForm));

        logger.info("Saved Item Id: " + savedItem.getId());
        return savedItem;
    }
}
