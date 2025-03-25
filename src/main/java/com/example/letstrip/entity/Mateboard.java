package com.example.letstrip.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Mateboard {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATEBOARD_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "MATEBOARD_SEQUENCE_GENERATOR", sequenceName = "seq_mateboard", initialValue = 1, allocationSize = 1)
    private int seq;
    private String id;
    private String name;
    private String subject;
    private String content;
    private int hit;
    @Temporal(TemporalType.DATE)
    private Date logtime;
}
