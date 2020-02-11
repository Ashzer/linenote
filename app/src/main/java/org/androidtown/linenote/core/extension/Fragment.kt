package org.androidtown.linenote.core.extension

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.androidtown.linenote.core.platform.BaseFragment

inline fun FragmentManager.inTransaction(func : FragmentTransaction.() -> FragmentTransaction) =
    beginTransaction().func().commit()

inline fun <reified T : ViewModel> Fragment.viewModel(factory: ViewModelProvider.Factory, body: T.() -> Unit): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun BaseFragment.close() = fragmentManager?.popBackStack()

//val BaseFragment.viewContainer: View get() = (activity as BaseActivity).aLayout_fragmentContainer

val BaseFragment.appContext: Context get() = activity?.applicationContext!!