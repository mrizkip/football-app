package me.qidonk.footballapp.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.qidonk.footballapp.R
import me.qidonk.footballapp.main.DetailMatchActivity
import me.qidonk.footballapp.model.FavoriteMatch
import org.jetbrains.anko.startActivity

class FavoriteMatchAdapter(
    private val context: Context?,
    private val favorites: List<FavoriteMatch>
) : RecyclerView.Adapter<FavoriteMatchAdapter.FavoriteMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(viewHolder: FavoriteMatchViewHolder, position: Int) {
        viewHolder.bindItem(favorites[position])
    }

    class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val date: TextView = view.findViewById(R.id.itemMatch_date)
        private val homeTeam: TextView = view.findViewById(R.id.itemMatch_homeTeam)
        private val awayTeam: TextView = view.findViewById(R.id.itemMatch_awayTeam)
        private val homeScore: TextView = view.findViewById(R.id.itemMatch_homeScore)
        private val awayScore: TextView = view.findViewById(R.id.itemMatch_awayScore)

        fun bindItem(match: FavoriteMatch) {
            date.text = match.matchDate
            homeTeam.text = match.homeTeam
            awayTeam.text = match.awayTeam
            homeScore.text = match.homeScore
            awayScore.text = match.awayScore

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