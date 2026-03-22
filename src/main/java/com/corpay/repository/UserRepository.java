package com.corpay.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.corpay.dao.cxxcow.Users;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository extends AbstractBaseRepository<Users, Integer> {
    
    public UserRepository() {
        super(Users.class);
    }

    public Optional<Users> findUser(String oktaUid) {
        try {
            List<Users> result = executeNamedQuery("Users.findByIamId", Map.of("iamId", oktaUid));
            return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
