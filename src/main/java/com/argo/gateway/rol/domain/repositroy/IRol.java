package com.argo.gateway.rol.domain.repositroy;

import com.commons.user.models.entity.rol.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRol  extends JpaRepository<Rol,Integer> {
}
