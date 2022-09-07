package com.era.apicourse.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    private String uuid;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, length = 1024)
    private String description;

    @Column(nullable = false, updatable = false)
    private Date timestamp;

    @ToString.Exclude
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Topic> topics;

    @Column(nullable = false)
    private boolean visible;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Exam exam;

}
