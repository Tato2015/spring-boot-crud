package pe.tato.spring_boot.crud.employees.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import pe.tato.spring_boot.crud.employees.commons.Filter;
import pe.tato.spring_boot.crud.employees.commons.IPaginationCommons;
import pe.tato.spring_boot.crud.employees.commons.PaginationModel;
import pe.tato.spring_boot.crud.employees.commons.SortModel;
import pe.tato.spring_boot.crud.employees.dto.EmployeeResponseDTO;
import pe.tato.spring_boot.crud.employees.utils.DateUtil;

@Service
@RequiredArgsConstructor
public class EmployeePaginationServiceImpl implements IPaginationCommons<EmployeeResponseDTO> {

	private final EntityManager entityManager;

	@Override
	public Page<EmployeeResponseDTO> pagination(PaginationModel pagination) {
		try {

			String sqlCount = "SELECT count(e) " + getFrom().toString()
					+ getFilters(pagination.getFilters()).toString();
			String sqlSelect = getSelect().toString() + getFrom().toString()
					+ getFilters(pagination.getFilters()).toString() + getOrder(pagination.getSorts());

			Query queryCount = entityManager.createQuery(sqlCount);
			Query querySelect = entityManager.createQuery(sqlSelect);

			this.setParams(pagination.getFilters(), queryCount);
			this.setParams(pagination.getFilters(), querySelect);

			Long total = (long) queryCount.getSingleResult();

			querySelect.setFirstResult((pagination.getPageNumber()) * pagination.getRowsPerPage());
			querySelect.setMaxResults(pagination.getRowsPerPage());

			@SuppressWarnings("unchecked")
			List<EmployeeResponseDTO> lista = querySelect.getResultList();

			PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

			return new PageImpl<>(lista, pageable, total);
		} catch (RuntimeException e) {
			throw new ServiceException("error when generating the pagination " + e.getMessage());
		}
	}

	@Override
	public StringBuilder getSelect() {
		return new StringBuilder(" SELECT new pe.tato.spring_boot.crud.employees.dto.EmployeeResponseDTO("
				+ "e.employeeId," + "new pe.tato.spring_boot.crud.employees.dto.ContactResponseDTO(" + "c.contactId,"
				+ "c.name," + "c.lastName," + "c.phone," + "c.birthDate,"
				+ "new pe.tato.spring_boot.crud.employees.dto.MaritalStatusResponseDTO(" + "m.maritalStatusId,"
				+ "m.code," + "m.description" + ")," + "new pe.tato.spring_boot.crud.employees.dto.GenderResponseDTO("
				+ "g.genderId," + "g.code," + "g.description" + "),"
				+ "new pe.tato.spring_boot.crud.employees.dto.IdentificationDocumentResponseDTO(" + "i.documentId,"
				+ "i.code," + "i.description" + ")," + "c.document," + "c.createdAt," + "c.updatedAt,"
				+ "c.registrationStatus," + "c.idUser" + ")," + "e.managerId," + "e.hireDate," + "e.createdAt,"
				+ "e.updatedAt," + "e.registrationStatus," + "e.idUser" + ") ");
	}

	@Override
	public StringBuilder getFrom() {
		return new StringBuilder("FROM Employee e " + "LEFT JOIN e.contact c " + "LEFT JOIN c.maritalStatus m "
				+ "LEFT JOIN c.gender g " + "LEFT JOIN c.identificationDocument i ");
	}

	@Override
	public StringBuilder getFilters(List<Filter> filters) {
		StringBuilder sql = new StringBuilder("where 1=1 ");

		for (Filter filtro : filters) {
			if (filtro.getField().equals("employeeId")) {
				sql.append(" AND e.employeeId = :employeeId");
			}

			if (filtro.getField().equals("hireDate")) {
				sql.append(" AND DATE(e.hireDate) = :hireDate");
			}

			if (filtro.getField().equals("contactId")) {
				sql.append(" AND c.contactId = :contactId");
			}

			if (filtro.getField().equals("managerId")) {
				sql.append(" AND e.managerId = :managerId");
			}

			if (filtro.getField().equals("birthDate")) {
				sql.append(" AND DATE(c.birthDate) = :birthDate");
			}

			if (filtro.getField().equals("document")) {
				sql.append(" AND UPPER(c.document) LIKE UPPER(:document) ");
			}

			if (filtro.getField().equals("name")) {
				sql.append(" AND UPPER(c.name) LIKE UPPER(:name) ");
			}

			if (filtro.getField().equals("lastName")) {
				sql.append(" AND UPPER(c.lastName) LIKE UPPER(:lastName) ");
			}

			if (filtro.getField().equals("phone")) {
				sql.append(" AND UPPER(c.phone) LIKE UPPER(:phone) ");
			}

		}

		return sql;
	}

	@Override
	public Query setParams(List<Filter> filters, Query query) {
		for (Filter filtro : filters) {
			if (filtro.getField().equals("employeeId")) {
				query.setParameter("employeeId", filtro.getValue());
			}

			if (filtro.getField().equals("hireDate")) {
				LocalDate hireDate = DateUtil.convertStringToLocalDate(filtro.getValue().trim());
				query.setParameter("hireDate", hireDate);
			}

			if (filtro.getField().equals("contactId")) {
				query.setParameter("contactId", filtro.getValue());
			}

			if (filtro.getField().equals("managerId")) {
				query.setParameter("managerId", filtro.getValue());
			}

			if (filtro.getField().equals("birthDate")) {
				LocalDate birthDate = DateUtil.convertStringToLocalDate(filtro.getValue().trim());
				query.setParameter("birthDate", birthDate);
			}

			if (filtro.getField().equals("document")) {
				query.setParameter("document", "%" + filtro.getValue() + "%");
			}

			if (filtro.getField().equals("name")) {
				query.setParameter("name", "%" + filtro.getValue() + "%");
			}

			if (filtro.getField().equals("lastName")) {
				query.setParameter("lastName", "%" + filtro.getValue() + "%");
			}

			if (filtro.getField().equals("phone")) {
				query.setParameter("phone", "%" + filtro.getValue() + "%");
			}

		}
		return query;
	}

	@Override
	public StringBuilder getOrder(List<SortModel> sorts) {
		boolean flagMore = false;
		StringBuilder sql = new StringBuilder("");
		if (!sorts.isEmpty()) {
			sql.append(" ORDER BY ");

			for (SortModel sort : sorts) {
				if (sort.getColName().equals("employeeId")) {
					if (flagMore)
						sql.append(", ");

					sql.append(" employeeId " + sort.getSort());
					flagMore = true;
				}

				if (sort.getColName().equals("hireDate")) {
					if (flagMore)
						sql.append(", ");
					sql.append(" hireDate " + sort.getSort());
					flagMore = true;
				}

				if (sort.getColName().equals("contactId")) {
					if (flagMore)
						sql.append(", ");

					sql.append(" contactId " + sort.getSort());
					flagMore = true;
				}

				if (sort.getColName().equals("managerId")) {
					if (flagMore)
						sql.append(", ");

					sql.append(" managerId " + sort.getSort());
					flagMore = true;
				}

				if (sort.getColName().equals("birthDate")) {
					if (flagMore)
						sql.append(", ");
					sql.append(" birthDate " + sort.getSort());
					flagMore = true;
				}

				if (sort.getColName().equals("document")) {
					if (flagMore)
						sql.append(", ");

					sql.append(" document " + sort.getSort());
					flagMore = true;
				}

				if (sort.getColName().equals("name")) {
					if (flagMore)
						sql.append(", ");

					sql.append(" name " + sort.getSort());
					flagMore = true;
				}

				if (sort.getColName().equals("lastName")) {
					if (flagMore)
						sql.append(", ");

					sql.append(" lastName " + sort.getSort());
					flagMore = true;
				}

				if (sort.getColName().equals("phone")) {
					if (flagMore)
						sql.append(", ");

					sql.append(" phone " + sort.getSort());
					flagMore = true;
				}

			}
		}
		return sql;
	}

}
