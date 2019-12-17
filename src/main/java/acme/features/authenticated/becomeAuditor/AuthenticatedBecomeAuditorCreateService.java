/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.becomeAuditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.becomeAuditor.BecomeAuditor;
import acme.entities.becomeAuditor.BecomeAuditorStatus;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedBecomeAuditorCreateService implements AbstractCreateService<Authenticated, BecomeAuditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedBecomeAuditorRepository repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<BecomeAuditor> request) {
		assert request != null;

		boolean result;
		Principal principal = request.getPrincipal();

		principal = request.getPrincipal();
		result = this.repository.findOneAuditorByUserAccountId(principal.getAccountId()) == null;

		return result;
	}

	@Override
	public void validate(final Request<BecomeAuditor> request, final BecomeAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Principal principal;

		principal = request.getPrincipal();

		boolean noMorePending = !(this.repository.findPendingById(principal.getAccountId()) > 0);

		errors.state(request, noMorePending, "status", "authenticated.become-auditor.error.noMorePending");
	}

	@Override
	public void bind(final Request<BecomeAuditor> request, final BecomeAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<BecomeAuditor> request, final BecomeAuditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsabilityStatement");
	}

	@Override
	public BecomeAuditor instantiate(final Request<BecomeAuditor> request) {
		assert request != null;

		BecomeAuditor result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new BecomeAuditor();
		result.setUserAccount(userAccount);
		result.setStatus(BecomeAuditorStatus.PENDING);

		return result;
	}

	@Override
	public void create(final Request<BecomeAuditor> request, final BecomeAuditor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<BecomeAuditor> request, final Response<BecomeAuditor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
