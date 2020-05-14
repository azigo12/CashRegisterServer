package ba.unsa.etf.si.local_server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@javax.persistence.Table(name = "items")
public class Item {
    @Id
    private Long id;

    private String name;
    private String unit;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ItemType itemType;

    public Item(String name, String unit, ItemType itemType) {
        this.name = name;
        this.unit = unit;
        this.itemType = itemType;
    }
}
