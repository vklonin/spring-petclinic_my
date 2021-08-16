package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import guru.qa.domain.Owner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class FindOwnerTest {

	private OwnersManager om = new OwnersManager();
	private int newOwnerID;

	@BeforeEach
	void addOwner() {
		newOwnerID = om.createOwner(Owner.builder()
			.firstName("Vlad")
			.lastName("Klonin")
			.address("34 Yaroslavskaya")
			.phone("000000000")
			.city("Moscow")
			.build());
	}

	@RepeatedTest(2)
	@Test
	void findOwnersTest(){
		Selenide.open("http://localhost:8080/owners/find");
		Selenide.$("#lastName").setValue("Klonin");
		Selenide.$("button[type='submit']").click();
		Selenide.$("table.table").shouldBe(Condition.visible);
		Selenide.$$("tr").find(Condition.text("Name")).shouldHave(Condition.text("Vlad Klonin"));
	}

	@AfterEach
	void releaseOwner(){
		om.deleteOwner(newOwnerID);
	}
}

