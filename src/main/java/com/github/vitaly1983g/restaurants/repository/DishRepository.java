package com.github.vitaly1983g.restaurants.repository;

import com.github.vitaly1983g.restaurants.error.DataConflictException;
import com.github.vitaly1983g.restaurants.model.Dish;
import com.github.vitaly1983g.restaurants.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.restId=:restId ORDER BY d.restId, d.name")
    List<Dish> getAll(int restId);

    @Query("SELECT d FROM Dish d WHERE d.restId=:restId AND d.id IN (:ids) ORDER BY d.restId, d.name")
    List<Dish> getAllCollection(int restId, List<Integer> ids);

/*    @Query("SELECT d from Dish d WHERE d.restId=:restId AND d.dateTime >= :startDate AND d.dateTime < :endDate ORDER BY d.dateTime DESC")
    List<Dish> getBetweenHalfOpen(LocalDateTime startDate, LocalDateTime endDate, int restId);*/

    @Query("SELECT d FROM Dish d WHERE d.id = :id and d.restId = :restId")
    Optional<Dish> get(int id, int restId);

/*    @Query("SELECT d FROM Dish d JOIN FETCH d.restId WHERE d.id = :id and d.restId= :restId")
    Optional<Dish> getWithUser(int id, int restId);*/

    default Dish checkBelong(int id, int restId) {
        return get(id, restId).orElseThrow(
                () -> new DataConflictException("Dish id=" + id + " doesn't belong to Restaurant id=" + restId));
    }

}