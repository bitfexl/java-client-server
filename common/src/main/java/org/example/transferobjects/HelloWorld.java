package org.example.transferobjects;

import java.io.Serial;

public abstract class HelloWorld implements TransferObject {
    @Serial
    private static final long serialVersionUID = 0x633e31fe02b42316L;

    private String name;

    public HelloWorld(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
