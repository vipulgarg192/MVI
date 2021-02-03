package com.app.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.mvi.model.User
import com.app.mvi.network.ApiHelpImp
import com.app.mvi.network.RetrofitBuilder
import com.app.mvi.ui.main.MainAdapter
import com.app.mvi.ui.main.MainIntent
import com.app.mvi.ui.main.MainStates
import com.app.mvi.ui.main.MainViewModel
import com.app.mvi.util.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())
private lateinit var recyclerView : RecyclerView
private lateinit var buttonFetchUser : Button
private lateinit var progressBar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        buttonFetchUser = findViewById(R.id.buttonFetchUser)
        progressBar = findViewById(R.id.progressBar)
        setupUI()
        setupViewModel()
        setupClicks()
        observeViewModel()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }

    private fun setupClicks() {
        buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelpImp(
                    RetrofitBuilder.apiServices
                )
            )
        ).get(MainViewModel::class.java)
    }

    private fun observeViewModel(){
       lifecycleScope.launch {
           mainViewModel.state.collect(){
               when(it){
                   is MainStates.Idle -> {

                   }
                   is MainStates.Loading -> {
                       buttonFetchUser.visibility = View.GONE
                       progressBar.visibility = View.VISIBLE

                   }
                   is MainStates.Users -> {
                       progressBar.visibility = View.GONE
                       buttonFetchUser.visibility = View.GONE
                       renderList(it.user)
                   }
                   is MainStates.Error -> {
                       progressBar.visibility = View.GONE
                       buttonFetchUser.visibility = View.VISIBLE
                       Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                   }
               }
           }
       }
    }

    private fun renderList(users: List<User>) {
        recyclerView.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }

}