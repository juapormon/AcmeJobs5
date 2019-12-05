
package acme.features.administrator.jobsChart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.JobsChart;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorJobsChartShowService implements AbstractShowService<Administrator, JobsChart> {

	@Autowired
	AdministratorJobsChartRepository repository;


	@Override
	public boolean authorise(final Request<JobsChart> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<JobsChart> request, final JobsChart entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "ratioOfPublishedJobs", "ratioOfDraftJobs", "ratioOfPendingApplications", "ratioOfAcceptedApplications", "ratioOfRejectedApplications");
	}

	@Override
	public JobsChart findOne(final Request<JobsChart> request) {
		assert request != null;

		JobsChart result = new JobsChart();

		result.setRatioOfPublishedJobs(this.repository.ratioOfPublishedJobs());
		result.setRatioOfDraftJobs(this.repository.ratioOfDraftJobs());
		result.setRatioOfPendingApplications(this.repository.ratioOfPendingApplications());
		result.setRatioOfAcceptedApplications(this.repository.ratioOfAcceptedApplications());
		result.setRatioOfRejectedApplications(this.repository.ratioOfRejectedApplications());

		return result;
	}

}
