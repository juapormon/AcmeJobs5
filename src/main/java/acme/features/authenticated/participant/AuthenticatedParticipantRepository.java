
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message.MessageThread;
import acme.entities.message.Participant;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipantRepository extends AbstractRepository {

	@Query("select p from Participant p where p.id = ?1")
	Participant findOneById(int id);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneMessageThreadById(int messageThreadId);

	@Query("select count(mt)>0 from MessageThread mt where mt.id = ?1 and mt.creator.id = ?2")
	Boolean findIsThreadCreator(int threadId, int creatorId);

	@Query("select count(p)>0 from Participant p where p.messageThread.id = ?1 and p.authenticated.id = ?2")
	Boolean findIsThreadParticipant(int threadId, int authenticatedId);

	@Query("select count(p)>0 from Participant p where p.messageThread.id = ?1 and p.authenticated.id = ?2")
	Boolean findAuthenticatedExists(int authenticatedId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select p from Participant p where p.messageThread.id = ?1")
	Collection<Participant> findManyParticipantsByThreadId(int threadId);

	@Query("select a from Authenticated a where a.userAccount.username = ?1")
	Authenticated findOneAuthenticatedByUsername(String username);
}
