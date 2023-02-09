package com.zapmap.pokemon.core.base

import androidx.viewbinding.ViewBinding

abstract class BaseFragmentViewHandler<VB : ViewBinding> {
    lateinit var viewBinding: VB
    abstract fun setUpView()
    fun isViewBindingInitialized() = this::viewBinding.isInitialized
}