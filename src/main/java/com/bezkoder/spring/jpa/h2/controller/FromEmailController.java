package com.bezkoder.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bezkoder.spring.jpa.h2.model.FromEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.h2.repository.FromEmailRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FromEmailController {

	@Autowired
	FromEmailRepository fromEmailRepository;

	@GetMapping("/fromEmails")
	public ResponseEntity<List<FromEmail>> getAllFromEmails(@RequestParam(required = false) String title) {
		try {
			List<FromEmail> fromEmails = new ArrayList<>();

			if (title == null)
				fromEmailRepository.findAll().forEach(fromEmails::add);
			else
				fromEmailRepository.findByEmail(title).forEach(fromEmails::add);

			if (fromEmails.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(fromEmails, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/fromEmails/{id}")
	public ResponseEntity<FromEmail> getFromEmailById(@PathVariable("id") long id) {
		Optional<FromEmail> fromEmailData = fromEmailRepository.findById(id);

		if (fromEmailData.isPresent()) {
			return new ResponseEntity<>(fromEmailData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/fromEmails")
	public ResponseEntity<FromEmail> createFromEmail(@RequestBody FromEmail fromEmail) {
		try {
			FromEmail _fromEmail = fromEmailRepository
					.save(new FromEmail(fromEmail.getEmail()));
			return new ResponseEntity<>(_fromEmail, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/fromEmails/{id}")
	public ResponseEntity<FromEmail> updateFromEmail(@PathVariable("id") long id, @RequestBody FromEmail fromEmail) {
		Optional<FromEmail> fromEmailData = fromEmailRepository.findById(id);

		if (fromEmailData.isPresent()) {
			FromEmail _fromEmail = fromEmailData.get();
			_fromEmail.setEmail(fromEmail.getEmail());
			return new ResponseEntity<>(fromEmailRepository.save(_fromEmail), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/fromEmails/{id}")
	public ResponseEntity<HttpStatus> deleteFromEmail(@PathVariable("id") long id) {
		try {
			fromEmailRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
