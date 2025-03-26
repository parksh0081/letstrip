package com.example.letstrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Festival {
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, 
          generator = "festival_SEQUENCE_GENERATOR")
   @SequenceGenerator(name="festival_SEQUENCE_GENERATOR", 
   sequenceName = "seq_festival", initialValue = 1, allocationSize = 1)
   
   private int seq;
   private String name;
   private int code;
   private String area;
   private String period;
   private String month;
   private String image1;
   private String content;
}