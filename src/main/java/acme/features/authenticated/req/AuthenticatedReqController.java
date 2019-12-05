
package acme.features.authenticated.req;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.reqs.Req;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/request/")

public class AuthenticatedReqController extends AbstractController<Authenticated, Req> {

	// Internal State --------------

	@Autowired
	private AuthenticatedReqListService	listService;

	@Autowired
	private AuthenticatedReqShowService	showService;


	// Constructors ----------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
