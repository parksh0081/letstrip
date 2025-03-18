package com.example.letstrip.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Travelplan {
	@Id
    private String id;  // 사용자 ID (PK)
    @OneToMany(mappedBy = "travelplan", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference  // 무한참조 방지
    private List<Timeline> timelines;  // 여행 일정 리스트 (1:N 관계)
}
