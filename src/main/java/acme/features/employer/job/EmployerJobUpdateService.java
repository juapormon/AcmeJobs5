
package acme.features.employer.job;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service

public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId() && job.getStatus() == JobStatus.DRAFT;

		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "title", "deadline", "salary", "moreInfo", "description");
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Job job = this.repository.findOneJobById(entity.getId());
		boolean isDraft = job.getStatus() == JobStatus.DRAFT;
		errors.state(request, isDraft, "status", "employer.job.form.error.is-published");

		if (isDraft && entity.getStatus() == JobStatus.PUBLISHED) {
			float sum = 0;
			for (Duty duty : this.repository.findManyDutiesByJobId(entity.getId())) {
				sum += duty.getWeekPercentage();
			}

			boolean correctWeeklyWorkload = sum == 100;
			errors.state(request, correctWeeklyWorkload, "description", "employer.job.form.error.incorrect-weekly-workload");
			if (!correctWeeklyWorkload) {
				entity.setStatus(JobStatus.DRAFT);
			}
		}

		job.setStatus(JobStatus.DRAFT);

		boolean salaryHasErrors = errors.hasErrors("salary");
		if (!salaryHasErrors) {
			boolean isEur = entity.getSalary().getCurrency().equals("EUR") || entity.getSalary().getCurrency().equals("€");
			errors.state(request, isEur, "salary", "employer.job.form.error.rejected-currency");
		}

		boolean deadlineHasErrors = errors.hasErrors("deadline");
		if (!deadlineHasErrors) {
			Calendar calendar = new GregorianCalendar();
			boolean isFuture = entity.getDeadline().after(calendar.getTime());
			errors.state(request, isFuture, "deadline", "employer.job.form.error.must-future");
		}

		boolean referenceHasErrors = errors.hasErrors("reference");
		if (!referenceHasErrors) {
			Job existing = this.repository.findOneJobByReference(entity.getReference());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "reference", "employer.job.form.error.reference-unique");
		}
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

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
