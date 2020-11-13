package com.argo.gateway.User.Roles.domain;

import com.argo.gateway.User.Roles.domain.en.roles_enum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoles  extends JpaRepository<Roles,Integer> {

    Optional<Roles> findByRol(roles_enum enumr);
}
