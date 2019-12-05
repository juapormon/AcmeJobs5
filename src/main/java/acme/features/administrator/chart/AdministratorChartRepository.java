
package acme.features.administrator.chart;

import org.springframework.data.jpa.repository.Query;

import acme.framework.repositories.AbstractRepository;

public interface AdministratorChartRepository extends AbstractRepository {

	@Query("SELECT i.sector, count(i) FROM Investor i WHERE i.sector IN (SELECT c.sector FROM Company c) GROUP BY i.sector ORDER BY i.sector")
	Object[] findInvestorsCommonSectors();

	@Query("SELECT c.sector, count(c) FROM Company c WHERE c.sector IN (SELECT i.sector FROM Investor i) GROUP BY c.sector ORDER BY c.sector")
	Object[] findCompaniesCommonSectors();

	@Query("SELECT c.sector, count(c) FROM Company c WHERE c.sector NOT IN (SELECT i.sector FROM Investor i) GROUP BY c.sector ORDER BY c.sector")
	Object[] findCompanySectors();

	@Query("SELECT i.sector, count(i) FROM Investor i WHERE i.sector NOT IN (SELECT c.sector FROM Company c) GROUP BY i.sector ORDER BY i.sector")
	Object[] findInvestorSectors();

}
