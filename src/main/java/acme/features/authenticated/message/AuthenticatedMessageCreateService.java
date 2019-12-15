
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.message.Message;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		int threadId = request.getModel().getInteger("threadId");
		Principal principal = request.getPrincipal();
		boolean result = this.repository.findExistsMessageThreadParticipant(threadId, principal.getActiveRoleId());

		return result;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("threadId", request.getModel().getInteger("threadId"));
		request.unbind(entity, model, "title", "moment", "body", "tags", "creator.userAccount.username");
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Message result = new Message();
		result.setCreator(this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId()));
		int messageThreadId = request.getModel().getInteger("threadId");
		result.setMessageThread(this.repository.findOneMessageThreadById(messageThreadId));

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isConfirmed = request.getModel().getString("confirm").length() > 0 && request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "authenticated.message.error.must-confirm");

		CustomisationParameters cp = this.repository.findOneCustomisationParameters();

		boolean titleHasErrors = errors.hasErrors("title");
		if (!titleHasErrors) {
			errors.state(request, !cp.isSpam(entity.getTitle()), "title", "authenticated.message.form.error.spam");
		}

		boolean bodyHasErrors = errors.hasErrors("body");
		if (!bodyHasErrors) {
			errors.state(request, !cp.isSpam(entity.getBody()), "body", "authenticated.message.form.error.spam");
		}
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);
	}

}
