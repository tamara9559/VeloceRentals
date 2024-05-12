package co.edu.cue.velocerentals.repositories;

import co.edu.cue.velocerentals.model.User;

import java.sql.SQLException;

public interface UserRepository extends Repository<User>{
    User byName(String username) throws SQLException;
}
