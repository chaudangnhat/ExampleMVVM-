package com.gauravgoyal.mvvm_with_testing.features.userinfo.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gauravgoyal.mvvm_with_testing.features.userinfo.di.Injection
import com.gauravgoyal.mvvm_with_testing.R
import com.gauravgoyal.mvvm_with_testing.features.userinfo.viewmodel.UserViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_user_info.*

class UserInfoFragment : Fragment() {

    companion object {
        private val TAG = UserInfoFragment::class.java.simpleName
    }

    lateinit var viewModelFactory: UserViewModel.ViewModelFactory

    private lateinit var viewModel: UserViewModel

    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view: View = inflater.inflate(R.layout.fragment_user_info, container, false)

        //button update data user
        val btnUpdateUser = view.findViewById<Button>(R.id.update_user_button)
        btnUpdateUser.setOnClickListener { updateUserName() }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = Injection.provideViewModelFactory(activity!!)

        this.viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(UserViewModel::class.java)

        disposable.add(viewModel.userName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ this.user_name.text = it },
                        { error -> Log.e(TAG, "Unable to get username", error) }))
    }

    override fun onStop() {
        super.onStop()
        // clear all the subscription
        disposable.clear()
    }

    private fun updateUserName() {
        val userName = user_name_input.text.toString()
        // Disable the update button until the user name update has been done
        update_user_button.isEnabled = false
        // Subscribe to updating the user name.
        // Enable back the button once the user name has been updated
        disposable.add(viewModel.updateUserName(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ update_user_button.isEnabled = true },
                        { error -> Log.e(TAG, "Unable to update username", error) }))
    }
}