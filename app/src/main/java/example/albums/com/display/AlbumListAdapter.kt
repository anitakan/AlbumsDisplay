package example.albums.com.display

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AlbumListAdapter(var albumList: List<AlbumData>) : RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: AlbumListAdapter.ViewHolder, position: Int) {
        holder.bindItems(albumList.get(position))

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.album_detail, parent, false)
        return ViewHolder(v)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(album: AlbumData) {
            val textViewTitle = itemView.findViewById(R.id.title_text) as TextView
            textViewTitle.text = album.title
        }
    }
}