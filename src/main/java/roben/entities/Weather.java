package roben.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "data")
@Table(name = "tempdata")
public class Weather {

	@Id
	@SequenceGenerator(name = "temp_seq", sequenceName = "temp_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "temp_seq")
	@Column(name = "weather")
	private long id;
	@Column(name = "cityname")
	private String cityName;
	@Column(name = "temperature")
	private Double temp;

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Weather(String cityName) {
		super();
		this.cityName = cityName;
	}

	public Weather() {
		super();
	}

	@Override
	public String toString() {
		return "Weather [id=" + id + ", cityName=" + cityName + ", temp=" + temp + "]";
	}

}
