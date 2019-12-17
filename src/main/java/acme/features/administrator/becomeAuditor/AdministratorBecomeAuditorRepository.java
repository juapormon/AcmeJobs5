
package acme.features.administrator.becomeAuditor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.becomeAuditor.BecomeAuditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorBecomeAuditorRepository extends AbstractRepository {

	@Query("select b from BecomeAuditor b where b.status = acme.entities.becomeAuditor.BecomeAuditorStatus.PENDING")
	Collection<BecomeAuditor> findManyAll();

	@Query("select b from BecomeAuditor b where b.id = ?1")
	BecomeAuditor findOneBecomeAuditorById(int id);
}
