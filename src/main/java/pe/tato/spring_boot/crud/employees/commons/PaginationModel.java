package pe.tato.spring_boot.crud.employees.commons;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationModel {

	private Integer pageNumber;
	private Integer rowsPerPage;
	// private Long totalRows;
	// private Long totalPges;
	private List<Filter> filters;
	private List<SortModel> sorts;

}
