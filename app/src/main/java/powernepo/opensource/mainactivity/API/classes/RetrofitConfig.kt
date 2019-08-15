package powernepo.opensource.mainactivity.API.classes

import powernepo.opensource.mainactivity.API.interfaces.CepService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig{

    val rInstance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCepService() : CepService = rInstance.create(CepService::class.java)
}
