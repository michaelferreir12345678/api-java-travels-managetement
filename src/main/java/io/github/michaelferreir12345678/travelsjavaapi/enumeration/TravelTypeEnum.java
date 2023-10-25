package io.github.michaelferreir12345678.travelsjavaapi.enumeration;


public enum TravelTypeEnum {

    RETURN("RETURN"), ONE_WAY("ONE-WAY"), MULTI_CITY("MULTI-CITY");

    private String value;

    private TravelTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Method that returns the value in the Enum.
     *
     * @author Mariana Azevedo
     * @since 24/03/2020
     *
     * @param value
     * @return a TravelTypeEnum
     */
    public static TravelTypeEnum getEnum(String value) {

        for(TravelTypeEnum t : values()) {
            if(value.equals(t.getValue())) {
                return t;
            }
        }

        throw new RuntimeException("Type not found.");
    }

}