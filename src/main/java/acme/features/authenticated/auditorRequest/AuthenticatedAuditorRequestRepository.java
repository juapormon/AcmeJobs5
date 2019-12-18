
package acme.features.authenticated.auditorRequest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorRequest.AuditorRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRequestRepository extends AbstractRepository {

	@Query("select ar from AuditorRequest ar where ar.id = ?1")
	AuditorRequest findOneById(int id);

	@Query("select count(ar)>0 from AuditorRequest ar where ar.userAccount.id = ?1 and ar.status = acme.entities.auditorRequest.AuditorRequestStatus.PENDING")
	Boolean findHasPendingByUserAccountId(int id);

	@Query("select count(a)>0 from Auditor a where a.userAccount.id = ?1")
	Boolean findIsAuditorByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);
}
