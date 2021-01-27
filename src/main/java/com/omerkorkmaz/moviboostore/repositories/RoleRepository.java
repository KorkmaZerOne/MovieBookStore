package com.omerkorkmaz.moviboostore.repositories;

import com.omerkorkmaz.moviboostore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>
{

	Role findByName(String name);

}
