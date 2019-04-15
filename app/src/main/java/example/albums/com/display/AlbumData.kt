package example.albums.com.display

class AlbumData : Comparable<AlbumData> {

    var userId: Int = 0
    var id: Int = 0
    var title: String? = null

    override fun compareTo(other: AlbumData): Int {
        return this.title!!.compareTo(other.title!!)
    }
}
