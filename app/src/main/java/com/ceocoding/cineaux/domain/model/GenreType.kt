package com.ceocoding.cineaux.domain.model

sealed class GenreType(val type: String ){
    object Action: GenreType("Action")
    object Adventure: GenreType("Adventure")
    object Animation: GenreType("Animation")
    object Comedy: GenreType("Comedy")
    object Crime: GenreType("Crime")
    object Documentary: GenreType("Documentary")
    object Drama: GenreType("Drama")
    object Family: GenreType("Family")
    object Fantasy: GenreType("Fantasy")
    object History: GenreType("History")
    object Horror: GenreType("Horror")
    object Music: GenreType("Music")
    object Mystery: GenreType("Mystery")
    object Romance: GenreType("Romance")
    object ScienceFiction: GenreType("Science Fiction")
    object TvMovie: GenreType("TV Movie")
    object Thriller: GenreType("Thriller")
    object War: GenreType("War")
    object Western: GenreType("Western")
    object Film: GenreType("Film")

    companion object{
        fun toGenre(code: Int): GenreType {
            return when(code) {
                28 -> Action
                12 -> Adventure
                16 -> Animation
                35 -> Comedy
                80 -> Crime
                99 -> Documentary
                18 -> Drama
                10751 -> Family
                14 -> Fantasy
                36 -> History
                27 -> Horror
                10402 -> Music
                9648 -> Mystery
                10749 -> Romance
                878 -> ScienceFiction
                10770 -> TvMovie
                53 -> Thriller
                10752 -> War
                37 -> Western
                else -> Film
            }
        }
    }
}
