package com.skylightdevelopers.android.moviemaasala.core.ui.base


import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.skylightdevelopers.android.moviemaasala.core.di.base.Injectable
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

// Easy to switch base fragment in future
typealias BaseFragment = DaggerFragment

/**
 * Base fragment providing Dagger support and [ViewModel] support
 */
abstract class DaggerFragment : Fragment(), Injectable {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

//    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}
