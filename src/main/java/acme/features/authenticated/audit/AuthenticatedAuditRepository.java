
package acme.features.authenticated.audit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.audit.Audit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditRepository extends AbstractRepository {

	@Query("select a from Audit a where a.id = ?1")
	Audit findOneById(int id);

	@Query("select a from Audit a where a.job.id = ?1 and a.status = acme.entities.audit.AuditStatus.PUBLISHED")
	Collection<Audit> findManyPublishedByJobId(int id);
}
