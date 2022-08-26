package com.example.test2.app.ui.fragments.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2.app.R
import com.example.test2.app.databinding.FragmentHomeBinding
import com.example.test2.app.ui.activities.MainActivity
import com.example.test2.app.ui.fragments.home.adapter.HomeRecyclerAdapter
import com.example.test2.app.ui.base.BaseFragment
import com.example.test2.util.addLinearDividerDecoration
import com.example.test2.util.ifNotEmpty
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeVM>() {

    private var binding: FragmentHomeBinding? = null

    override val vm: HomeVM by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@HomeFragment
            vm = this@HomeFragment.vm
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        addLiveDataObservers()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    /**
     * Perform initial setup for the views bound to this Fragment.
     */
    private fun setupViews() = binding?.run {
        // Setup the RecyclerView.
        homeRecycler.layoutManager = LinearLayoutManager(root.context)
        homeRecycler.adapter = HomeRecyclerAdapter(this@HomeFragment.vm)
        homeRecycler.addLinearDividerDecoration(R.drawable.home_recycler_item_divider)

        // Keep track of the search input so that the LiveData can correctly reflect the input.
        homeSearchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                this@HomeFragment.vm.searchText.value = s?.toString()
            }
        })

        // Display the last search keyword typed by the user into the input field.
        this@HomeFragment.vm.getLastSavedSearch()?.ifNotEmpty {
            homeSearchInput.text = SpannableStringBuilder(it)
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun addLiveDataObservers() {
        vm.event.observe(this) { it?.let { event ->
            (activity as? MainActivity)?.onNavigationEvent(event)
        }}
    }

}