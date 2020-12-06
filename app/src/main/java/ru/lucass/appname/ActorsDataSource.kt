package ru.lucass.appname

class ActorsDataSource {
    fun getActors(): List<Actor> {
        return listOf(
                Actor(R.string.Doyney_cast, "@drawable/downey_jr"),
                Actor(R.string.Evans_cast, "@drawable/evans"),
                Actor(R.string.Ruffalo_cast, "@drawable/ruffalo"),
                Actor(R.string.Hemswort_cast, "@drawable/hemsworth")
        )
    }
}
