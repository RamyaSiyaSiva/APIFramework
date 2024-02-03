package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@Deleteplace")
	public void beforeScenario() throws IOException {
		//Execute this code only when place_id is null
		stepDefination m = new stepDefination();
		if (stepDefination.place_id==null) {
		m.add_place_payload_with("Ramya","German","Frankfurt");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_placeid_created_maps_to_using("Ramya", "GetPlaceAPI");
		}
	}
	
};
