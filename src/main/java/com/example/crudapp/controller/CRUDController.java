package com.example.crudapp.controller;

import com.example.crudapp.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CRUDController {

    private List<Item> items = new ArrayList<>();
    private Long nextId = 1L;

    // Create (Add)
    @GetMapping("/items/new")
    public String newItemForm(Model model) {
        model.addAttribute("item", new Item(0L, "", ""));
        return "item-form";
    }

    @PostMapping("/items")
    public String createItem(@ModelAttribute Item item) {
        item.setId(nextId++);
        items.add(item);
        return "redirect:/items";
    }

    // Read (List)
    @GetMapping("/items")
    public String listItems(Model model) {
        model.addAttribute("items", items);
        return "item-list";
    }

    // Update (Edit)
    @GetMapping("/items/edit/{id}")
    public String editItemForm(@PathVariable Long id, Model model) {
        Item item = items.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
        if (item != null) {
            model.addAttribute("item", item);
            return "item-form";
        }
        return "redirect:/items";
    }

    @PostMapping("/items/update")
    public String updateItem(@ModelAttribute Item item) {
        items.stream().filter(i -> i.getId().equals(item.getId()))
                .forEach(i -> {
                    i.setName(item.getName());
                    i.setDescription(item.getDescription());
                });
        return "redirect:/items";
    }

    // Delete
    @GetMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        items.removeIf(item -> item.getId().equals(id));
        return "redirect:/items";
    }
}
