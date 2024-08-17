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
@Table( name = "identification_document")
public class IdentificationDocument {
	
	@Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
	@SequenceGenerator(name = "sequenceDocument", sequenceName = "document_seq", allocationSize = 1)
    @Column( name = "document_id" )
    private Long documentId;
	
	@Column( name = "code" )
	private String code;
	
	@Column( name = "description" )
	private String description;

}
