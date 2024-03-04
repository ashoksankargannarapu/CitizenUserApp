package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STATE_MASTER")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class State {

	@Id
	private Integer stateId;

	private String stateName;

	private Integer countryId;

	

}
