
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message.MessageThread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneById(int id);

	@Query("select distinct m.messageThread from Message m where m.creator.id = ?1")
	Collection<MessageThread> findManyByAuthenticatedId(int authenticatedId);

	@Query("select count(mt)>0 from MessageThread mt join mt.participants p where mt.id = ?1 and p.id = ?2")
	Boolean findExistsMessageThreadParticipant(int messageThreadId, int authenticatedId);
}
