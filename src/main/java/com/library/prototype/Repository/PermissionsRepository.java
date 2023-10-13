package com.library.prototype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.prototype.Entity.Permissions;

public interface PermissionsRepository extends JpaRepository<Permissions, Long>{



    @Query(value = "SELECT p from Permissions p WHERE p.user = :uEmail AND p.serviceName = :service AND p.methodName = :method")
    List<Permissions> isMethodPresent(String uEmail, String service, String method);
    
}
