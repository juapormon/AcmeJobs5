
package acme.features.sponsor.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.CommercialBanner;
import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select b from CommercialBanner b where b.id = ?1")
	CommercialBanner findOneCommercialBannerById(int id);

	@Query("select b from CommercialBanner b where b.sponsor.id = ?1")
	Collection<CommercialBanner> findManyBySponsorId(int SponsorId);

	@Query("select cp from CustomisationParameters cp")
	CustomisationParameters findCustomisationParameters();

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor getSponsorById(int activeRoleId);

}
