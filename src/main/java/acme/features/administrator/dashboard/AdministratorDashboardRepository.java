
package acme.features.administrator.dashboard;

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
}
