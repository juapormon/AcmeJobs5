
package acme.features.administrator.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.company.Company;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCompanyCreateService implements AbstractCreateService<Administrator, Company> {

	@Autowired
	AdministratorCompanyRepository repository;


	@Override
	public boolean authorise(final Request<Company> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Company> request, final Company entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Company> request, final Company entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "ceo", "activities", "website", "phone", "email", "stars", "incorporated");
	}

	@Override
	public Company instantiate(final Request<Company> request) {
		Company result;

		result = new Company();

		return result;
	}

	@Override
	public void validate(final Request<Company> request, final Company entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Company> request, final Company entity) {

		this.repository.save(entity);
	}
}
