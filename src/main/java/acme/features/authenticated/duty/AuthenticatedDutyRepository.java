
package acme.features.authenticated.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedDutyRepository extends AbstractRepository {

	@Query("select j from Job j where (select duty from Duty duty where duty.id = ?1) member of j.descriptor.duties")
	Job findOneJobByDutyId(int dutyId);

	@Query("select j.descriptor.duties from Job j where j.id = ?1")
	Collection<Duty> findManyByJobId(int jobId);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneDutyById(int id);
}
