package pe.tato.spring_boot.crud.employees.commons;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Filters {

	private List<Filter> filters;
}
