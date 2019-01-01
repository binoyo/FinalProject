package com.lambton.project.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.lambton.project.commands.ItemForm;
import com.lambton.project.entity.Item;

@Component
public class ItemFormToItem implements Converter<ItemForm, Item> {

    @Override
    public Item convert(ItemForm itemForm) {
        Item item = new Item();
        if (itemForm.getId() != null  && !StringUtils.isEmpty(itemForm.getId())) {
            item.setId(new Long(itemForm.getId()));
        }
        item.setName(itemForm.getName());
        item.setDescription(itemForm.getDescription());
        item.setPrice(itemForm.getPrice());
        item.setImageUrl(itemForm.getImageUrl());
        return item;
    }
}
