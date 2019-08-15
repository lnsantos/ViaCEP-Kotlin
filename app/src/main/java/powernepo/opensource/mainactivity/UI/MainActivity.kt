package powernepo.opensource.mainactivity.UI

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.ContextMenu
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import kotlinx.android.synthetic.main.dialog_cep.seuCEP

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_cep.*
import powernepo.opensource.mainactivity.pojo.Cep
import powernepo.opensource.mainactivity.API.classes.RetrofitConfig
import powernepo.opensource.mainactivity.API.interfaces.CepService
import powernepo.opensource.mainactivity.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            dialogSearch(this)
        }

    }

    private fun dialogSearch(c: Context){
        val builder = AlertDialog.Builder(c)
        builder.setView(View.inflate(c,R.layout.dialog_cep, null))
        builder.setPositiveButton("BUSCAR CEP"){ dialogInterface: DialogInterface, i: Int ->
            var cepInformado = seuCEP.text.toString()

            if(cepInformado == null) cepInformado = "13917112"
            if(cepInformado.isBlank()) Toast.makeText(c,"Informe o CEP", Toast.LENGTH_SHORT).show()
            else carregaDados(seuCEP.text.toString())
        }.show()
    }

    private fun carregaDados(cep: String ) : Unit =  RetrofitConfig()
        .getCepService()
        .getCep(cep).enqueue(object : Callback<Cep>{
            override fun onFailure(call: Call<Cep>, t: Throwable) {
                Toast.makeText(applicationContext,"INVALID CEP", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Cep>, response: Response<Cep>) {
                ruaEncontrada.setText(response.body()!!.logradouro.toString())
                complementoEncontrada.setText(response.body()!!.complemento.toString())
                cidadeEncontrada.setText(response.body()!!.bairro.toString())

                Toast.makeText(applicationContext,"CEP ENCONTRADO", Toast.LENGTH_SHORT).show()
            }
        })



}

