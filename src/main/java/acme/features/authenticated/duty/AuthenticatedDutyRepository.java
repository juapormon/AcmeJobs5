
package acme.features.authenticated.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDutyRepository extends AbstractRepository {

	@Query("select d.job from Duty d where d.id = ?1")
	Job findOneJobByDutyId(int dutyId);

	@Query("select d from Duty d where d.job.id = ?1")
	Collection<Duty> findManyByJobId(int jobId);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneDutyById(int id);
}
