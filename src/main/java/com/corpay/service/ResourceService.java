package com.corpay.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.corpay.dao.cxxcow.Users;
import com.corpay.dto.Menu;
import com.corpay.repository.LinkResourceRepository;
import com.corpay.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ResourceService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private LinkResourceRepository linkResourceRepository;

    public Map<String, Menu> findUserResources(String oktaUid) {
        Optional<Users> user = userRepository.findUser(oktaUid);
        if (user.isPresent()) {
            Map<String, Menu> menuMap = linkResourceRepository.findMenusForInternalUser(user.get().getUserId()).stream()
                    .sorted(Comparator.comparingInt((com.corpay.dao.dbo.LinkResource linkResource) -> linkResource.getLevel0SortNbr())
                            .thenComparingInt(linkResource -> linkResource.getLevel1SortNbr()))
                    .collect(Collectors.groupingBy(linkResource -> linkResource.getLevel1Desc(),
                            LinkedHashMap::new,
                            Collectors.mapping(linkResource -> new Menu(linkResource.getLabelKey(), linkResource.getLinkUriAddr(), linkResource.getBehaviorCd().toString()), Collectors.toList())))
                    .entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> {
                        if (entry.getValue().size() == 1) {
                            return entry.getValue().get(0);
                        }
                        return new Menu(entry.getValue());
                    }, (existing, replacement) -> existing, LinkedHashMap::new));
            return menuMap;
        } else {
            return Collections.emptyMap();
        }
    }
}
