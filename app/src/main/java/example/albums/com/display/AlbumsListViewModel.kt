package example.albums.com.display

import android.content.Context
import android.databinding.ObservableBoolean
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import com.android.volley.Response
import com.android.volley.VolleyError

class AlbumsListViewModel : Response.Listener<List<AlbumData>>, Response.ErrorListener {

    @NonNull
    var showProgress = ObservableBoolean(true)
    @NonNull
    val showError = ObservableBoolean(false)
    @Nullable
    private var callback: Callback? = null

    interface Callback {
        fun updateUI(response: List<AlbumData>)
        fun updateErrorMessage(message: String)
    }

    fun onStart(context: Context) {
        val volleyStringRequest = VolleyStringRequest()
        volleyStringRequest.fetchAlbumsList(albumsUrl, context, this, this)
    }

    companion object {
        const val albumsUrl = "https://jsonplaceholder.typicode.com/albums"
    }

    override fun onErrorResponse(error: VolleyError) {
        //should handle offline and other error scenarios
        showProgress.set(false)
        showError.set(true)
        callback?.updateErrorMessage(error.message!!)
    }

    override fun onResponse(response: List<AlbumData>?) {
        showProgress.set(false)
        showError.set(false)
        if (response != null) {
            val albumList = mutableListOf<AlbumData>()
            albumList.addAll(response)
            albumList.sort()
            callback?.updateUI(albumList)
        }
    }

    fun setCallback(callback: Callback?) {
        this.callback = callback
    }
}
