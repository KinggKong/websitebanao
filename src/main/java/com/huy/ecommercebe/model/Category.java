package com.huy.ecommercebe.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @Size(max = 50)
    String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_category_id")
    Category parentCatgory;

    int level;

}
