package me.qidonk.footballapp.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.TeamDetailActivity
import me.qidonk.footballapp.model.Team
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class TeamAdapter(private val context: Context?, val teams: List<Team>, private val clickListener: (Team) -> Unit) :
        RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.item_team, parent, false)

        return TeamViewHolder(rootView).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener(teams[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(viewHolder: TeamViewHolder, position: Int) {
        viewHolder.bindItem(teams[position])
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val teamLogo = view.itemTeam_logo
        val teamName = view.itemTeam_name

        fun bindItem(team: Team) {
            Picasso.get().load(team.teamLogo).into(teamLogo)
            teamName.text = team.teamName
        }
    }
}