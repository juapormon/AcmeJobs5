
package acme.features.authenticated.req;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.reqs.Req;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedReqShowService implements AbstractShowService<Authenticated, Req> {

	//Internal state ------------------------------------------------------------

	@Autowired
	AuthenticatedReqRepository repository;


	//AbstractShowService<Authenticated, Request> interface -----------------------

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
	public acme.entities.reqs.Req findOne(final Request<Req> request) {
		assert request != null;
		Req result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
