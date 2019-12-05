
package acme.features.administrator.jobsChart;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.forms.JobsChart;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/jobs-chart")
public class AdministratorJobsChartController extends AbstractController<Administrator, JobsChart> {

	@Autowired
	AdministratorJobsChartShowService showService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.CHART, BasicCommand.SHOW, this.showService);
	}

}
