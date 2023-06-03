package com.finalexample.demo.repository;

import com.finalexample.demo.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car,Long> {

}
