package com.corpay.dto;

import java.util.List;

public class Menu {
    private String label;    
    private String icon;
    private String url;
    private char window;
    private List<Menu> menuList = List.of();

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public char getWindow() {
        return window;
    }
    public void setWindow(char window) {
        this.window = window;
    }
    public List<Menu> getMenuList() {
        return menuList;
    }
}
