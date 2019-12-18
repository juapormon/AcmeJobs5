
package acme.features.authenticated.messageThread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.message.MessageThread;
import acme.entities.message.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, MessageThread> {

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
	}

	@Override
	public MessageThread instantiate(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result = new MessageThread();

		Authenticated auth = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());
		result.setCreator(auth);

		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean titleHasErrors = errors.hasErrors("title");
		if (!titleHasErrors) {
			CustomisationParameters cp = this.repository.findOneCustomisationParameters();
			errors.state(request, !cp.isSpam(entity.getTitle()), "title", "authenticated.message-thread.form.error.spam");
		}
	}

	@Override
	public void create(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;

		Date moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		Authenticated auth = this.repository.findOneAuthenticatedById(request.getPrincipal().getActiveRoleId());
		Participant creator = new Participant();
		creator.setMessageThread(entity);
		creator.setAuthenticated(auth);

		this.repository.save(entity);
		this.repository.save(creator);
	}

}
