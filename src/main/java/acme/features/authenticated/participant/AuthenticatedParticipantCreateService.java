
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipantCreateService implements AbstractCreateService<Authenticated, Participant> {

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		int threadId = request.getModel().getInteger("threadId");
		Principal principal = request.getPrincipal();
		boolean result = this.repository.findIsThreadCreator(threadId, principal.getActiveRoleId());

		return result;
	}

	@Override
	public void bind(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("threadId", request.getModel().getInteger("threadId"));
		request.unbind(entity, model, "authenticated.userAccount.username");
	}

	@Override
	public Participant instantiate(final Request<Participant> request) {
		assert request != null;

		Participant result = new Participant();

		int threadId = request.getModel().getInteger("threadId");
		result.setMessageThread(this.repository.findOneMessageThreadById(threadId));

		if (request.getModel().hasAttribute("authenticated.userAccount.username")) {
			String username = request.getModel().getString("authenticated.userAccount.username");
			result.setAuthenticated(this.repository.findOneAuthenticatedByUsername(username));
		}

		return result;
	}

	@Override
	public void validate(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean usernameHasErrors = errors.hasErrors("authenticated.userAccount.username");
		if (!usernameHasErrors) {
			boolean authenticatedExists = entity.getAuthenticated() != null;
			errors.state(request, authenticatedExists, "authenticated.userAccount.username", "authenticated.participant.form.error.inexistent-user");

			if (authenticatedExists) {
				boolean participantNonExistent = !this.repository.findIsThreadParticipant(entity.getMessageThread().getId(), entity.getAuthenticated().getId());
				errors.state(request, participantNonExistent, "authenticated.userAccount.username", "authenticated.participant.form.error.existent-participant");
			}
		}
	}

	@Override
	public void create(final Request<Participant> request, final Participant entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
