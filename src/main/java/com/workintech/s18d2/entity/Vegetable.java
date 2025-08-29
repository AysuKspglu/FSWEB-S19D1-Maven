package com.workintech.s18d2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vegetables", schema = "fsweb")
public class Vegetable {
    @Id
    private Long id;

    @NotBlank
    private String name;

    @Min(0)
    private Double price;

    // ✅ boolean + isim: grownOnTree  →  Lombok: setGrownOnTree(..) / isGrownOnTree()
    private boolean grownOnTree;
}
