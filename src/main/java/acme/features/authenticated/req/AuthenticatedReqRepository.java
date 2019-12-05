
package acme.features.authenticated.req;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.reqs.Req;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedReqRepository extends AbstractRepository {

	@Query("select r from Req r where r.id = ?1")
	Req findOneById(int id);

	@Query("select r from Req r where r.deadline > current_date()")
	Collection<Req> findManyAll();

}
