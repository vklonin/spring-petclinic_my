package guru.qa.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Owner {
	int id;
	String firstName;
	String lastName;
	String address;
	String city;
	String phone;
}
