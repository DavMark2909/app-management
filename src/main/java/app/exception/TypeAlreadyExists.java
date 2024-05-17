package app.exception;

public class TypeAlreadyExists extends Exception{
    public TypeAlreadyExists() {
        super("Tried to create the already existed type");
    }
}
