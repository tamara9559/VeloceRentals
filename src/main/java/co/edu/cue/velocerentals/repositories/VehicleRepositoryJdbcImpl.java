package co.edu.cue.velocerentals.repositories;

import co.edu.cue.velocerentals.model.Vehicle;
import co.edu.cue.velocerentals.repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryJdbcImpl implements Repository<Vehicle> {
    private Connection conn;

    public VehicleRepositoryJdbcImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Vehicle> toList() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vehiculo")) {
            while (rs.next()) {
                Vehicle v = getVehicle(rs);
                vehicles.add(v);
            }
        }
        return vehicles;
    }

    @Override
    public Vehicle byId(Long id) throws SQLException {
        Vehicle vehicle = null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from vehiculo where id=?")) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = getVehicle(rs);
                }
            }
        }
        return vehicle;
    }

    @Override
    public void save(Vehicle vehicle) throws SQLException {

        String sql;
        if (vehicle.getId() > 0) {
            sql = "update vehiuclo set name=?, price=?, category=?, availability=?, type=? where id=?";
        } else {
            sql = "insert into vehiculo (name, price, category, availability, type) values (?,?,?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vehicle.getName());
            stmt.setDouble(2, vehicle.getPrice());
            stmt.setString(3, vehicle.getCategory());
            stmt.setString(4, vehicle.getAvailability());
            stmt.setString(5,vehicle.getType());

            if (vehicle.getId() > 0) {
                stmt.setLong(5, vehicle.getId());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from vehicle where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Vehicle getVehicle(ResultSet rs) throws SQLException {
        Vehicle v = new Vehicle();
        v.setId(rs.getInt("id"));
        v.setName(rs.getString("nombre"));
        v.setPrice(rs.getDouble("precio"));
        v.setCategory(rs.getString("sku"));
        v.setAvailability(rs.getString("disponibilidad"));
        v.setType(rs.getString("tipo"));

        return v;
    }
}
