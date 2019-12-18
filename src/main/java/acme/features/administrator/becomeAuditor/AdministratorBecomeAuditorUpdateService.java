
package acme.features.administrator.becomeAuditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.becomeAuditor.BecomeAuditor;
import acme.entities.becomeAuditor.BecomeAuditorStatus;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorBecomeAuditorUpdateService implements AbstractUpdateService<Administrator, BecomeAuditor> {

	@Autowired
	AdministratorBecomeAuditorRepository repository;


	@Override
	public boolean authorise(final Request<BecomeAuditor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<BecomeAuditor> request, final BecomeAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<BecomeAuditor> request, final BecomeAuditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsabilityStatement", "status");

	}

	@Override
	public BecomeAuditor findOne(final Request<BecomeAuditor> request) {
		assert request != null;

		BecomeAuditor result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneBecomeAuditorById(id);

		return result;
	}

	@Override
	public void validate(final Request<BecomeAuditor> request, final BecomeAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<BecomeAuditor> request, final BecomeAuditor entity) {
		assert request != null;
		assert entity != null;

		if (entity.getStatus().equals(BecomeAuditorStatus.ACCEPTED)) {
			Auditor auditor = new Auditor();
			auditor.setFirm(entity.getFirm());
			auditor.setResponsabilityStatement(entity.getResponsabilityStatement());
			auditor.setUserAccount(entity.getUserAccount());
			this.repository.save(auditor);
		}

		this.repository.save(entity);
	}

}
