package com.worldcupscoreboard.model;

import java.util.Objects;

public class Team {

    private final Country country;

    public Team(Country country) {
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return country == team.country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(country);
    }
}
