package app.items;

import lombok.*;

@Builder
@Getter
@Setter
public class Item {
    private String name;
    private long id;
    private TypeUnit type;
    private double price;
}
