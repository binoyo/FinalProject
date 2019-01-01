package com.lambton.project.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lambton.project.entity.Item;
import com.lambton.project.repository.ItemRepository;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ItemRepositoryTest {

    private static final BigDecimal BIG_DECIMAL_100 = BigDecimal.valueOf(100.00);
    private static final String ITEM_DESCRIPTION = "a cool item";
    private static final String IMAGE_URL = "http://an-imageurl.com/image1.jpg";

    @Autowired
    private ItemRepository itemRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Item item = new Item();
        item.setDescription(ITEM_DESCRIPTION);
        item.setImageUrl(IMAGE_URL);
        item.setPrice(BIG_DECIMAL_100);

        //when
        itemRepository.save(item);

        //then
        Assert.assertNotNull(item.getId());
        Item newItem = itemRepository.findOne(item.getId());
        Assert.assertEquals((Long) 1L, newItem.getId());
        Assert.assertEquals(ITEM_DESCRIPTION, newItem.getDescription());
        Assert.assertEquals(BIG_DECIMAL_100.compareTo(newItem.getPrice()), 0);
        Assert.assertEquals(IMAGE_URL, newItem.getImageUrl());
    }
}