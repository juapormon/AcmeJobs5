
package acme.features.administrator.dashboard;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("SELECT count(a.id) FROM Announcement a")
	Integer findTotalNumberOfAnnouncements();

	@Query("SELECT count(a.id) FROM Company a")
	Integer findTotalNumberOfCompanyRecords();

	@Query("SELECT count(a.id) FROM Investor a")
	Integer findTotalNumberOfInvestorRecords();

	@Query("SELECT max(a.reward.amount) FROM Req a where a.deadline > current_date()")
	Object[] findMaxRewardOfActiveRequests();

	@Query("SELECT min(a.reward.amount) FROM Req a where a.deadline > current_date()")
	Object[] findMinRewardOfActiveRequests();

	@Query("SELECT avg(a.reward.amount) FROM Req a where a.deadline > current_date()")
	Object[] findAvgRewardOfActiveRequests();

	@Query("SELECT stddev(a.reward.amount) FROM Req a where a.deadline > current_date()")
	Object[] findStandardDeviationRewardOfActiveRequests();

	@Query("SELECT max(a.maxMoney.amount) FROM Offer a where a.deadline > current_date()")
	Object[] findMaxRewardOfActiveOffers();

	@Query("SELECT min(a.maxMoney.amount) FROM Offer a where a.deadline > current_date()")
	Object[] findMinRewardOfActiveOffers();

	@Query("SELECT avg(a.maxMoney.amount) FROM Offer a where a.deadline > current_date()")
	Object[] findAvgRewardOfActiveOffers();

	@Query("SELECT stddev(a.maxMoney.amount) FROM Offer a where a.deadline > current_date()")
	Object[] findStandardDeviationRewardOfActiveOffers();

	@Query("SELECT avg(SELECT COUNT(j) FROM Job j WHERE j.employer.id = e.id) FROM Employer e")
	Object[] findAvgJobsPerEmployer();

	@Query("SELECT avg(SELECT COUNT(a) FROM Application a WHERE a.job.employer.id = e.id) FROM Employer e")
	Object[] findAvgApplicationsPerEmployer();

	@Query("SELECT avg(SELECT COUNT(a) FROM Application a WHERE a.worker.id = w.id) FROM Worker w")
	Object[] findAvgApplicationsPerWorker();

	@Query("SELECT i.sector, count(i) FROM Investor i WHERE i.sector IN (SELECT c.sector FROM Company c) GROUP BY i.sector ORDER BY i.sector")
	Object[] findInvestorsCommonSectors();

	@Query("SELECT c.sector, count(c) FROM Company c WHERE c.sector IN (SELECT i.sector FROM Investor i) GROUP BY c.sector ORDER BY c.sector")
	Object[] findCompaniesCommonSectors();

	@Query("SELECT c.sector, count(c) FROM Company c WHERE c.sector NOT IN (SELECT i.sector FROM Investor i) GROUP BY c.sector ORDER BY c.sector")
	Object[] findCompanySectors();

	@Query("SELECT i.sector, count(i) FROM Investor i WHERE i.sector NOT IN (SELECT c.sector FROM Company c) GROUP BY i.sector ORDER BY i.sector")
	Object[] findInvestorSectors();

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

	@Query("select a.moment, count(a), a.status from Application a where a.moment >= ?1 group by day(a.moment), month(a.moment), year(a.moment) having a.status = acme.entities.jobs.ApplicationStatus.PENDING")
	Object[] findPendingApplicationsPerDay(Date after);

	@Query("select a.moment, count(a), a.status from Application a where a.moment >= ?1 group by day(a.moment), month(a.moment), year(a.moment) having a.status = acme.entities.jobs.ApplicationStatus.ACCEPTED")
	Object[] findAcceptedApplicationsPerDay(Date after);

	@Query("select a.moment, count(a), a.status from Application a where a.moment >= ?1 group by day(a.moment), month(a.moment), year(a.moment) having a.status = acme.entities.jobs.ApplicationStatus.REJECTED")
	Object[] findRejectedApplicationsPerDay(Date after);
}
