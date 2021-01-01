package com.faranjit.clean.core.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.faranjit.clean.core.Navigator
import com.faranjit.clean.core.vm.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by Bulent Turkmen on 28.03.2020.
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewDataBinding> : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    protected lateinit var viewDataBinding: VB

    protected lateinit var vm: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, provideLayoutId())
        viewDataBinding.lifecycleOwner = this

        vm = provideViewModel()

        vm.spinnerLiveData.observe(this, Observer {
            showLoading(it)
        })

        initObservers()
    }

    open fun initObservers() {
        vm.unauthorizedLiveData.observe(this, Observer {
            if (it.getContentIfNotHandled() == true) {
                navigator.openAuthorizationScreen(this)
            }
        })
    }

    open fun showDialog(title: String? = null, message: String, task: (() -> Unit)? = null) {
        // TODO display dialog
        task?.invoke()
    }

    /**
     * Gets the resId of the layout to be inflated
     */
    abstract fun provideLayoutId(): Int

    /**
     * Gets the viewMode bounded to this activity.
     */
    abstract fun provideViewModel(): VM

    /**
     * Show progress bar while api request running and dismiss at the end.
     */
    abstract fun showLoading(visible: Boolean)
}