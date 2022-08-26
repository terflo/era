package com.era.courseapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
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

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topic> topics;

    @Column(nullable = false)
    private boolean visible;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Exam exam;

}
