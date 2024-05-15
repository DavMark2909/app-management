package app.items;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TypeUnit {
//    type name
    private String name;
//    all fileds in db are of type double, this parameter for future formatting purposes
    private boolean isDouble;
//    abbreviation
    private String shortName;
}
