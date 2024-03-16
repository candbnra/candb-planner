package net.nrasoft.candb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.nrasoft.candb.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByEmail(String email);
}
