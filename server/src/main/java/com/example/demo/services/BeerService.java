package com.example.demo.services;

import com.example.demo.model.Beer;
import com.example.demo.repository.BeerRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Stream;

@Service
public class BeerService implements CommandLineRunner {
    private BeerRepository repository;

    @Autowired
    BeerService(BeerRepository repository){
        this.repository = repository;
    }
    
    public Collection<Beer> findAll(){
        return repository.findAll();
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Kentucky Brunch Brand Scott",
                "Good Morning",
                "Very Hazy",
                "King Julius",
                "Budweiser",
                "Coors Light").forEach(
                name -> repository.save(new Beer(name))
        );

        repository.findAll()
                .forEach(
                        beer -> System.out.println(new JSONObject(beer))
                );
    }

    public Beer addBeer(Beer beer) {
        repository.save(beer);
        return beer;
    }

    public void deleteBeer(Beer beer) {
        repository.delete(beer);
    }
}
