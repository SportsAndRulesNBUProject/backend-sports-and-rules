package com.nbu.sportsandrules.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nbu.sportsandrules.entity.League;
import com.nbu.sportsandrules.repository.LeagueRepository;

@Service
public class LeagueService {
    @Autowired
    LeagueRepository leaguerepository;

    public League getLeagueById(Integer id) {
        try {
            League league = leaguerepository.findById(id).get();
            return league;
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    public List<League> getAllLEagues() {
        List<League> leagues = new ArrayList<>();
        leaguerepository.findAll().forEach(l -> leagues.add(l));
        return leagues;
    }

    public void deleteLeague(Integer id) {
        leaguerepository.deleteById(id);
    }

    public boolean addLeague(League newLeague) {
        List<League> sportByName = leaguerepository.findByName(newLeague.getName());
        if (sportByName.size() > 0) {
            return true;
        }

        leaguerepository.save(newLeague);
        return false;
    }

    public List<League> getLeaguesBySportId(Integer id) {
        return leaguerepository.findBySportId(id);
    }

    public void updateLeague(League league) {
        leaguerepository.save(league);
    }

}
