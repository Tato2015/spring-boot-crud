package pe.tato.spring_boot.crud.employees.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table( name = "marital_status")
public class MaritalStatus {

	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	@SequenceGenerator(name = "sequenceMaritalStatus", sequenceName = "marital_status_seq", allocationSize = 1)
    @Column( name = "marital_status_id" )
    private Long maritalStatusId;
	
	@Column( name = "code" )
	private String code;
	
	@Column( name = "description" )
	private String description;
	
}
