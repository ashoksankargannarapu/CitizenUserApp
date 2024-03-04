package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COUNTRY_MASTER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {

	@Id
	private Integer countryId;

	private String countryName;
}
