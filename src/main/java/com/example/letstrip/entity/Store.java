package com.example.letstrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_SEQUENCE_GENERATOR")
	@SequenceGenerator(name="STORE_SEQUENCE_GENERATOR", sequenceName = "seq_store", initialValue = 1, allocationSize = 1)
    private int seq;
    private String id;
    private String placeid;
    
    @ManyToOne
    @JoinColumn(name="id", insertable = false, updatable = false)
    private Place place;
    //insertable = false , updatable = false
    //	=> Store 엔티티를 데이터베이스에 저장/update 할 때 placeid 값을 자동으로 삽입하지 않도록
    
    public void addPlace(Place place) {
    	this.place=place;
    }
    
}
