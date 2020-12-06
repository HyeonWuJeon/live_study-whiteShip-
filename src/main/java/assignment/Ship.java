package assignment;

public enum Ship {
    SEAMAN("HyunWoo","whiteShip");

    private String name;
    private String value;


    Ship(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }


}
