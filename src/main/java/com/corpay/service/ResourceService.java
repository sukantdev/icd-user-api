package com.corpay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.corpay.dao.cxxcow.Users;
import com.corpay.dao.dbo.LinkResource;
import com.corpay.dto.Menu;
import com.corpay.repository.LinkResourceRepository;
import com.corpay.repository.ResourceRepository;
import com.corpay.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

@ApplicationScoped
public class ResourceService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private ResourceRepository resourceRepository;

    @Inject
    private LinkResourceRepository linkResourceRepository;

    public List<Menu> findUserResources(String oktaUid) {
        Optional<Users> user = userRepository.findUser(oktaUid);
        if (user.isPresent()) {
            Config config = ConfigProvider.getConfig();
            String labelPrefix = "icd_header_";
            String iconSufix = "_icon";
            List<Menu> menuList = linkResourceRepository.findMenusForInternalUser().stream()
                    .map(resource -> {
                        Menu menu = new Menu();
                        String key = resource.getLabelKey();
                        menu.setLabel(config.getValue(labelPrefix + key, String.class));
                        menu.setIcon(config.getValue(labelPrefix + key + iconSufix, String.class));
                        menu.setUrl(resource.getLinkUriAddr());
                        menu.setWindow(resource.getBehaviorCd());
                        return menu;
                    })
                    .collect(Collectors.toList());
            return menuList;
        } else {
            return List.of();
        }
    }
}