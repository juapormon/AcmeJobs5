
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message.Message;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyByMessageThreadId(Integer messageThreadId);

	@Query("select count(m) from Message m where m.messageThread.id = ?1 and m.creator.id = ?2")
	Integer findCountOfMessages(int messageThreadId, int authenticatedId);

}
