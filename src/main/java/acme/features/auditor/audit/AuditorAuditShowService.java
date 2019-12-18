
package acme.features.auditor.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audit.Audit;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service

public class AuditorAuditShowService implements AbstractShowService<Auditor, Audit> {

	@Autowired
	private AuditorAuditRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;

		Principal principal = request.getPrincipal();
		int id = request.getModel().getInteger("id");
		Audit audit = this.repository.findOneById(id);
		boolean result = audit.getAuditor().getId() == principal.getActiveRoleId();

		return result;
	}

	@Override
	public void unbind(final Request<Audit> request, final Audit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "status", "body", "job.id");
	}

	@Override
	public Audit findOne(final Request<Audit> request) {
		assert request != null;

		Audit result;
		int id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);
		return result;
	}

}
