
package acme.features.administrator.jobsChart;

import org.springframework.data.jpa.repository.Query;

import acme.framework.repositories.AbstractRepository;

public interface AdministratorJobsChartRepository extends AbstractRepository {

	@Query("select 1.0 * count(j) / (select count(b) from Job b) from Job j where j.status = acme.entities.jobs.JobStatus.PUBLISHED")
	Double ratioOfPublishedJobs();

	@Query("select 1.0 * count(j) / (select count(b) from Job b) from Job j where j.status = acme.entities.jobs.JobStatus.DRAFT")
	Double ratioOfDraftJobs();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.PENDING")
	Double ratioOfPendingApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.ACCEPTED")
	Double ratioOfAcceptedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.jobs.ApplicationStatus.REJECTED")
	Double ratioOfRejectedApplications();
}
