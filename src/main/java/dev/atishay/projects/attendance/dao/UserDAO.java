package dev.atishay.projects.attendance.dao;

import dev.atishay.projects.attendance.models.User;


import java.util.List;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Update;

import java.util.Optional;

public class UserDAO {
    private final Jdbi jdbi;

    public UserDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public long createUser(User user) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO users (name, age, emailId) VALUES (:name, :age, :emailId)")
                        .bind("name", user.getName())
                        .bind("age", user.getAge())
                        .bind("emailId", user.getEmailID())
                        .executeAndReturnGeneratedKeys("userId")
                        .mapTo(Long.class)
                        .one()
        );
    }

    public Optional<User> getUserById(long id) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM users WHERE userId = :id")
                        .bind("id", id)
                        .mapToBean(User.class)
                        .findOne()
        );
    }

    public boolean updateUser(long id, User updatedUser) {
        int rowsUpdated = jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE users SET name = :name, age = :age, emailId = :emailId WHERE userId = :id")
                        .bind("name", updatedUser.getName())
                        .bind("age", updatedUser.getAge())
                        .bind("emailId", updatedUser.getEmailID())
                        .bind("id", id)
                        .execute()
        );
        return rowsUpdated > 0;
    }

    public boolean deleteUser(long id) {
        int rowsDeleted = jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM users WHERE userId = :id")
                        .bind("id", id)
                        .execute()
        );
        return rowsDeleted > 0;
    }
}


