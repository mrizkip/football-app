package me.qidonk.footballapp.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.DetailMatchActivity
import me.qidonk.footballapp.model.Match
import org.jetbrains.anko.startActivity

class MatchAdapter(
    private val context: Context?,
    private val matches: List<Match>
) : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(viewHolder: MatchViewHolder, position: Int) {
        viewHolder.bindItem(matches[position])
    }

    class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val date: TextView = view.findViewById(R.id.itemMatch_date)
        private val homeTeam: TextView = view.findViewById(R.id.itemMatch_homeTeam)
        private val awayTeam: TextView = view.findViewById(R.id.itemMatch_awayTeam)
        private val homeScore: TextView = view.findViewById(R.id.itemMatch_homeScore)
        private val awayScore: TextView = view.findViewById(R.id.itemMatch_awayScore)
        private val time: TextView = view.findViewById(R.id.itemMatch_time)

        fun bindItem(match: Match) {
            date.text = match.matchDate
            time.text = match.matchTime
            homeTeam.text = match.homeTeam
            awayTeam.text = match.awayTeam
            homeScore.text = match.scoreHome
            awayScore.text = match.scoreAway

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailMatchActivity>(
                    "matchId" to match.matchId,
                    "homeTeamId" to match.homeTeamId,
                    "awayTeamId" to match.awayTeamId
                )
            }
        }
    }
}