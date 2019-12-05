
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenge.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	@Autowired
	AdministratorChallengeRepository repository;


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "goalGold", "goalSilver", "goalBronze", "rewardGold", "rewardSilver", "rewardBronze");

	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;

		result = new Challenge();

		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean deadlineHasErrors = errors.hasErrors("deadline");
		if (!deadlineHasErrors) {
			Calendar calendar = new GregorianCalendar();
			boolean isFuture = entity.getDeadline().after(calendar.getTime());
			errors.state(request, isFuture, "deadline", "administrator.challenge.error.must-future");
		}
		boolean rewardBronzeHasErrors = errors.hasErrors("rewardBronze");
		if (!rewardBronzeHasErrors) {
			boolean isEur = entity.getRewardBronze().getCurrency().equals("EUR") || entity.getRewardBronze().getCurrency().equals("€");
			rewardBronzeHasErrors = rewardBronzeHasErrors || !isEur;
			errors.state(request, isEur, "rewardBronze", "administrator.challenge.error.rejected-currency");
		}

		boolean rewardSilverHasErrors = errors.hasErrors("rewardSilver");
		if (!rewardSilverHasErrors) {
			boolean isEur = entity.getRewardSilver().getCurrency().equals("EUR") || entity.getRewardSilver().getCurrency().equals("€");
			rewardSilverHasErrors = rewardSilverHasErrors || !isEur;
			errors.state(request, isEur, "rewardSilver", "administrator.challenge.error.rejected-currency");
		}
		boolean rewardGoldHasErrors = errors.hasErrors("rewardGold");
		if (!rewardGoldHasErrors) {
			boolean isEur = entity.getRewardGold().getCurrency().equals("EUR") || entity.getRewardGold().getCurrency().equals("€");
			rewardGoldHasErrors = rewardGoldHasErrors || !isEur;
			errors.state(request, isEur, "rewardGold", "administrator.challenge.error.rejected-currency");
		}
	}
	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		this.repository.save(entity);

	}

}
