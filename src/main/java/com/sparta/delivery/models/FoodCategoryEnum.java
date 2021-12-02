package com.sparta.delivery.models;

public enum FoodCategoryEnum {
    CHICKEN("치킨"),
    BURGER("버거"),
    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    ASIAN("아시안"),
    WESTERN("양식"),
    DESSERT("카페/디저트");

    private final String category;

    FoodCategoryEnum(String category) {
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }
}
