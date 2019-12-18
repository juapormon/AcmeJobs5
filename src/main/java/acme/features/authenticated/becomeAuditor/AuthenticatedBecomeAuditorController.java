
package acme.features.authenticated.becomeAuditor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.becomeAuditor.BecomeAuditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/become-auditor/")
public class AuthenticatedBecomeAuditorController extends AbstractController<Authenticated, BecomeAuditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedBecomeAuditorCreateService createService;


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
