package com.pss.PSS.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users_description", schema = "pss")
@Data //Генирирует геттеры и сеттеры и прочее
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDescriptionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone_number")
    private String telephone_number;

}
