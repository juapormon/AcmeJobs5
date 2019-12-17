
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.audit.Audit;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from Audit a where a.id = ?1")
	Audit findOneAuditById(int id);

	@Query("select a from Audit a where (a.job.id=?1 and (a.status=1 or a.auditor.id=?2))")
	Collection<Audit> findManyAuditByJobId(int idJob, int idAuditor);

	@Query("select count(a) from Audit a where a.id=?1 and (a.status=1 or a.auditor.id=?2)")
	int findPublishedOrOwnAudit(int idAudit, int idAuditor);

	@Query("select j.status from Job j where j.id = ?1")
	int findIsJobPublished(int jobId);

	@Query("select j from Job j where j.id = ?1")
	Job findJobById(int jobId);

	@Query("select ar from Auditor ar where ar.id = ?1")
	Auditor findAuditorById(int auditorId);

	void save(Audit entity);

}
