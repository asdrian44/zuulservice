package com.argo.gateway.User.domain.repositroy;

import com.argo.gateway.User.domain.User;
import com.argo.gateway.User.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDetails  extends JpaRepository<UserDetails,User> {
}
