package roben.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import roben.entities.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

	List<Weather> findByCityName(String cityName);

	@Modifying
	@Transactional
	@Query("delete from data u where u.cityName = :cityName")
	void deleteUsersByFirstName(@Param("cityName") String cityName);
	

}
