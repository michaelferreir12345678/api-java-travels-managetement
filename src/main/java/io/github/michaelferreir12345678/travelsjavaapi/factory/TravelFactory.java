package io.github.michaelferreir12345678.travelsjavaapi.factory;

import io.github.michaelferreir12345678.travelsjavaapi.model.Travel;

/**
 * Interface that provides method for manipulate an object Travel.
 *
 * @author Mariana Azevedo
 * @since 08/09/2019
 */
public interface TravelFactory {

    Travel createTravel (String type);
}
