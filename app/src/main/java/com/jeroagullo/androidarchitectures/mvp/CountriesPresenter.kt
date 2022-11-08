package com.jeroagullo.androidarchitectures.mvp

import android.util.Log
import com.jeroagullo.androidarchitectures.model.CountriesService
import com.jeroagullo.androidarchitectures.mvc.CountriesController
import com.jeroagullo.androidarchitectures.mvc.MVCActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CountriesPresenter (view: View) {

    val TAG: String = CountriesController::class.java.name

    private var view: View
    private var service: CountriesService

    init{
        this.view = view
        service = CountriesService()
        fetchCountries()
    }

    private fun fetchCountries(){
        service.getCountries()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value ->
                    var countryNames: MutableList<String> = mutableListOf<String>()
                    for (country in value) {
                        countryNames.add(country.countryName)
                    }
                    // Update the view
                    view.setValues(countryNames)
                },
                {error ->
                    Log.i(TAG, "error: $error")
                    view.onError()
                }
            )
    }

    fun onRefresh() {
        fetchCountries()
    }

    interface View {
        fun setValues(countries: List<String>)
        fun onError()
    }

}