package me.qidonk.footballapp.main.fragment.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team_detail.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.model.Team
import me.qidonk.footballapp.presenter.TeamDetailPresenter
import me.qidonk.footballapp.repository.ApiRepository
import me.qidonk.footballapp.view.TeamDetailView

class TeamDetailFragment : Fragment(), TeamDetailView {

    private lateinit var presenter: TeamDetailPresenter

    companion object {
        fun newInstance(): TeamDetailFragment {
            return TeamDetailFragment()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, repository, gson)

        val teamId = arguments?.getString("teamId")

        presenter.getTeamDetail(teamId)
    }

    override fun showTeamDetail(data: Team) {
        teamDetail_description.text = data.teamDescription
    }
}
