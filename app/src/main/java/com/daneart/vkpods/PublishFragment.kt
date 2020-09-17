package com.daneart.vkpods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.publish_fragment.*

class PublishFragment : Fragment() {

    companion object {
        fun newInstance() = PublishFragment()
    }

    private lateinit var viewModel: PublishViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.publish_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PublishViewModel::class.java)
        // TODO: Use the ViewModel
        btn_publish.setOnClickListener {
            it.findNavController().navigate(R.id.action_publishFragment_to_finishFragment)
        }
    }

}