package com.sparta.delivery.converter;

import com.sparta.delivery.models.OrderFood;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class OrderFoodsConverter implements AttributeConverter<List<OrderFood>, String> {

    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<OrderFood> attribute) {
        for(OrderFood orderFoods : attribute){

        }

        return attribute.stream().map(String::valueOf).collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<OrderFood> convertToEntityAttribute(String dbData) {
        System.out.println(dbData);
        return null;
    }
}
