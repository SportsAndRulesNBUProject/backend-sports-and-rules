package com.nbu.sportsandrules.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbu.sportsandrules.entity.Achievement;
import com.nbu.sportsandrules.entity.Sport;
import com.nbu.sportsandrules.repository.AchievementRepository;
import com.nbu.sportsandrules.repository.SportRepository;

@Service
public class SportService {
	@Autowired
	private SportRepository sportRepository;

	@Autowired
	private AchievementRepository achievementRepository;

	/**
	 * Duplicated names will not be allowed
	 */
	public boolean addSport(Sport sport) {
		List<Sport> sportByName = sportRepository.findByName(sport.getName());
		if (sportByName.size() > 0) {
			return true;
		}

		sportRepository.save(sport);
		return false;
	}

	public List<Sport> getAllSports() {
		List<Sport> sports = new ArrayList<>();
		sportRepository.findAll().forEach(sport -> sports.add(sport));

		for (Sport sport : sports) {
			List<Achievement> achievements = getSportAchievements(sport.getId());

			sport.setAchievements(achievements);
		}

		return sports;
	}

	public List<Sport> getAllSportsByCategoryId(Integer id) {
		List<Sport> sports = new ArrayList<>();
		sportRepository.findByCategoryId(id).forEach(sport -> sports.add(sport));

		for (Sport sport : sports) {
			List<Achievement> achievements = getSportAchievements(sport.getId());

			sport.setAchievements(achievements);
		}

		return sports;
	}

	public void saveAchievements(List<Achievement> achievements) {
		for (Achievement a : achievements) {
			achievementRepository.save(a);
		}
	}

	public Achievement getAchivementByIdAndSportId(Integer id, Integer sportId) {
		try {
			Achievement achievement = achievementRepository.findByIdAndSportId(id, sportId);
			return achievement;
		} catch (NoSuchElementException nse) {
			return null;
		}
	}

	public Sport getSportById(Integer id) {
		Sport sport = sportRepository.findById(id).get();
		List<Achievement> achievements = getSportAchievements(id);
		sport.setAchievements(achievements);
		return sport;
	}

	private List<Achievement> getSportAchievements(Integer id) {
		return achievementRepository.findBySportId(id);
	}

	public void updateSport(Sport sport) {
		sportRepository.save(sport);
	}

	public void deleteSportById(Integer id) {
		sportRepository.deleteById(id);
	}

}
