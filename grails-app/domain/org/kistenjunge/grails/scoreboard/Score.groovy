package org.kistenjunge.grails.scoreboard

class Score {
	static belongsTo = [gamer:Gamer]
	Game game
	Session gamesNight
	int points
    static constraints = {
    }

    String toString(){
	"$gamer : $points"
    }
}
