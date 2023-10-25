package io.github.michaelferreir12345678.travelsjavaapi.factory.impl;


import io.github.michaelferreir12345678.travelsjavaapi.enumeration.TravelTypeEnum;
import io.github.michaelferreir12345678.travelsjavaapi.factory.TravelFactory;
import io.github.michaelferreir12345678.travelsjavaapi.model.Travel;

public class TravelFactoryImpl implements TravelFactory {

    @Override
    public Travel createTravel (String type) {

        if (TravelTypeEnum.ONE_WAY.getValue().equals(type)) {
            return new Travel(TravelTypeEnum.ONE_WAY);
        } else if (TravelTypeEnum.MULTI_CITY.getValue().equals(type)) {
            return new Travel(TravelTypeEnum.MULTI_CITY);
        }

        return new Travel(TravelTypeEnum.RETURN);
    }

}
