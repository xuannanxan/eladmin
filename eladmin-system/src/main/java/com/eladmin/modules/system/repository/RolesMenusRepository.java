package com.eladmin.modules.system.repository;


import com.eladmin.modules.system.domain.RolesMenus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolesMenusRepository extends JpaRepository<RolesMenus, String>, JpaSpecificationExecutor<RolesMenus> {

}
