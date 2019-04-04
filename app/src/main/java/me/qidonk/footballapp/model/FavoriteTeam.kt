package me.qidonk.footballapp.model

data class FavoriteTeam(
    val id: Long?,
    val teamId: String?,
    val teamLogo: String?,
    val teamName: String?,
    val teamBuildYear: String?,
    val teamStadium: String?,
    val teamDescription: String?
) {
    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_LOGO: String = "TEAM_LOGO"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_BUILD_YEAR: String = "TEAM_BUILD_YEAR"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
    }
}