
package acme.features.sponsor.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.CommercialBanner;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	@Autowired
	SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "targetURL", "creditCardNumber", "creditCardCvv", "creditCardMonth", "creditCardYear");
	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		assert request != null;
		CommercialBanner result;

		result = new CommercialBanner();

		Sponsor sponsor = this.repository.findOneSponsorById(request.getPrincipal().getActiveRoleId());
		result.setSponsor(sponsor);

		if (sponsor.getCreditCard() != null) {
			result.setCreditCardNumber(sponsor.getCreditCard().getCreditCardNumber());
			result.setCreditCardCvv(sponsor.getCreditCard().getCreditCardCvv());
			result.setCreditCardMonth(sponsor.getCreditCard().getCreditCardMonth());
			result.setCreditCardYear(sponsor.getCreditCard().getCreditCardYear());
		}

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CustomisationParameters cp = this.repository.findOneCustomisationParameters();

		boolean sloganHasErrors = errors.hasErrors("slogan");
		if (!sloganHasErrors) {
			errors.state(request, !cp.isSpam(entity.getSlogan()), "slogan", "sponsor.commercial-banner.form.error.spam");
		}
	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
