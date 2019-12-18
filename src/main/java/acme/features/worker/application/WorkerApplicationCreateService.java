
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Application;
import acme.entities.jobs.ApplicationStatus;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service

public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		int jobId = request.getModel().getInteger("jobId");
		Job job = this.repository.findOneJobById(jobId);
		boolean result = job.getIsActive();

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("jobId", request.getModel().getInteger("jobId"));
		request.unbind(entity, model, "reference", "moment", "status", "statement", "skills", "qualifications");
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result = new Application();
		result.setStatus(ApplicationStatus.PENDING);

		int jobId = request.getModel().getInteger("jobId");
		result.setJob(this.repository.findOneJobById(jobId));

		Worker worker = this.repository.findOneWorkerById(request.getPrincipal().getActiveRoleId());
		result.setWorker(worker);
		result.setSkills(worker.getSkills());
		result.setQualifications(worker.getQualifications());

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean referenceHasErrors = errors.hasErrors("reference");
		if (!referenceHasErrors) {
			Application existing = this.repository.findOneApplicationByReference(entity.getReference());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "reference", "worker.application.form.error.reference-unique");
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		entity.setMoment(new Date(System.currentTimeMillis() - 1));
		this.repository.save(entity);
	}
}
