package com.apap.tugasAkhir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugasAkhir.model.UserRoleModel;

@Repository
public interface UserRoleDb extends JpaRepository<UserRoleModel, Long>{
	UserRoleModel findByUsername(String username);

}
