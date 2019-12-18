
package acme.features.authenticated.becomeAuditor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Auditor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBecomeAuditorRepository extends AbstractRepository {

	@Query("select count(b) from BecomeAuditor b where b.userAccount.id = ?1 and b.status=acme.entities.becomeAuditor.BecomeAuditorStatus.PENDING")
	Integer findPendingById(int id);

	@Query("select a from Auditor a where a.userAccount.id = ?1")
	Auditor findOneAuditorByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);
}
