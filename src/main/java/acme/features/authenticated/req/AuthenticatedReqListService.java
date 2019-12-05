
package acme.features.authenticated.req;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.reqs.Req;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedReqListService implements AbstractListService<Authenticated, Req> {

	// Internal State ----------------------------------------------------

	@Autowired
	AuthenticatedReqRepository repository;


	// AbstractListService<Authenticated, Request> Interface -------------

	@Override
	public boolean authorise(final Request<Req> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Req> request, final Req entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "deadline", "text", "reward", "ticker");
	}

	@Override
	public Collection<Req> findMany(final Request<Req> request) {
		assert request != null;
		Collection<Req> result;
		result = this.repository.findManyAll();
		return result;
	}
}
