
package acme.features.auditor.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorJobShowService implements AbstractShowService<Auditor, Job> {

	@Autowired
	private AuditorJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		int jobId = request.getModel().getInteger("id");
		Job job = this.repository.findOneJobById(jobId);
		boolean result = job.getIsActive();

		return result;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int jobId = request.getModel().getInteger("id");
		model.setAttribute("auditId", this.repository.findAuditIdByAuditorIdJobId(request.getPrincipal().getActiveRoleId(), jobId));
		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "description");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

}
