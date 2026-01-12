package com.example.crudOprt.Repository;

import com.example.crudOprt.Entity.ProjectUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ProjectUser,Long> {
    Optional<ProjectUser> findByEmail(String mail);
}
