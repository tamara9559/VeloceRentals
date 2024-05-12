package co.edu.cue.velocerentals.repositories;

import co.edu.cue.velocerentals.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryImpl implements UserRepository {

    private Connection conn;

    public UsuarioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<User> toList() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from usuarios")) {
            while (rs.next()) {
                User u = getUser(rs);
                users.add(u);
            }
        }
        return users;
    }

    @Override
    public User byId(Long id) throws SQLException {
        User user = null;
        try ( PreparedStatement stmt = conn.prepareStatement("select * from usuarios where id=?")) {
            stmt.setLong(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = getUser(rs);
                }
            }
        }
        return user;
    }

    @Override
    public void save(User user) throws SQLException {
        String sql;
        if (user.getId() != null && user.getId() > 0) {
            sql = "update usuarios set username=?, password=?, email=? where id=?";
        } else {
            sql = "insert into usuarios (username, password, email) values (?,?,?)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());

            if (user.getId() != null && user.getId() > 0) {
                stmt.setLong(4, user.getId());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from usuarios where id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public User byName(String user) throws SQLException {
        User user1 = null;
        try ( PreparedStatement stmt = conn.prepareStatement("select * from usuarios where username=?")) {
            stmt.setString(1, user);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user1 = getUser(rs);
                }
            }
        }
        return user1;
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setName(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        return user;
    }

}
