package com.example.ec.repo;

import com.example.ec.domin.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour, Integer> {
}
