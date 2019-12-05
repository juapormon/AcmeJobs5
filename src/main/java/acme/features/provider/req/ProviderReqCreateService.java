
package acme.features.provider.req;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.reqs.Req;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderReqCreateService implements AbstractCreateService<Provider, Req> {

	@Autowired
	ProviderReqRepository repository;

	@Override
	public boolean authorise(final Request<Req> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Req> request, final Req entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void unbind(final Request<Req> request, final Req entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment", "deadline", "text", "reward", "ticker");

	}

	@Override
	public Req instantiate(final Request<Req> request) {
		Req result;

		result = new Req();

		return result;
	}

	@Override
	public void validate(final Request<Req> request, final Req entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("ticker")) {
			boolean tickerIsUnique = this.repository.findManyByTicker(entity.getTicker()).size() == 0;
			errors.state(request, tickerIsUnique, "ticker", "provider.request.error.ticker-unique");
		}

		boolean deadlineHasErrors = errors.hasErrors("deadline");
		if (!deadlineHasErrors) {
			Calendar calendar = new GregorianCalendar();
			boolean isFuture = entity.getDeadline().after(calendar.getTime());
			errors.state(request, isFuture, "deadline", "provider.request.error.must-future");
		}

		boolean isConfirmed = request.getModel().getString("confirm").length() > 0
				&& request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "provider.request.error.must-confirm");

	}

	@Override
	public void create(final Request<Req> request, final Req entity) {
		Date creationMoment;

		creationMoment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(creationMoment);
		this.repository.save(entity);

	}

}
