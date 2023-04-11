package com.pss.PSS.model;

import com.pss.PSS.model.enums.Role;
import com.pss.PSS.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Entity
@Table(name = "ads", schema = "pss")
@Data //Генирирует геттеры и сеттеры и прочее
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AdEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "kind")
    private String kind;

    @Column(name = "sex")
    private String sex;

    @Column(name = "photo")
    private String photo;

    @Column(name = "date")
    private String date;

    @Column(name = "place")
    private String place;

    @Column(name = "description")
    private String description;

    @Column(name = "user_description")
    private String user_description;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;


}
