package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.entity.ScreenSize;

public interface ScreenSizeRepository extends JpaRepository<ScreenSize, Integer>, JpaSpecificationExecutor<ScreenSize>{

	ScreenSize findByValue(String value);
}
