package com.nbu.sportsandrules.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbu.sportsandrules.entity.SportType;
import com.nbu.sportsandrules.repository.SportTypeRepository;

@Service
public class SportTypeService {
	@Autowired
	private SportTypeRepository sportTypeRepository;

	/**
	 * Duplicated names will not be allowed
	 */
	public boolean addSportType(SportType sportType) {
		List<SportType> sportByName = sportTypeRepository.findByName(sportType.getName());
		if (sportByName.size() > 0) {
			return true;
		}
		sportTypeRepository.save(sportType);
		return false;
	}

	public List<SportType> getAllSportTypes() {
		List<SportType> sportTypes = new ArrayList<>();
		sportTypeRepository.findAll().forEach(s -> sportTypes.add(s));
		return sportTypes;
	}

	public SportType getSportTypeByid(Integer id) {
		try {
			SportType sportType = sportTypeRepository.findById(id).get();
			return sportType;
		} catch (NoSuchElementException nse) {
			return null;
		}
	}

	public void deleteSportType(Integer id) {
		sportTypeRepository.deleteById(id);
	}
}
