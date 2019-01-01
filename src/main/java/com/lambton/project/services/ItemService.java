package com.lambton.project.services;

import java.util.List;

import com.lambton.project.commands.ItemForm;
import com.lambton.project.entity.Item;

public interface ItemService {

    List<Item> listAll();

    Item getById(Long id);

    Item saveOrUpdate(Item item);

    void delete(Long id);

    Item saveOrUpdateItemForm(ItemForm itemForm);
}
