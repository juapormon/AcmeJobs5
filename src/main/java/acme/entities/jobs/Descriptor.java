
package acme.entities.jobs;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Descriptor extends DomainEntity {

	private static final long		serialVersionUID	= 1L;

	@NotBlank
	private String					description;

	// Relationships

	@NotNull
	@OneToMany()
	private Collection<@Valid Duty>	duties;
}
