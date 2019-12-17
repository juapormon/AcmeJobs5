
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audit.Audit;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service

public class AuditorAuditRecordListService implements AbstractListService<Auditor, Audit> {

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<Audit> request) {
		assert request != null;
		int jobId = request.getModel().getInteger("id");
		return this.repository.findIsJobPublished(jobId) > 0;
	}

	@Override
	public Collection<Audit> findMany(final Request<Audit> request) {
		assert request != null;

		Collection<Audit> result;
		int idJob = request.getModel().getInteger("id");
		int idAuditor = request.getPrincipal().getActiveRoleId();

		result = this.repository.findManyAuditByJobId(idJob, idAuditor);

		return result;
	}

	@Override
	public void unbind(final Request<Audit> request, final Audit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "title", "status", "body", "job", "auditor");
	}

}
