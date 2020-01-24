package com.example.demo.controller;

import com.example.demo.model.Beer;
import com.example.demo.repository.BeerRepository;
import com.example.demo.services.BeerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class BeerController {
    private BeerService service;

    public BeerController(BeerService service){
        this.service = service;
    }

    @CrossOrigin
    @GetMapping("/")
    public Collection<Beer> list() {
        return service.findAll();
    }

    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<?> addBeer(@RequestBody Beer beer){
        Beer responseBeer = service.addBeer(beer);
        ResponseEntity<?> responseAddBeer = new ResponseEntity<>(responseBeer, HttpStatus.CREATED);
        return responseAddBeer;
    }

    @CrossOrigin
    @DeleteMapping("/")
    public void deleteBeer(@RequestBody Beer beer){
        service.deleteBeer(beer);
    }
}
