package com.lambton.project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lambton.project.commands.ItemForm;
import com.lambton.project.converters.ItemToItemForm;
import com.lambton.project.entity.Item;
import com.lambton.project.services.ItemService;
import com.lambton.project.validator.ItemValidator;

@Controller
public class ItemController {
	private ItemService itemService;

	private ItemToItemForm itemToItemForm;

	private ItemValidator itemValidator;

	@Autowired
	public void setItemToItemForm(ItemToItemForm itemToItemForm) {
		this.itemToItemForm = itemToItemForm;
	}

	@Autowired
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@Autowired
	public void setItemValidator(ItemValidator itemValdiator) {
		this.itemValidator = itemValdiator;
	}

	@RequestMapping({ "/item/list", "/item" })
	public String listItems(Model model) {
		model.addAttribute("items", itemService.listAll());
		return "item/list";
	}

	@RequestMapping("/item/show/{id}")
	public String getItem(@PathVariable String id, Model model) {
		model.addAttribute("item", itemService.getById(Long.valueOf(id)));
		return "item/show";
	}

	@RequestMapping("item/edit/{id}")
	public String edit(@PathVariable String id, Model model) {
		Item item = itemService.getById(Long.valueOf(id));
		ItemForm itemForm = itemToItemForm.convert(item);

		model.addAttribute("itemForm", itemForm);
		return "item/itemform";
	}

	@RequestMapping("/item/new")
	public String newItem(Model model) {
		model.addAttribute("itemForm", new ItemForm());
		return "item/itemform";
	}

	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public String saveOrUpdateItem(@Valid ItemForm itemForm, BindingResult bindingResult) {
		itemValidator.validate(itemForm, bindingResult);
		if (bindingResult.hasErrors()) {
			return "item/itemform";
		}

		Item savedItem = itemService.saveOrUpdateItemForm(itemForm);

		return "redirect:/item/show/" + savedItem.getId();
	}

	@RequestMapping("/item/delete/{id}")
	public String delete(@PathVariable String id) {
		itemService.delete(Long.valueOf(id));
		return "redirect:/item/list";
	}

	@ModelAttribute("oneToTen")
	public List<Integer> populateNum() {
		List<Integer> num = new ArrayList<Integer>();
		for (int i = 0; i <= 10; i++) {
			num.add((Integer) i);
		}
		return num;
	}
}
