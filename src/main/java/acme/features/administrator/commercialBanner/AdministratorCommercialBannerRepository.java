
package acme.features.administrator.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.CommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository

public interface AdministratorCommercialBannerRepository extends AbstractRepository {

	@Query("select b from CommercialBanner b where b.id = ?1")
	CommercialBanner findOneById(int id);

	@Query("select b from CommercialBanner b")
	Collection<CommercialBanner> findManyAll();
}
