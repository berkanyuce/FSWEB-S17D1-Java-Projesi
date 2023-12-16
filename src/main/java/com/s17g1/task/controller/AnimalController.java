package com.s17g1.task.controller;

import com.s17g1.task.entity.Animal;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnimalController {
    private Map<Integer, Animal> animals;

    @PostConstruct
    public void loadAll() {
        this.animals = new HashMap<>();
        this.animals.put(1, new Animal(1, "maymun"));
    }

    @GetMapping(path = "/workintech/animal")
    public List<Animal> getAnimals() {
        System.out.println("---animals list get triggered---");
        return new ArrayList<>(this.animals.values());
    }

    @GetMapping(path = "/workintech/animal/{id}")
    public Animal getAnimal(@PathVariable("id") Integer id) {
        System.out.println("---animals get by id triggered---");
        return this.animals.get(id);
    }

    @PostMapping(path = "/workintech/animal")
    public void addAnimal(@RequestBody Animal animal) {
        this.animals.put(animal.getId(), animal);
    }

    @PutMapping(path = "/workintech/animal/{id}")
    public Animal updateAnimal(@PathVariable("id") Integer existingRecordId, @RequestBody Animal newAnimal) {
        this.animals.replace(existingRecordId, newAnimal);
        return this.animals.get(existingRecordId);
    }

    @DeleteMapping(path = "/workintech/animal/{id}")
    public void deleteAnimal(@PathVariable("id") Integer id){
        this.animals.remove(id);
    }
}
