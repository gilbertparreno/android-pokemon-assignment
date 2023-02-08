package com.zapmap.pokemon.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding, V : BaseFragmentView<VB>> : Fragment() {

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    abstract fun onCreateView(viewBindingRoot: View): V
    abstract fun onViewCreated(
        viewHelper: V,
        savedInstanceState: Bundle?
    )

    abstract fun observeChanges()

    lateinit var rootView: V
    var isViewReinitialized = true

    private var viewBinding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = if (this::rootView.isInitialized && rootView.isViewBindingInitialized()) {
            isViewReinitialized = false
            rootView.viewBinding
        } else {
            isViewReinitialized = true
            bindingInflater.invoke(inflater, container, false)
        }
        if (!this::rootView.isInitialized || rootView.viewBinding != viewBinding) {
            rootView = onCreateView(viewBinding!!.root)
            rootView.viewBinding = viewBinding!!
        }
        return viewBinding!!.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        if (isViewReinitialized) {
            onViewCreated(rootView, savedInstanceState)
        }
        observeChanges()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }
}