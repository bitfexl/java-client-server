package org.example.transferobjects;

import org.example.NotImplementedByClientException;

public class HelloWorldImpl extends HelloWorld {
    public HelloWorldImpl(String name) {
        super(name);
    }

    @Override
    public void execute() throws NotImplementedByClientException {
        throw new NotImplementedByClientException();
    }
}
