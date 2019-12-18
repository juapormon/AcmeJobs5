
package acme.features.auditor.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audit.Audit;
import acme.entities.audit.AuditStatus;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service

public class AuditorAuditDeleteService implements AbstractDeleteService<Auditor, Audit> {

	@Autowired
	AuditorAuditRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int id = request.getModel().getInteger("id");
		Audit audit = this.repository.findOneById(id);
		boolean result = audit.getAuditor().getId() == principal.getActiveRoleId() && audit.getStatus() == AuditStatus.DRAFT;

		return result;
	}

	@Override
	public void bind(final Request<Audit> request, final Audit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Audit> request, final Audit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "status", "body", "job.id");
	}

	@Override
	public void validate(final Request<Audit> request, final Audit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public Audit findOne(final Request<Audit> request) {
		assert request != null;

		Audit result;
		int id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void delete(final Request<Audit> request, final Audit entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
