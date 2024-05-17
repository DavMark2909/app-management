package app.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class TypeUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    type name
    private String name;
//    all fileds in db are of type double, this parameter for future formatting purposes
    @Column(name = "isdouble")
    private boolean isDouble;
//    abbreviation
    @Column(name = "shortname")
    private String shortName;
}
