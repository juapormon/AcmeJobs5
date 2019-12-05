
package acme.features.consumer.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offer.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	@Autowired
	ConsumerOfferRepository repository;


	@Override
	public boolean authorise(final Request<Offer> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "description", "minMoney", "maxMoney", "ticker");
	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		// TODO Auto-generated method stub
		Offer result;
		result = new Offer();
		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("ticker")) {
			boolean tickerIsUnique = this.repository.findManyByTicker(entity.getTicker()).size() == 0;
			errors.state(request, tickerIsUnique, "ticker", "consumer.offer.error.ticker-unique");
		}

		boolean deadlineHasErrors = errors.hasErrors("deadline");
		if (!deadlineHasErrors) {
			Calendar calendar = new GregorianCalendar();
			boolean isFuture = entity.getDeadline().after(calendar.getTime());
			errors.state(request, isFuture, "deadline", "consumer.offer.error.must-future");
		}
		boolean maxMoneyHasErrors = errors.hasErrors("maxMoney");
		if (!maxMoneyHasErrors) {
			boolean isEur = entity.getMaxMoney().getCurrency().equals("EUR") || entity.getMaxMoney().getCurrency().equals("€");
			maxMoneyHasErrors = maxMoneyHasErrors || !isEur;
			errors.state(request, isEur, "maxMoney", "consumer.offer.error.rejected-currency");
		}

		boolean minMoneyHasErrors = errors.hasErrors("minMoney");
		if (!minMoneyHasErrors) {
			boolean isEur = entity.getMinMoney().getCurrency().equals("EUR") || entity.getMinMoney().getCurrency().equals("€");
			minMoneyHasErrors = minMoneyHasErrors || !isEur;
			errors.state(request, isEur, "minMoney", "consumer.offer.error.rejected-currency");
		}

		if (!minMoneyHasErrors && !maxMoneyHasErrors) {
			boolean notGreater = entity.getMinMoney().getAmount() <= entity.getMaxMoney().getAmount();
			errors.state(request, notGreater, "maxMoney", "consumer.offer.error.max-lower-than-min");
		}

		boolean isConfirmed = request.getModel().getString("confirm").length() > 0 && request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "consumer.offer.error.must-confirm");
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		// TODO Auto-generated method stub
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
