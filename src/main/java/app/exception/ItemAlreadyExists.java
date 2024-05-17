package app.exception;

import app.items.DbItem;

public class ItemAlreadyExists extends Exception {

    public ItemAlreadyExists(String message) {
        super("Item " + message + " already exists");
    }
}
