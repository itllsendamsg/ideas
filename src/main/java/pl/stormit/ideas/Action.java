package pl.stormit.ideas;

import java.util.Objects;

public enum Action {
    LIST("list"), ADD("add");

    private final String value;

    Action(String value) {
        this.value = value;
    }

    public static Action of(String value) {
        for (Action action : values()) {
            if (Objects.equals(action.value, value)) {
                return action;
            }
        }

        throw new IllegalArgumentException("\n" +
                "\n" +
                "       ----------------------------------------------------------------------------------- \n" +
                "       *********************************************************************************** \n" +
                "       ----------------------------------------------------------------------------------- \n" +
                "                          ENHKX Med+) ) ) Drone------------analysing------------           \n" +
                "\n" + "                            [ ILLEGAL ARGUMENT EXCEPTION ]" +
                "\n" + "                    ENHKX Med+) ) ) Drone has analysed the provided input " +
                "\n" + "                               and detected the unknown action as: " + value);
    }
}
