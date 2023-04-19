package com.example.tabdil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tabdil.data.model.local.LocalCurrency
import com.example.tabdil.databinding.ActivityMainBinding
import com.example.tabdil.ui.CurrencyAdapter
import com.example.tabdil.ui.FavoriteAdapter
import com.example.tabdil.ui.MainViewModel
import com.example.tabdil.ui.OnFavoriteClickListener
import com.example.tabdil.ui.OnPinClickListener
import com.example.tabdil.util.ResultOf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val mainAdapter: CurrencyAdapter = CurrencyAdapter()
    private val favoriteAdapter = FavoriteAdapter()
    private lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCurrencies()

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.listCurrencies.adapter = mainAdapter

        setContentView(binding.root)

        setSupportActionBar(binding.myToolbar)

        getData()

        handelSwipeRefreshLayout()

        clickFavorite()
        clickPin()

        handleFavorite()
    }

    private fun getFavoritesName(){
        viewModel.getFavoritesName()
    }

    private fun putFavoritesDataOnUi(){
        viewModel.favoriteNamesLiveData.observe(this){
            favoriteAdapter.submitList(it)
        }
    }

    private fun handleFavorite(){
        binding.listFavoritesCurrencies.adapter = favoriteAdapter
        drawerLayout = binding.root
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun handelSwipeRefreshLayout(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getCurrencies()
            getData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun getData(){
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currencyStateFlow.collect{
                    if (it is ResultOf.Success){
                        putDataOnView(it.data)
                    }
                }
            }
        }
    }

    private fun clickFavorite(){
        mainAdapter.setFavoriteClickListener(object : OnFavoriteClickListener {
            override fun onFavoriteClick(item: LocalCurrency) {
                viewModel.updateFavoriteOrUnfavorite(item)
            }
        })
    }

    private fun clickPin(){
        mainAdapter.setPinClickListener(object : OnPinClickListener {
            override fun onPinClick(item: LocalCurrency) {
                viewModel.updatePinOrUnpin(item)
            }
        })
    }

    fun putDataOnView(data:List<LocalCurrency>){
        mainAdapter.submitList(data)
    }


    private fun connectHandler(disconnectedView: MenuItem) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                App.isConnected().collect{
                    disconnectedView.isVisible = !it
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        if (menu != null) {
            val disconnectedView: MenuItem = menu.findItem(R.id.no_connection)
            disconnectedView.isVisible = false
            connectHandler(disconnectedView)
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            getFavoritesName()
            putFavoritesDataOnUi()
            true
        } else super.onOptionsItemSelected(item)
    }

}



/*
private fun handleUiState(state: ResultOf<*>) {
        val progressBar = activity?.findViewById<ProgressBar>(R.id.progress_bar)
        val errorLayout = activity?.findViewById<TextView>(R.id.error_view)
        val resultView = activity?.findViewById<ConstraintLayout>(R.id.result_view)
        when (state) {
            is ResultOf.ErrorEmptyLocal -> {
                errorLayout?.visibility = View.VISIBLE
                progressBar?.visibility = View.INVISIBLE
                resultView?.visibility = View.INVISIBLE
                Toast.makeText(requireContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            }
            is ResultOf.LoadingEmptyLocal -> {
                errorLayout?.visibility = View.INVISIBLE
                progressBar?.visibility = View.VISIBLE
                resultView?.visibility = View.INVISIBLE
            }
            is ResultOf.Success -> {
                errorLayout?.visibility = View.INVISIBLE
                progressBar?.visibility = View.INVISIBLE
                resultView?.visibility = View.VISIBLE
            }
            is ResultOf.LoadingFillLocal -> {
                errorLayout?.visibility = View.INVISIBLE
                progressBar?.visibility = View.VISIBLE
                resultView?.visibility = View.VISIBLE
            }
            is ResultOf.ErrorFillLocal -> {
                errorLayout?.visibility = View.INVISIBLE
                progressBar?.visibility = View.INVISIBLE
                resultView?.visibility = View.VISIBLE
                Toast.makeText(requireContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show()
            }
        }
    }

}
 */