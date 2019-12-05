
package acme.features.anonymous.company;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.company.Company;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCompanyRepository extends AbstractRepository {

	@Query("select c from Company c where c.id = ?1")
	Company findOneById(int id);

	@Query("select c from Company c")
	Collection<Company> findManyAll();

	@Query("select c from Company c where c.stars = 5")
	Collection<Company> findManyTop();
}
