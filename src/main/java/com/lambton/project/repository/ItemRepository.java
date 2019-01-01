package com.lambton.project.repository;

import org.springframework.data.repository.CrudRepository;

import com.lambton.project.entity.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
