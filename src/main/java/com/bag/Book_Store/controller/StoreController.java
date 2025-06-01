package com.bag.Book_Store.controller;

import com.bag.Book_Store.model.dto.response.StoreResponse;
import com.bag.Book_Store.model.entity.Store;
import com.bag.Book_Store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StoreController {
//    private final List<Store> storeList = List.of(new Store("Sede Surco", "Av. Caminos del Inca 2570, Santiago de Surco, Lima", "Lunes a sábado: 8:00 a. m. – 8:00 p. m.", "https://maps.google.com/maps?q=Av.+Caminos+del+Inca+2570,+Surco,+Lima&output=embed"),
//            new Store("Sede San Miguel", "Av. La Marina 2155, San Miguel, Lima", "Lunes a sábado: 8:00 a. m. – 8:00 p. m.", "https://maps.google.com/maps?q=Av.+La+Marina+2155,+San+Miguel,+Lima&output=embed"),
//            new Store("Sede Miraflores", "Av. José Pardo 610, Miraflores, Lima", "Lunes a viernes: 8:00 a. m. – 8:00 p. m. | Sábados: 9:00 a. m. – 8:00 p. m.", "https://maps.google.com/maps?q=Av.+José+Pardo+610,+Miraflores,+Lima&output=embed"));

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

}
