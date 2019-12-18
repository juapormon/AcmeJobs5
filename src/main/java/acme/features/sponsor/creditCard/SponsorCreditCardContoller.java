
package acme.features.sponsor.creditCard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/credit-card")
public class SponsorCreditCardContoller extends AbstractController<Sponsor, CreditCard> {

	@Autowired
	private SponsorCreditCardShowService	showService;
	@Autowired
	private SponsorCreditCardUpdateService	updateService;
	@Autowired
	private SponsorCreditCardCreateService	createService;
	@Autowired
	private SponsorCreditCardDeleteService	deleteService;

	/*
	 * @Autowired
	 * private SponsorCreditCardCreateService createService;
	 *
	 * @Autowired
	 * private SponsorCreditCardDeleteService deleteService;
	 */


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		/*
		 * super.addBasicCommand(BasicCommand.CREATE, this.createService);
		 * super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		 * super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		 */
	}
}
