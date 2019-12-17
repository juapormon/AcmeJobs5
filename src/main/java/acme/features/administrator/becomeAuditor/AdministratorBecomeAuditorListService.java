
package acme.features.administrator.becomeAuditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.becomeAuditor.BecomeAuditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorBecomeAuditorListService implements AbstractListService<Administrator, BecomeAuditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorBecomeAuditorRepository repository;


	@Override
	public boolean authorise(final Request<BecomeAuditor> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<BecomeAuditor> findMany(final Request<BecomeAuditor> request) {
		assert request != null;
		Collection<BecomeAuditor> result;
		result = this.repository.findManyAll();
		return result;
	}

	@Override
	public void unbind(final Request<BecomeAuditor> request, final BecomeAuditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsabilityStatement", "status");

	}

}
