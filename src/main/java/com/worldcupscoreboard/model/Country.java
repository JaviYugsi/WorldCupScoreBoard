package com.worldcupscoreboard.model;

public enum Country {

    NETHERLANDS(1),
    SENEGAL(2),
    ECUADOR(3),
    QATAR(4),
    ENGLAND(5),
    USA(6),
    IRAN(7),
    WALES(8),
    ARGENTINA(9),
    POLAND(10),
    MEXICO(11),
    SAUDI_ARABIA(12),
    FRANCE(13),
    AUSTRALIA(14),
    DENMARK(15),
    TUNISIA(16),
    SPAIN(17),
    COSTA_RICA(18),
    GERMANY(19),
    JAPAN(20),
    BELGIUM(21),
    CANADA(22),
    MOROCCO(23),
    CROATIA(24),
    BRAZIL(25),
    SERBIA(26),
    SWITZERLAND(27),
    CAMEROON(28),
    PORTUGAL(29),
    GHANA(30),
    URUGUAY(31),
    SOUTH_KOREA(32);


    private final int idCountry;

    Country(int idCountry) {
        this.idCountry = idCountry;
    }

    public int getIdCountry() {
        return idCountry;
    }
}
