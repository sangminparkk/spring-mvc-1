package com.chandler.springmvc1.spring.project.controller;

import com.chandler.springmvc1.spring.project.domain.DeliveryCode;
import com.chandler.springmvc1.spring.project.domain.Item;
import com.chandler.springmvc1.spring.project.domain.ItemRepository;
import com.chandler.springmvc1.spring.project.domain.ItemType;
import com.chandler.springmvc1.spring.project.dto.ItemSaveForm;
import com.chandler.springmvc1.spring.project.dto.ItemUpdateForm;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/basic/items")
@Controller
public class BasicItemController {

    private final ItemRepository itemRepository;

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>();
        regions.put("SEOUL", "서울");
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        return regions;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        List<DeliveryCode> deliveryCodes = new ArrayList<>();
        deliveryCodes.add(new DeliveryCode("FAST", "빠른 배송"));
        deliveryCodes.add(new DeliveryCode("NORMAL", "일반 배송"));
        deliveryCodes.add(new DeliveryCode("SLOW", "느린 배송"));
        return deliveryCodes;
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/{itemId}")
    public String getItem(@PathVariable Long itemId, Model model) {
        var item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "addForm";
    }

    @PostMapping("/add")
    public String addItem(@Valid @ModelAttribute("item") ItemSaveForm itemSaveForm, Errors errors, RedirectAttributes redirectAttributes, Model model) {
        Item item = new Item(itemSaveForm.getItemName(), itemSaveForm.getPrice(), itemSaveForm.getQuantity());

        totalAmountMin(item, errors);
        if (errors.hasErrors()) {
            log.info("errors={}", errors);
            return "addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true); //?status=true
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @Valid @ModelAttribute("item") ItemUpdateForm itemUpdateForm, Errors errors) {
        Item updateParam = new Item(itemUpdateForm.getItemName(), itemUpdateForm.getPrice(), itemUpdateForm.getQuantity());

        totalAmountMin(updateParam, errors);
        if (errors.hasErrors()) {
            return "editForm";
        }

        itemRepository.update(itemId, updateParam);
        return "redirect:/basic/items/" + itemId;
    }

    @PostConstruct // for test
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

    private static void totalAmountMin(Item item, Errors errors) {
        if (item.getQuantity() != null && item.getPrice() != null) {
            int resultPrice = item.getQuantity() * item.getPrice();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }


}
