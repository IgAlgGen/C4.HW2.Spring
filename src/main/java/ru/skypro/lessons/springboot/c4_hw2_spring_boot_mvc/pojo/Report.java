package ru.skypro.lessons.springboot.c4_hw2_spring_boot_mvc.pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.generator.EventType;

import java.io.Serializable;
import java.util.Calendar;


@Entity
@Table(name = "reportc4")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;
    @Lob
    @Column()
    private String report;
    @CurrentTimestamp (event = EventType.INSERT, source = SourceType.DB)
    @Column()
    private Calendar createdTime;

}




