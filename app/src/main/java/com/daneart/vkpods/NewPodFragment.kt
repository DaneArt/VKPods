package com.daneart.vkpods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.new_pod_fragment.*

class NewPodFragment : Fragment() {

    companion object {
        fun newInstance() = NewPodFragment()
    }

    private lateinit var viewModel: NewPodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_pod_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewPodViewModel::class.java)
        // TODO: Use the ViewModel

        button2.setOnClickListener{
            it.findNavController().navigate(R.id.action_newPodFragment_to_editFragment)

        }
    }

}