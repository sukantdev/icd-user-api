package com.corpay.repository;

import java.util.List;
import java.util.Map;

import com.corpay.dto.Menu;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResourceRepository extends AbstractBaseRepository<Menu, Integer> {
    
    public ResourceRepository() {
        super(Menu.class);
    }

    public List<Menu> findResourcesForUser(String userId) {
        try {
            return executeNamedQuery("Resource.findResourcesForUser", Map.of("userId", userId));
        } catch (Exception e) {
            return List.of();
        }
    }

    
}
