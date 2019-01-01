package com.lambton.project.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.lambton.project.commands.ItemForm;
import com.lambton.project.entity.Item;

@Component
public class ItemToItemForm implements Converter<Item, ItemForm> {
    @Override
    public ItemForm convert(Item item) {
        ItemForm itemForm = new ItemForm();
        itemForm.setId(item.getId());
        itemForm.setName(item.getName());
        itemForm.setDescription(item.getDescription());
        itemForm.setPrice(item.getPrice());
        itemForm.setImageUrl(item.getImageUrl());
        return itemForm;
    }
}
