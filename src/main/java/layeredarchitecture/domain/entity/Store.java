package layeredarchitecture.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STORE")
public class Store {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "store"
    )
    private List<StoreFruitList> storeFruits;

}
