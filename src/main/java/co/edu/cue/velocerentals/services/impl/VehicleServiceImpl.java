package co.edu.cue.velocerentals.services.impl;

import co.edu.cue.velocerentals.model.Vehicle;
import co.edu.cue.velocerentals.services.VehicleService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named
public class VehicleServiceImpl implements VehicleService {
    @Override
    public List<Vehicle> toList() {
        return Arrays.asList(new Vehicle(1, "ferrari F-40", 30000,"carro", "disponible","deportivo"),
                new Vehicle(2, "FORD raptor ", 10000,"carro", "no disponible","camioneta"),
                new Vehicle(3, "mt-09", 1000,"moto", "en reserva","deportiva"));
    }

    @Override
    public Optional<Vehicle> byId(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Vehicle vehicle) {

    }

    @Override
    public void delete(int id) {

    }
}
