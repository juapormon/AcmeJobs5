
package acme.features.auditor.audit;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audit.Audit;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service

public class AuditorAuditUpdateService implements AbstractUpdateService<Auditor, Audit> {

	@Autowired
	AuditorAuditRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;

		Principal principal;
		principal = request.getPrincipal();
		int id = request.getModel().getInteger("id");
		Audit audit = this.repository.findOneAuditById(id);
		Auditor auditor = audit.getAuditor();

		return principal.getActiveRoleId() == auditor.getId();
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

		request.unbind(entity, model, "creationMoment", "title", "status", "body", "job", "auditor");
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

		Audit audit;
		int auditId;

		auditId = request.getModel().getInteger("id");
		audit = this.repository.findOneAuditById(auditId);

		return audit;
	}

	@Override
	public void update(final Request<Audit> request, final Audit entity) {
		assert request != null;
		assert entity != null;
		Date creationMoment;

		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);

		this.repository.save(entity);
	}

}
