package com.corpay.dto;

import java.util.List;

public class Menu {
    private String key;
    private String url;
    private String window;
    private List<Menu> menuList;

    public Menu(String key, String url, String window) {
        this.key = key;
        this.url = url;
        this.window = window;
        this.menuList = List.of();
    }

    public Menu(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public String getKey() {
        return key;
    }
    public String getUrl() {
        return url;
    }
    public String getWindow() {
        return window;
    }
    public List<Menu> getMenuList() {
        return menuList;
    }
}
