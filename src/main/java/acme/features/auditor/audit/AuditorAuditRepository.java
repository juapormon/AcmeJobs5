
package acme.features.auditor.audit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.audit.Audit;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRepository extends AbstractRepository {

	@Query("select a from Audit a where a.id = ?1")
	Audit findOneById(int id);

	@Query("select count(a)>0 from Audit a where a.auditor.id = ?1 and a.job.id = ?2")
	Boolean findExistsAuditByAuditorIdJobId(int auditorId, int jobId);

	@Query("select a from Audit a where a.auditor.id = ?1")
	Collection<Audit> findMany(int auditorId);

	@Query("select j.status from Job j where j.id = ?1")
	int findIsJobPublished(int jobId);

	@Query("select j from Job j where j.id = ?1")
	Job findJobById(int jobId);

	@Query("select ar from Auditor ar where ar.id = ?1")
	Auditor findAuditorById(int auditorId);

	void save(Audit entity);

}
