package com.example.letstrip.repository;

import com.example.letstrip.entity.Festival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface FestivalRepository extends JpaRepository<Festival, Integer> {

	@Query("SELECT f FROM Festival f WHERE "
	        + "(:areas IS NULL OR f.area LIKE CONCAT(:areas, '%')) "
	        + "AND (:months IS NULL OR f.month IN :months) "
	        + "AND (LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%'))) ")
	Page<Festival> findFestivalsByFilters(@Param("areas") Collection<String> areas,
	                                      @Param("months") Collection<String> months,
	                                      @Param("name") String name,
	                                      Pageable pageable);
}
