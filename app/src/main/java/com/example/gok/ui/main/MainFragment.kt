package com.example.gok.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gok.R
import com.example.gok.data.model.Cash
import com.example.gok.data.model.MainResponse
import com.example.gok.data.model.Product
import com.example.gok.data.model.Spotlight
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel.states.observe(viewLifecycleOwner, Observer { state ->
            when(state) {
                is MainViewState.ShowResponse -> showInfos(state.mainResponse)
                is MainViewState.ShowError -> showError(state.error)
            }
        })

        mainViewModel.loadingStates.observe(viewLifecycleOwner, Observer { loadingState ->
            when (loadingState) {
                is LoadingViewState.ShowLoading -> showHideLoading(loadingState.isLoading)
            }
        })

        mainViewModel.getMainResponse()

    }

    private fun showHideLoading(isShow: Boolean) {
        val progressBar = view?.findViewById<ProgressBar>(R.id.loading)

        if (isShow) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    private fun showInfos(mainResponse: MainResponse) {
        showSpotlightList(mainResponse.spotlight)
        showProductsList(mainResponse.products)
        showDigioInfos(mainResponse.cash)

        val infosLayout = view?.findViewById<ConstraintLayout>(R.id.infosLayout)
        infosLayout?.visibility = View.VISIBLE
    }

    private fun showSpotlightList(spotlightList: ArrayList<Spotlight>) {
        val recyclerViewSpotlight = view?.findViewById<RecyclerView>(R.id.recyclerSpotlight)

        val myAdapter = RecyclerSpotlightAdapter(spotlightList)

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        recyclerViewSpotlight?.let {recycler ->
            with(recycler) {
                layoutManager = linearLayoutManager
                adapter = myAdapter
                setHasFixedSize(false)
            }
        }
    }

    private fun showProductsList(productsList: ArrayList<Product>) {
        val recyclerViewProducts = view?.findViewById<RecyclerView>(R.id.recyclerProducts)

        val myAdapter = RecyclerProductsAdapter(productsList)

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        recyclerViewProducts?.let {recycler ->
            with(recycler) {
                layoutManager = linearLayoutManager
                adapter = myAdapter
                setHasFixedSize(false)
            }
        }
    }

    private fun showDigioInfos(cash: Cash) {
        val textView = view?.findViewById<TextView>(R.id.textViewDigioCashTitle)
        val imageView = view?.findViewById<ImageView>(R.id.imageViewDigioCash)

        textView?.text = cash.title
        Picasso.get().load(cash.bannerURL).into(imageView)
    }

    private fun showError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

}