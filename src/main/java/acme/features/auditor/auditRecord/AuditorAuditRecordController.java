
package acme.features.auditor.auditRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.audit.Audit;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/audit-record")

public class AuditorAuditRecordController extends AbstractController<Auditor, Audit> {

	//	@Autowired
	//	AuditorAuditRecordListService	listService;

	@Autowired
	AuditorAuditRecordShowService	showService;

	@Autowired
	AuditorAuditRecordCreateService	createService;

	@Autowired
	AuditorAuditRecordUpdateService	updateService;


	@PostConstruct
	private void initialise() {
		//	super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
