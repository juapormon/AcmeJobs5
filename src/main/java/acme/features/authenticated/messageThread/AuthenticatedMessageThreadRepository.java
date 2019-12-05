
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

	@Query("select count(m) from Message m where m.messageThread.id = ?1 and m.creator.id = ?2")
	Integer findCountOfMessages(int messageThreadId, int authenticatedId);
}
