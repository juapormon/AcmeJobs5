
package acme.entities.customisationParameters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomisationParameters extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "^[^,]+([,][^,]+)*$")
	private String				spamWords;

	@Range(min = 0, max = 100)
	private float				spamThreshold;


	@Transient
	public List<String> getSpamWordsList() {
		return Arrays.asList(this.spamWords.split(" ")).stream().map(x -> x.toLowerCase()).collect(Collectors.toList());
	}

	public boolean isSpam(final String text) {
		int spamCount = 0;
		for (String spamWord : this.getSpamWordsList()) {
			spamCount += StringUtils.countMatches(text, spamWord);
		}
		float spamThreshold = 0;
		return (float) spamCount / (float) text.split("\\w+").length * 100 > spamThreshold;
	}
}
