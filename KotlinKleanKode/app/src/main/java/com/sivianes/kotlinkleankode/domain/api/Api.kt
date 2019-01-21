package com.sivianes.kotlinkleankode.domain.api

import com.sivianes.kotlinkleankode.domain.model.POTD
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    //GET https://api.nasa.gov/neo/rest/v1/feed?start_date=START_DATE&end_date=END_DATE&api_key=API_KEY

    //start_date	YYYY-MM-DD	none	Starting date for asteroid search
    //end_date	YYYY-MM-DD	7 days after start_date	Ending date for asteroid search
    //api_key	string	DEMO_KEY	api.nasa.gov key for expanded usage

//    @GET("neo/rest/v1/feed")
//    fun hitCountCheck(@Query("start_date") action: String,
//                      @Query("end_date") format: String,
//                      @Query("api_key") list: String,
//            Observable<Content>


    @GET("planetary/apod")
    fun getPOTD(@Query("date") date: String,
                      @Query("hd") hd: Boolean,
                      @Query("api_key") api_key: String):
            Observable<POTD>



    companion object {
        fun create(): Api {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://api.nasa.gov/")
                .build()

            return retrofit.create(Api::class.java)
        }
    }

}