package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.request.CreateStoreRequest;
import com.bag.Book_Store.model.dto.response.StoreResponse;
import com.bag.Book_Store.model.entity.Store;
import com.bag.Book_Store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping({"/contactanos"})
    public String mostrarTiendas(Model model, @RequestParam(required = false) String storeName) {
        List<StoreResponse> storeList = storeService.findAll();
        model.addAttribute("stores", storeList);

        StoreResponse storeSelected = storeList.stream()
                .filter((t) -> t.getName().equals(storeName))
                .findFirst()
                .orElse(storeList.getFirst());
        model.addAttribute("storeSelected", storeSelected);
        return "contactanos";
    }

    @GetMapping({"/admin/stores"})
    public String findAll(Model model) {
        model.addAttribute("stores", storeService.findAll());
        return "admin/stores/list";
    }

    @GetMapping("/admin/stores/new")
    public String showAddStoreForm(Model model) {
        model.addAttribute("store", new Store());
        return "admin/stores/form";
    }

    @PostMapping("/admin/stores/save")
    public String addStore(@ModelAttribute("store")CreateStoreRequest store) {
        storeService.save(store);
        return "redirect:/admin/stores";
    }
}
