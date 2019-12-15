
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customisationParameters.CustomisationParameters;
import acme.entities.message.Message;
import acme.entities.message.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneMessageThreadById(int messageThreadId);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyByMessageThreadId(Integer messageThreadId);

	@Query("select count(mt)>0 from MessageThread mt join mt.participants p where mt.id = ?1 and p.id = ?2")
	Boolean findExistsMessageThreadParticipant(int messageThreadId, int authenticatedId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select c from CustomisationParameters c")
	CustomisationParameters findOneCustomisationParameters();
}
