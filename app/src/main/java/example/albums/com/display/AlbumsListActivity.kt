package example.albums.com.display

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class AlbumsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums_list)
        setTitle(R.string.app_name)
    }
}
