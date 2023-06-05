# java-client-server
Java client server communication using tcp/ip sockets.

The project uses a 3 modules: client, common, server;

The common module defines a set of transfer objects (abstract classes) which all provide a execute() method and have some fields (arguments).

The server and client both provide an implementation for each transfer object, both with the exact same name, but only the server actually implements execute().

On the client the transfer object (implementation without code for execute()) gets serialized with all of its fields (arguments) and sent to the server.

The server deserializes the transfer object, but now using the implementation which provides actual code for execute(). The execute() method is called and can now access the deserialized field values transferred by the client.
