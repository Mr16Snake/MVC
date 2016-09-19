package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand>{

	@Query("SELECT DISTINCT b FROM Brand b LEFT JOIN FETCH b.smartphones")
	List<Brand> findWithSmartphones();
	
	Brand findByName(String name);
	
	default void delete(String name){
		delete(findByName(name));
	}
}
