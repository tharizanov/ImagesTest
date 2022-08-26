package com.example.test2.app.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

/**
 * Base [Fragment] class to be inherited by Fragments coupled with an extension of [BaseViewModel].
 */
abstract class BaseFragment<VM : BaseViewModel> : Fragment() {

    protected abstract val vm: VM

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(vm)
    }

}