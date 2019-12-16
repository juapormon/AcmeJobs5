
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Integer						totalNumberOfAnnouncements;
	Integer						totalNumberOfCompanyRecords;
	Integer						totalNumberOfInvestorRecords;

	Double						maxRewardOfActiveRequests;
	Double						minRewardOfActiveRequests;
	Double						avgRewardOfActiveRequests;
	Double						standardDeviationRewardOfActiveRequests;

	Double						maxRewardOfActiveOffers;
	Double						minRewardOfActiveOffers;
	Double						avgRewardOfActiveOffers;
	Double						standardDeviationRewardOfActiveOffers;

	Double						avgJobsPerEmployer;
	Double						avgApplicationsPerEmployer;
	Double						avgApplicationsPerWorker;

	Object[]					investorsCommonSectors;
	Object[]					companiesCommonSectors;
	Object[]					companySectors;
	Object[]					investorSectors;

	Double						ratioOfPublishedJobs;
	Double						ratioOfDraftJobs;
	Double						ratioOfPendingApplications;
	Double						ratioOfAcceptedApplications;
	Double						ratioOfRejectedApplications;
}
