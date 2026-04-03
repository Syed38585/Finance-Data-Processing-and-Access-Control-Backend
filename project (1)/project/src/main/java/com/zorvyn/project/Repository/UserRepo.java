package com.zorvyn.project.Repository;

import com.zorvyn.project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmailIgnoreCase(String email);

    User findByUserName(String username);

    List<User> findByIsDeletedFalse(Pageable pageable);
}
