
package com.example.todoappwithlogin.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todoappwithlogin.R
import com.example.todoappwithlogin.databinding.StatisticsFragBinding
import com.example.todoappwithlogin.util.setupRefreshLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Main UI for the statistics screen.
 */
class StatisticsFragment : Fragment() {

    private lateinit var viewDataBinding: StatisticsFragBinding

    private val statisticsVModel : StatisticsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(
            inflater, R.layout.statistics_frag, container,
            false
        )
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.viewmodel = statisticsVModel
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        this.setupRefreshLayout(viewDataBinding.refreshLayout)
    }
}
