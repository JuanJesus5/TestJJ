package com.ApiRest.TestJJ.controller;

import java.lang.module.ResolutionException;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ApiRest.TestJJ.Hospital;

@RestController
public class AppController {

	private ArrayList<Hospital> hospitals = new ArrayList<Hospital>();

	@GetMapping("/hospitals")
	public ArrayList<Hospital> getHospitals() {
		hospitals.add(new Hospital("Laughing Democritus Hospital", 35, 41));
		hospitals.add(new Hospital("Crying Heraclitus Hospital", -40, 12));
		hospitals.add(new Hospital("Seven Sages Hospital", 0, -33));

		return hospitals;
	}

	@PostMapping("/newhospital")
	public void addHospitals(@RequestParam String name, int xPos, int yPos) {
		Hospital h = new Hospital(name, xPos, yPos);
		hospitals.add(h);
	}

	@GetMapping("/parameters")
	ResponseEntity<String> wrongData(@RequestParam("xPosition") int xPosition,
			@RequestParam("yPosition") int yPosition) {
		if (xPosition <= -100 || xPosition >= 100)
			return new ResponseEntity<>("The ambulance is out of range in X Position. X value must be -100 < x < 100",
					HttpStatus.BAD_REQUEST);

		if (yPosition <= -80 || yPosition >= 80) {
			return new ResponseEntity<>("The ambulance is out of range in Y Position. Y value must be -80 < y < 80",
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>("The parameters are OK", HttpStatus.OK);
	}

	@GetMapping("/ambulance")
	public Object[] getClosestHospital(@RequestParam int xPosition, @RequestParam int yPosition)  throws Exception{

		ArrayList<Hospital> aux = hospitals;
		Hospital closestHospital = null;

		// Check the given parameters and throws an Exception in case the parameters are
		// out of range.
		if (xPosition <= -100 || xPosition >= 100 || yPosition <= -80 || yPosition >= 80)
			throw new Exception("The ambulance is out of range. You can check the parameters in this path /parameters");

		float distance = 1000.0f;

		for (int i = 0; i < aux.size(); i++) {

			// Distance between 2 points
			float xDist = (float) Math.pow(aux.get(i).getX() - xPosition, 2);
			float yDist = (float) Math.pow(aux.get(i).getY() - yPosition, 2);
			float finalDist = (float) Math.sqrt(xDist + yDist);

			if (distance > finalDist) {
				distance = finalDist;
				closestHospital = aux.get(i);
			}
		}

		return new Object[] { closestHospital, "distance: " + distance };
	}

}
