
package acme.features.sponsor.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorCreditCardShowService implements AbstractShowService<Sponsor, CreditCard> {

	@Autowired
	SponsorCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		int creditCardId = request.getModel().getInteger("id");
		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.findOneSponsorById(principal.getActiveRoleId());

		boolean result = sponsor.getCreditCard() != null && sponsor.getCreditCard().getId() == creditCardId;

		return result;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creditCardNumber", "creditCardCvv", "creditCardMonth", "creditCardYear");
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
