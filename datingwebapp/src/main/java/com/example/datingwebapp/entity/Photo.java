package com.example.datingwebapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tbl_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;


    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;

// keep it simple for now
@OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL, mappedBy = "image")
private Profile profile;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = true)
    private Date createDate;



}
