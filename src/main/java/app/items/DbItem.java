package app.items;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DbItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;
    private String type;
    private String unit;

    @Column(name="isdouble")
    private boolean isDouble;

    @Column(name="storagequantity")
    private double storageQuantity;
    @Column(name="shopquantity")
    private double shopQuantity;
    private double price;
}
