package pe.tato.spring_boot.crud.employees.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortModel {

	private String colName;
	private String sort;

}
