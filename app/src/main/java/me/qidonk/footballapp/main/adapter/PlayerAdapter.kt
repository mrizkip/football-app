package me.qidonk.footballapp.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player.view.*
import me.qidonk.footballapp.R
import me.qidonk.footballapp.model.Player

class PlayerAdapter(private val context: Context?, private val players: List<Player>, val clickListener: (Player) -> Unit) :
        RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.item_player, parent, false)

        return PlayerViewHolder(rootView).apply {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    clickListener(players[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(viewHolder: PlayerViewHolder, position: Int) {
        viewHolder.bindItem(players[position])
    }


    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val playerName = view.itemPlayer_name
        private val playerImage = view.itemPlayer_image
        private val playerPosition = view.itemPlayer_position

        fun bindItem(player: Player) {
            Picasso.get().load(player.image).into(playerImage)
            playerName.text = player.name
            playerPosition.text = player.position
        }
    }
}