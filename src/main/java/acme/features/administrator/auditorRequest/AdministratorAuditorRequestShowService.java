
package acme.features.administrator.auditorRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorRequest.AuditorRequest;
import acme.entities.auditorRequest.AuditorRequestStatus;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorAuditorRequestShowService implements AbstractShowService<Administrator, AuditorRequest> {

	@Autowired
	private AdministratorAuditorRequestRepository repository;


	@Override
	public boolean authorise(final Request<AuditorRequest> request) {
		assert request != null;

		int auditorRequestId = request.getModel().getInteger("id");
		AuditorRequest auditorRequest = this.repository.findOneById(auditorRequestId);
		boolean result = auditorRequest.getStatus() == AuditorRequestStatus.PENDING;

		return result;
	}

	@Override
	public void unbind(final Request<AuditorRequest> request, final AuditorRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username", "firm", "responsabilityStatement", "status");

	}

	@Override
	public AuditorRequest findOne(final Request<AuditorRequest> request) {
		assert request != null;
		AuditorRequest result;
		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

}
