package com.rsjava.reportgenerator.service;

import com.rsjava.reportgenerator.model.Car;
import com.rsjava.reportgenerator.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> getCars(){
        return carRepository.findAll();
    }

    public Car getCar(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No car by id" + id));
    }
}
