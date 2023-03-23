package com.pss.PSS.model;

import com.pss.PSS.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", schema = "pss")
@Data //Генирирует геттеры и сеттеры и прочее
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "pass")
    private String pass;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    public UserEntity(String username, String email, String pass, Role role) {
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.role = role;
    }
}
