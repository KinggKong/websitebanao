package com.huy.ecommercebe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "first_name")
    String firstName;
    @Column(name = "last-name")
    String lastName;
    @Column(name = "street-address")
    String streetAddress;
    @Column(name = "city")
    String city;

    @Column(name = "state")
    String state;

    @Column(name = "zip_code")
    String zipCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    User user;

    String mobile;
}
