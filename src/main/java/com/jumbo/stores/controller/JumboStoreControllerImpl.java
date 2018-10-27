package com.jumbo.stores.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumbo.stores.dto.StoreDTO;
import com.jumbo.stores.dto.StoreLocationDTO;
import com.jumbo.stores.service.IJumboStoreService;

@RestController
@RequestMapping("/api")
public class JumboStoreControllerImpl implements IJumboStoresController {

    @Autowired
    IJumboStoreService jumboStoreService;

    @Override
    @GetMapping("/list/nearest/stores")
    public List<StoreLocationDTO> getNearestStores(@RequestParam(name = "latitude", required = true) Double latitude,
            @RequestParam(name = "longitude", required = true) Double longitude,
            @RequestParam(name = "numNeighbour", defaultValue = "5") Integer numNeighbour) {
        return jumboStoreService.getNearestStoresByLocation(latitude, longitude, numNeighbour);
    }

    @Override
    @GetMapping("/list/stores")
    public List<StoreLocationDTO> getAllStores() {
        return jumboStoreService.getAllStoresLocation();
    }

    @Override
    @GetMapping("/store/{uuid}")
    public StoreDTO getStoreDetails(@PathVariable("uuid") String id) {
        return jumboStoreService.getStoreDetails(id);
    }

}