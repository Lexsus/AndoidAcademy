package ru.lucass.appname

class ActorsDataSource {
    fun getActors(): List<ActorMy> {
        return listOf(
                ActorMy(R.string.Doyney_cast, "@drawable/downey_jr"),
                ActorMy(R.string.Evans_cast, "@drawable/evans"),
                ActorMy(R.string.Ruffalo_cast, "@drawable/ruffalo"),
                ActorMy(R.string.Hemswort_cast, "@drawable/hemsworth")
        )
    }
}
