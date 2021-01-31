package ru.lucass.appname

import ru.lucass.data.Movie

interface Navigator {
    fun nextFragment(movie: Movie)
}