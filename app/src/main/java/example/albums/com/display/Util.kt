package example.albums.com.display

import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.NonNull

class Util {
    companion object {
        fun isConnected(@NonNull context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
        }
    }
}