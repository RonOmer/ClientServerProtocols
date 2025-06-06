# ClientServer Protocols

This project demonstrates two simple server implementations in Java:

## Project Description

- **KnockKnockServer**:  
  A server that interacts with clients using the classic "Knock Knock" joke protocol. When a client connects, the server guides the client through a knock-knock joke conversation.

- **RuppinServer**:  
  A server designed for educational purposes at Ruppin Academic Center. It can handle basic client requests, such as user management or simple commands (the exact protocol depends on your implementation).

## Project Structure

- `src/CSprotocols/` — Contains all Java source files, including both servers and the main entry point.
- `Main.java` — Entry point for running either server.

## How to Compile

Open a terminal in the project root folder and run:

```
javac -d out src/CSprotocols/*.java
```

This will compile all Java files and place the `.class` files in the `out` directory, preserving the package structure.

## How to Run

To run the main server program, use:

```
java -cp out CSprotocols.Main 1
```
or
```
java -cp out CSprotocols.Main 2
```

- Use `1` to start the KnockKnockServer.
- Use `2` to start the RuppinServer.

## Notes

- Make sure all source files have the correct package declaration (`package CSprotocols;`).
- If you change the package name, recompile the project.
- Delete old `.class` files if you encounter package-related errors.