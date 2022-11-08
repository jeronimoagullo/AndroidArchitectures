package com.jeroagullo.androidarchitectures.mvc

import android.util.Log
import com.jeroagullo.androidarchitectures.model.CountriesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CountriesController constructor(view: MVCActivity) {

    val TAG: String = CountriesController::class.java.name

    private var view: MVCActivity
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

}