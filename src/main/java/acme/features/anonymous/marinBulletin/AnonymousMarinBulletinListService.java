/*
 * AdministratorUserAccountListService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.marinBulletin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.MarinBulletin;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousMarinBulletinListService implements AbstractListService<Anonymous, MarinBulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AnonymousMarinBulletinRepository repository;


	// AbstractListService<Administrator, Shout interface --------------

	@Override
	public boolean authorise(final Request<MarinBulletin> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<MarinBulletin> findMany(final Request<MarinBulletin> request) {
		assert request != null;

		Collection<MarinBulletin> result;

		result = this.repository.findMany();

		return result;
	}

	@Override
	public void unbind(final Request<MarinBulletin> request, final MarinBulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "moment");
	}

}
