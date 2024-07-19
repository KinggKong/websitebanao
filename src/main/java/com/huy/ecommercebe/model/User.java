package com.huy.ecommercebe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String firstName;

    String lastName;

    String password;

    String email;

    String role;

    String mobile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Address> address = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "payment_information", joinColumns = @JoinColumn(name = "user_id"))
    List<PaymentInformation> paymentInformation = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Rating> ratings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Review> reviews = new ArrayList<>();

    LocalDateTime createAt;
}
