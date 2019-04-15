package example.albums.com.display

import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import example.albums.com.display.databinding.FragmentAlbumsListBinding

class AlbumListFragment : Fragment(), AlbumsListViewModel.Callback {
    private var binding: FragmentAlbumsListBinding? = null
    private var viewModel: AlbumsListViewModel? = null
    private var adapter: AlbumListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = AlbumsListViewModel()
        viewModel!!.setCallback(this)
        binding = inflate(this.layoutInflater, R.layout.fragment_albums_list, container, false)
        binding!!.viewModel = viewModel
        binding!!.recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayout.VERTICAL, false)
        adapter = AlbumListAdapter(emptyList())
        binding!!.recyclerView.adapter = adapter
        return binding!!.root
    }

    override fun onStart() {
        super.onStart()
        viewModel?.onStart(activity!!.applicationContext)
        //Can improve this by having NetworkBroadCastReceiver listening for Network state and
        // get call back for online, offline and accordingly update ui
    }

    override fun updateUI(response: List<AlbumData>) {
        adapter?.albumList = response
        adapter?.notifyDataSetChanged()
    }

    override fun updateErrorMessage(message: String) {
        binding?.errorMessage!!.text = message
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel?.setCallback(null)
    }
}