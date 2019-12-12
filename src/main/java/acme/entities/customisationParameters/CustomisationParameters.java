
package acme.entities.customisationParameters;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
		List<String> spamWords = this.getSpamWordsList();

		String[] textWords = text.split(" ");
		int wordCounter = 0;
		int spamCounter = 0;
		for (String word : textWords) {
			word = word.trim().toLowerCase();
			if (word.length() > 0) {
				wordCounter++;
				if (spamWords.contains(word)) {
					spamCounter++;
				}
			}
		}
		return (float) spamCounter / (float) wordCounter * 100 > this.spamThreshold;
	}
}
