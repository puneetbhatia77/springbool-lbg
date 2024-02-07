package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Beneficiary;
import net.javaguides.springboot.repository.BeneficiaryRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1")


public class BeneficiaryController {

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	
	// get beneficiaries - list all beneficiaries
	@GetMapping("beneficiaries")
	public List<Beneficiary> getAllBeneficiary(){
		return this.beneficiaryRepository.findAll();
	}
	
	// get beneficiary by id - load specific beneficiary
	@GetMapping("beneficiaries/{id}")
	public ResponseEntity<Beneficiary> getBeneficiaryById(@PathVariable(value = "id") Long beneficiaryId)
			throws ResourceNotFoundException {
		Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Beneficiary not exist with id :" + beneficiaryId));
		return ResponseEntity.ok(beneficiary);
		}
	
	// save beneficiary
	@PostMapping("beneficiaries")
	public Beneficiary createBeneficiary(@RequestBody Beneficiary beneficiary) {
		return this.beneficiaryRepository.save(beneficiary);
	}
	
	// update beneficiary
	@PutMapping("beneficiaries/{id}")
	public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable(value = "id") Long beneficiaryId,
			@RequestBody Beneficiary beneficiaryDetails)  throws ResourceNotFoundException 
	{
		Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
			.orElseThrow(() -> new ResourceNotFoundException("Beneficiry not exist with id :" + beneficiaryId));
	
	beneficiary.setFirstName(beneficiaryDetails.getFirstName());
	beneficiary.setLastName(beneficiaryDetails.getLastName());
	beneficiary.setEmail(beneficiaryDetails.getEmail());
	
	return ResponseEntity.ok(this.beneficiaryRepository.save(beneficiary));
}
	
	// delete beneficiary
	@DeleteMapping("beneficiaries/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBeneficiary(@PathVariable(value = "id") Long beneficiaryId)
			throws ResourceNotFoundException {
		Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId)
				.orElseThrow(() -> new ResourceNotFoundException("Beneficiry not exist with id :" + beneficiaryId));
		
		beneficiaryRepository.delete(beneficiary);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
