package com.app.persistence.repository;

import com.app.persistence.entity.PermissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.security.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<PermissionEntity, Long> {
}
