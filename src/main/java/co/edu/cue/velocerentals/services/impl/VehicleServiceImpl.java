package co.edu.cue.velocerentals.services.impl;

import co.edu.cue.velocerentals.model.Vehicle;
import co.edu.cue.velocerentals.repositories.Repository;
import co.edu.cue.velocerentals.repositories.VehicleRepositoryJdbcImpl;
import co.edu.cue.velocerentals.services.ServiceJdbcException;
import co.edu.cue.velocerentals.services.VehicleService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VehicleServiceImpl implements VehicleService {

    private Repository<Vehicle> repositoryJdbc;

    public VehicleServiceImpl(Connection connection) {
        this.repositoryJdbc = new VehicleRepositoryJdbcImpl(connection);
    }

    @Override
    public List<Vehicle> toList() {
        try {
            return repositoryJdbc.toList();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Vehicle> byId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.byId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }


    public void save(Vehicle vehicle) {
        try {
            repositoryJdbc.save(vehicle);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }

    }
    public void delete(int id) {
        try {
            repositoryJdbc.delete(id);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
