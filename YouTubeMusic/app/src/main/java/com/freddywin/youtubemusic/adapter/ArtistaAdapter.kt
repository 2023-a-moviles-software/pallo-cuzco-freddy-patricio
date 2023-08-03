import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freddywin.youtubemusic.ClaseCanciones
import com.freddywin.youtubemusic.R
import com.squareup.picasso.Picasso

class ArtistaAdapter(
    val artistasLits: ArrayList<ClaseCanciones>,
    val context: Context
) : RecyclerView.Adapter<ArtistaAdapter.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_CATEGORY = 0
        private const val VIEW_TYPE_SONG = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_artistas, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artista = artistasLits[position]
        holder.bind(artista)
    }

    override fun getItemCount(): Int {
        return artistasLits.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nuevoTitulo: TextView = itemView.findViewById(R.id.idArtistaTitulo)
        private val nuevoNombreArtista: TextView = itemView.findViewById(R.id.idNombreArtista)
        private val nuevoImagenView: ImageView = itemView.findViewById(R.id.idImagenes)

        fun bind(artista: ClaseCanciones) {
            nuevoTitulo.text = artista.titulo
            nuevoNombreArtista.text = artista.artista
            Picasso.get().load(artista.nuevoImagen).into(nuevoImagenView)
        }
    }
}
