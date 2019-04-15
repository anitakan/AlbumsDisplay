package example.albums.com.display

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class VolleyStringRequest {
    fun fetchAlbumsList(url: String, context: Context, listener: Response.Listener<List<AlbumData>>, errorListener: Response.ErrorListener) {
        val jsonString = FileUtil.readFromFile(context)
        if (!jsonString.isEmpty()) {
            parseJsonResponse(jsonString, context, listener)
        } else {
            if(Util.isConnected(context)) {
                val queue = Volley.newRequestQueue(context)

                val stringReq = StringRequest(Request.Method.GET, url,
                        Response.Listener<String> { response ->
                            parseJsonResponse(response, context, listener)
                        },
                        Response.ErrorListener {
                            val volleyError = VolleyError(context.getString(R.string.please_try_after_some_time))
                            errorListener.onErrorResponse(volleyError)
                        })
                queue.add(stringReq)
            } else{
                val volleyError = VolleyError(context.getString(R.string.please_check_connection))
                errorListener.onErrorResponse(volleyError)
            }
        }
    }

    private fun parseJsonResponse(response: String, context: Context, listener: Response.Listener<List<AlbumData>>) {
        val stringResponse = response
        FileUtil.writeToFile(stringResponse, context)
        val jsonArray = JSONArray(stringResponse)
        val albumList = mutableListOf<AlbumData>()
        for (index in 0 until jsonArray.length()) {
            val jsonObject: JSONObject = jsonArray.getJSONObject(index)
            val album = AlbumData()
            album.id = jsonObject.get("id").toString().toInt()
            album.title = jsonObject.get("title").toString()
            album.userId = jsonObject.get("userId").toString().toInt()
            Log.d("abc", "title " + album.title)
            albumList.add(album)
        }
        listener.onResponse(albumList)
    }
}