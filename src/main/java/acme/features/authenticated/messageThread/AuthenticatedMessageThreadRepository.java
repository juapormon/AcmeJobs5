
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.message.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneById(int id);

	@Query("select distinct p.messageThread from Participant p where p.authenticated.id = ?1")
	Collection<MessageThread> findManyByAuthenticatedId(int authenticatedId);

	@Query("select count(p)>0 from Participant p where p.messageThread.id = ?1 and p.authenticated.id = ?2")
	Boolean findExistsMessageThreadParticipant(int messageThreadId, int authenticatedId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select c from CustomisationParameters c")
	CustomisationParameters findOneCustomisationParameters();
}
