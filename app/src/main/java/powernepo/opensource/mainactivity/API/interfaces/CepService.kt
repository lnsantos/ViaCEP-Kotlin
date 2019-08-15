package powernepo.opensource.mainactivity.API.interfaces

import powernepo.opensource.mainactivity.pojo.Cep
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CepService{

    @GET("{cep}/json")
    fun getCep(@Path("cep") cep: String): Call<Cep>

}