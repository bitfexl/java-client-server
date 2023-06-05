package org.example.transferobjects;

import org.example.NotImplementedByClientException;

import java.io.Serializable;

/**
 * A transfer object should be an abstract class,
 * which does not implement execute() but rather declare
 * some (serializable) fields which act as the parameters.
 * The fields will be serialized and transferred to the server.
 * The server should provide an implementation for the
 * abstract class which implements execute() called
 * "ABSTRACTCLASSNAME"Impl. The client should do the
 * same, but instead of executing, it should throw the
 * NotImplementedByClientException exception (in most cases).
 * The name needs to be the same on the client and server
 * in order for serialization to work.
 */
public interface TransferObject extends Serializable {
    /**
     * Execute the method behind this transfer object
     * with the given parameters (fields).
     * @throws NotImplementedByClientException Should be thrown by the client to
     * indicate that this method will only work on the server.
     */
    void execute() throws NotImplementedByClientException;
}
