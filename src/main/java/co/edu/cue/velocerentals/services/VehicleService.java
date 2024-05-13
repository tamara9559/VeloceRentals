package co.edu.cue.velocerentals.services;

import co.edu.cue.velocerentals.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    List<Vehicle> toList();

    Optional<Vehicle> byId(Long id);

    void save(Vehicle vehicle);

    void delete(int id);

}
