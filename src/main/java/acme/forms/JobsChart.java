
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobsChart implements Serializable {

	private static final long	serialVersionUID	= 1L;

	Double						ratioOfPublishedJobs;
	Double						ratioOfDraftJobs;
	Double						ratioOfPendingApplications;
	Double						ratioOfAcceptedApplications;
	Double						ratioOfRejectedApplications;

}
