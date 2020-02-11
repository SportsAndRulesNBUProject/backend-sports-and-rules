package com.nbu.sportsandrules.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbu.sportsandrules.entity.Athlete;
import com.nbu.sportsandrules.repository.AthleteRepository;

@Service
public class AthleteService {
    @Autowired
    private AthleteRepository athleteRepository;

    public List<Athlete> getAllAthletes() {
        List<Athlete> athletes = new ArrayList<>();
        athleteRepository.findAll().forEach(a -> athletes.add(a));
        return athletes;
    }

    public Athlete getAthleteByid(Integer id) {
        try {
            Athlete athlete = athleteRepository.findById(id).get();
            return athlete;
        } catch (NoSuchElementException nse) {
            return null;
        }

    }

    public void deleteAthlete(Integer id) {
        athleteRepository.deleteById(id);
    }

    public void addAthlete(Athlete athlete) {
        athleteRepository.save(athlete);
    }

    public List<Athlete> getAllAthletesByTeamId(Integer id) {
        return athleteRepository.findByTeamId(id);

    }

    public void updateAthlete(Athlete athlete) {
        athleteRepository.save(athlete);
    }

    public List<Athlete> getAllAthletesByLeagueId(Integer id) {
        return athleteRepository.findByLeagueId(id);
    }

}
