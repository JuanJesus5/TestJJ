package com.ApiRest.TestJJ.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("/ambulance")
	public Hospital getClosestHospital(@RequestParam int xPosition, @RequestParam int yPosition) throws Exception {

		ArrayList<Hospital> aux = hospitals;
		Hospital closestHospital = null;

		if (xPosition <= -100 || xPosition >= 100) {
			throw new Exception("The ambulance is out of range in X Position ");
		}

		if (yPosition <= -80 || yPosition >= 80) {
			throw new Exception("The ambulance is out of range in Y Position ");
		}

		float distance = 1000.0f;

		for (int i = 0; i < aux.size(); i++) {
			
			//Distance between 2 points
			float xDist = (float) Math.pow(aux.get(i).getX() - xPosition, 2);
			float yDist = (float) Math.pow(aux.get(i).getY() - yPosition, 2);
			float finalDist = (float) Math.sqrt(xDist + yDist);

			if (distance > finalDist) {
				distance = finalDist;
				closestHospital = aux.get(i);
			}
		}

		return closestHospital;
	}

}
