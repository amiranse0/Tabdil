package com.example.tabdil.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tabdil.R
import com.example.tabdil.data.model.local.LocalCurrency
import com.example.tabdil.databinding.FragmentMainBinding
import com.example.tabdil.util.ResultOf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment: Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val mainAdapter: CurrencyAdapter = CurrencyAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrencies()

        initValues(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currencyStateFlow.collect{
                    handleUiState(it)
                    if (it is ResultOf.Success){
                        putDataOnView(it.data)
                    }
                }
            }
        }
    }

    fun initValues(view: View){
        binding = FragmentMainBinding.bind(view)

        binding.listCurrencies.adapter = mainAdapter
    }

    fun putDataOnView(data:List<LocalCurrency>){
        mainAdapter.submitList(data)
    }

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