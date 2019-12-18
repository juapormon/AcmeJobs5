
package acme.features.sponsor.creditCard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCreditCardRepository extends AbstractRepository {

	@Query("select cc from CreditCard cc where cc.id = ?1")
	CreditCard findOneById(int id);

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor findOneSponsorById(int sponsorId);
}
