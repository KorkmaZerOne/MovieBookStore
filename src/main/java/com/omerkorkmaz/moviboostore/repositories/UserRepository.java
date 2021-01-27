package com.omerkorkmaz.moviboostore.repositories;

import com.omerkorkmaz.moviboostore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {

    User findUserByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
