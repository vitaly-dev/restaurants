package com.github.vitalydev.restaurants.web.restaurant;

import com.github.vitalydev.restaurants.model.Restaurant;
import com.github.vitalydev.restaurants.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

@Slf4j
public abstract class AbstractRestaurantController {

    @Autowired
    protected RestaurantRepository repository;

    public Restaurant get(int restId) {
        log.info("get restaurant id={}", restId);
        return repository.checkExistence(restId);
    }

    public List<Restaurant> getAll() {
        log.info("getAll restaurants");
        return repository.findAll(Sort.by("name"));
    }
}