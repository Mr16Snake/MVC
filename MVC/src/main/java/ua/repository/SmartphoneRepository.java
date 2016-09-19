package ua.repository;

import java.util.List;

//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Smartphone;

public interface SmartphoneRepository extends JpaRepository<Smartphone, Integer>, JpaSpecificationExecutor<Smartphone>{
	@Query("SELECT s FROM Smartphone s LEFT JOIN FETCH s.brand")
	List<Smartphone> findAll();
	
	Smartphone findByModel(String model);
	
	@Query("SELECT s FROM Smartphone s LEFT JOIN FETCH s.brand LEFT JOIN FETCH s.budget LEFT JOIN FETCH s.screenSize WHERE s.id=:id")
	Smartphone findOneBrandBudgetScreenSizeInited(@Param("id")int id);
	
//	@Query(value = "SELECT s FROM Smartphone s LEFT JOIN FETCH s.brand",
//			countQuery="SELECT count(s.id) FROM Smartphone s LEFT JOIN s.brand b")
//	Page<Smartphone> findAll(Pageable pageable);
	
}
