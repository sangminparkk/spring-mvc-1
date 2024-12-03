package com.chandler.springmvc1.spring.project.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    private ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    @DisplayName("상품 저장")
    void save() {
        // given
        var itemA = new Item("ItemA", 12000, 10);

        // when
        itemRepository.save(itemA);

        // then
        var findItem = itemRepository.findById(itemA.getId());
        assertEquals(itemA.getId(), findItem.getId());
        assertEquals("ItemA", findItem.getItemName());
        assertEquals(12000, findItem.getPrice());
        assertEquals(10, findItem.getQuantity());
    }

    @Test
    @DisplayName("상품 전체 조회")
    void findAll() {
        // given
        var itemA = new Item("ItemA", 12000, 10);
        var itemB = new Item("ItemB", 20000, 20);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertNotNull(items);
        assertEquals(2L, items.size());

    }

    @Test
    @DisplayName("상품 수정")
    void update() {
        // given
        var itemA = new Item("ItemA", 12000, 10);
        itemRepository.save(itemA);

        // when
        var updateParam = new Item("ItemB", 20000, 20);
        itemRepository.update(itemA.getId(), updateParam);

        // then
        Item findItem = itemRepository.findById(itemA.getId());

        assertEquals("ItemB", findItem.getItemName());
        assertEquals(20000, findItem.getPrice());
        assertEquals(20, findItem.getQuantity());
    }

}