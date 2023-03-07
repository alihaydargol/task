package com.producter.task.repository;

import com.producter.task.model.BasketballPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketballPlayerRepository extends JpaRepository<BasketballPlayer,Long> {

}
