
package acme.entities.banner;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Sponsor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NonCommercialBanner extends Banner {

	private static final long	serialVersionUID	= 1L;

	@URL
	private String				jingle;

	// D05 -------------------------------

	@Valid
	@ManyToOne(optional = true)
	private Sponsor				sponsor;
}
