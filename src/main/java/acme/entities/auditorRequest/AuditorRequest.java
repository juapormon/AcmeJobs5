
package acme.entities.auditorRequest;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class AuditorRequest extends DomainEntity {

	private static final long		serialVersionUID	= 1L;

	@NotBlank
	private String					firm;

	@NotBlank
	private String					responsabilityStatement;

	@NotNull
	private AuditorRequestStatus	status;

	// Relationships

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private UserAccount				userAccount;
}
