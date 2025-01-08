package com.zurich.insurance_management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zurich.insurance_management.custom.exceptions.GeneralControlledException;
import com.zurich.insurance_management.custom.mappers.InsuranceMapper;
import com.zurich.insurance_management.entities.Insurance;
import com.zurich.insurance_management.repository.InsuranceRepository;
import com.zurich.insurance_management.requests.InsuranceRequest;
import com.zurich.insurance_management.responses.CommonResponse;
import com.zurich.insurance_management.responses.InsuranceResponse;
import com.zurich.insurance_management.service.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	private InsuranceRepository repository;

	@Override
	public List<InsuranceResponse> getInsuranceList(String id) throws GeneralControlledException {
		Long idNumber = Long.valueOf(id);
		List<Insurance> insurances = this.repository.findAllByClientId(idNumber);
		return InsuranceMapper.entitiesToResponses(insurances);
	}

	@Override
	public InsuranceResponse getInsurance(String id) throws GeneralControlledException {
		Long idNumber = Long.valueOf(id);
		Optional<Insurance> insurance = this.repository.findById(idNumber);
		if (insurance.isEmpty()) {
			throw new GeneralControlledException("No hemos encontrado la póliza solicitada");
		}
		return InsuranceMapper.entityToResponse(insurance.get());
	}

	@Override
	public CommonResponse createInsurance(InsuranceRequest request) throws GeneralControlledException {
		Long newId = this.generateUniqueId();
		if (newId == null) {
			throw new GeneralControlledException("Por el momento no podemos crear nuevas pólizas");
		}
		Insurance insurance = InsuranceMapper.requestToEntity(request);
		insurance.setId(newId);
		Insurance result = this.repository.save(insurance);
		return new CommonResponse(result != null);
	}

	@Override
	public CommonResponse updateInsurance(InsuranceRequest request, boolean forzeId) throws GeneralControlledException {
		Long idNumber = Long.valueOf(request.getId());
		boolean exist = this.repository.existsById(idNumber);
		if (!exist && !forzeId) {
			throw new GeneralControlledException("Por el momento no hemos encontrado la póliza por actualizar");
		}
		Insurance insurance = InsuranceMapper.requestToEntity(request);
		Insurance result = this.repository.save(insurance);
		return new CommonResponse(result != null);
	}

	@Override
	public CommonResponse deleteInsurance(String id) throws GeneralControlledException {
		Long idNumber = Long.valueOf(id);
		this.repository.deleteById(idNumber);
		return new CommonResponse(true);
	}

	private Long generateUniqueId() {
		Random rand = new Random();
		Long id = null;
		boolean exists = true;
		int tries = 0;

		while (exists) {
			id = 1000000000L + (long) (rand.nextDouble() * 8999999999L);
			exists = repository.existsById(id);
			if (exists && tries >= 3) {
				exists = false;
			} else if (exists) {
				tries++;
			}
		}

		return id;
	}

}
