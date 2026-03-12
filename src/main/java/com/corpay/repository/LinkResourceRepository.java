package com.corpay.repository;

import java.util.List;

import com.corpay.dao.dbo.LinkResource;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LinkResourceRepository extends AbstractBaseRepository<LinkResource, Integer> {

    public LinkResourceRepository() {
        super(LinkResource.class);
    }

    public List<LinkResource> findMenusForInternalUser() {
        try {
            return executeNamedQuery("LinkResource.findMenusForInternalUser");
        } catch (Exception e) {
            return List.of();
        }
    }

    public List<LinkResource> findMenusForExternalUser(String userId) {
        try {
            return executeNamedQuery("LinkResource.findMenusForExternalUser", userId);
        } catch (Exception e) {
            return List.of();
        }
    }

    public List<LinkResource> findMenusForBrandedExternalUser(String userId, String brand) {
        try {
            return executeNamedQuery("LinkResource.findMenusForBrandedExternalUser", userId, brand);
        } catch (Exception e) {
            return List.of();
        }
    }
}
