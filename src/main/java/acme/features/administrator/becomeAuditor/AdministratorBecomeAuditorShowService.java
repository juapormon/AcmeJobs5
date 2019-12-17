
package acme.features.administrator.becomeAuditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.becomeAuditor.BecomeAuditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorBecomeAuditorShowService implements AbstractShowService<Administrator, BecomeAuditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBecomeAuditorRepository repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<BecomeAuditor> request) {
		assert request != null;

		return true;
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

}
