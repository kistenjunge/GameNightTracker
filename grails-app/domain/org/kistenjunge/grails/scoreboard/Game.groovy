package org.kistenjunge.grails.scoreboard

class Game {
	String title
	byte[] image
	String imageType

    static constraints = {
	image(nullable:true, maxSize: 16384)
	imageType(nullable:true)
	title (blank:false, nullable:false, unique:true)
    }
    String toString()
    {
	"$title"
    }
}
