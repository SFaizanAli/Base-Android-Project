package com.example.baseandroidproject.ui.main.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowInsets

import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.model.PostResponseModel
import com.example.baseandroidproject.databinding.ActivityPostsBinding

import com.example.baseandroidproject.ui.base.BaseActivity
import com.example.baseandroidproject.ui.main.adapter.RecyclerViewUserHistoryAdapter
import com.example.baseandroidproject.ui.main.viewmodel.PostsViewModel
import com.example.baseandroidproject.utils.*

class PostsActivity : BaseActivity() {


    private lateinit var binding: ActivityPostsBinding
    private lateinit var context: Context

    private var loadingDialog: Dialog? = null
    private var musicAdapter: RecyclerViewUserHistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        window.insetsController?.hide(WindowInsets.Type.statusBars())
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        val postsViewModel = ViewModelProvider(this)[PostsViewModel::class.java]

        context = this@PostsActivity
        loadingDialog = Common.loadingDialog(this)

        postsViewModel.getPostsResultsItems()


        postsViewModel.postResponse.observe(this) { response ->
            when (response) {
                is Resource.Success<*> -> {
                    loadingDialog?.dismiss()
                    response.data?.let {

                        if (it.size > 0) setRecyclerView(it) else setZeroState()
                    }
                }
                is Resource.Error<*> -> {
                    loadingDialog?.dismiss()
                    response.message?.let {
                        toast(it)
                    }
                }
                is Resource.Loading<*> -> loadingDialog?.show()
            }
        }
    }


    private fun setZeroState() {
        musicAdapter?.clearList()
        binding.rvSongs.gone()
        binding.tvNoSongs.visible()
        binding.tvNoSongs.text = resources.getString(R.string.no_result)
    }


    private fun setRecyclerView(it: PostResponseModel) {

        binding.tvNoSongs.gone()

        binding.rvSongs.layoutManager = LinearLayoutManager(this)
        musicAdapter = RecyclerViewUserHistoryAdapter(it)
        binding.rvSongs.adapter = musicAdapter

        musicAdapter?.setDataList(
            it
        )

    }

}