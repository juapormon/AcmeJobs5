
package acme.features.auditor.audit;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audit.Audit;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditCreateService implements AbstractCreateService<Auditor, Audit> {

	@Autowired
	AuditorAuditRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;

		int jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findJobById(jobId);

		return job.getIsActive() && !this.repository.findExistsAuditByAuditorIdJobId(request.getPrincipal().getActiveRoleId(), jobId);
	}

	@Override
	public void bind(final Request<Audit> request, final Audit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Audit> request, final Audit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("jobId", request.getModel().getInteger("jobId"));
		request.unbind(entity, model, "title", "status", "body");
	}

	@Override
	public Audit instantiate(final Request<Audit> request) {
		assert request != null;

		Audit result = new Audit();

		Principal principal = request.getPrincipal();
		Auditor auditor = this.repository.findAuditorById(principal.getActiveRoleId());
		result.setAuditor(auditor);

		int jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findJobById(jobId);
		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<Audit> request, final Audit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Audit> request, final Audit entity) {
		assert request != null;
		assert entity != null;

		entity.setCreationMoment(new Date(System.currentTimeMillis() - 1));
		this.repository.save(entity);
	}

}
