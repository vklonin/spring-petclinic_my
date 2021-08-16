package guru.qa;

import com.codeborne.selenide.Selenide;
import guru.qa.domain.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;

public class AddOwnerTest {

	private OwnersManager om = new OwnersManager();

	@Test
	void checkAddOwner(){
		Selenide.open("http://localhost:8080/owners/find");
		$("a.btn").click();
		$("#firstName").setValue("Jhon");
		$("#lastName").setValue("Kennedy");
		$("#address").setValue("Graveyard");
		$("#city").setValue("Washington");
		$("#telephone").setValue("999");
		$("button[type='submit']").click();
		Owner actualOwner = om.findByLastName("Kennedy").get(0);
		Assertions.assertEquals("Washington", actualOwner.getCity());
	}

}
