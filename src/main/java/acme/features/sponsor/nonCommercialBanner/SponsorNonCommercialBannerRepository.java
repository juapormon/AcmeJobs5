
package acme.features.sponsor.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.NonCommercialBanner;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select nb from NonCommercialBanner nb where nb.id = ?1")
	NonCommercialBanner findOneNonCommercialBannerById(int id);

	@Query("select nb from NonCommercialBanner nb where nb.sponsor.id = ?1")
	Collection<NonCommercialBanner> findManyBySponsorId(int Sponsorid);

	@Query("select cp from CustomisationParameters cp")
	CustomisationParameters findCustomisationParameters();

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor getSponsorById(int activeRoleId);
}
