package ru.lucass.appname

class FilmDataSource {
    fun getFilms(): List<Film> {
        return listOf(
                Film("Avengers: End Game", "@drawable/movie","13+"),
                Film("Tenet", "@drawable/tenet_movie","16+"),
                Film("Black Widow", "@drawable/black_window_movie","13+"),
                Film("Wonder Woman 1984", "@drawable/wonder_women_movie","13+")


        )
    }
}