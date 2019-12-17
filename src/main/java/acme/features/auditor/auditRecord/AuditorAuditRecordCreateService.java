
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audit.Audit;
import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service

public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, Audit> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;

		return true;
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
		request.unbind(entity, model, "title", "status", "body", "job", "auditor");
	}

	@Override
	public Audit instantiate(final Request<Audit> request) {
		assert request != null;

		Audit result;
		Principal principal;
		int auditorId;
		int jobId;
		Auditor auditor;
		Job job;

		result = new Audit();

		principal = request.getPrincipal();
		auditorId = principal.getActiveRoleId();
		auditor = this.repository.findAuditorById(auditorId);

		jobId = request.getModel().getInteger("id");
		job = this.repository.findJobById(jobId);

		result.setAuditor(auditor);
		result.setJob(job);
		result.setStatus(JobStatus.DRAFT);
		Date creationMoment;

		creationMoment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(creationMoment);

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
