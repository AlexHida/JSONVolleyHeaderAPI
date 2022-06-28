package com.example.jsonvolleyheaderapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.covid19api.com/dayone/country/south-africa/status/confirmed"
        val mostrarTexto = findViewById<TextView>(R.id.mtxtLista)

        var jsonRQ = object : JsonArrayRequest(
            Method.GET, url, null,
            { response ->
                var listCasos = ""
                for (i in 0 until response.length()){
                    val casos: JSONObject = response.getJSONObject(i)
                    listCasos = listCasos +
                            "\n"+"País: "+ casos.getString("Country")+"\n"+
                            "Casos Detectados: "+ casos.getString("Cases")+"\n"+
                            "Fecha: "+ casos.getString("Date")+"\n"
                }
                mostrarTexto.text = listCasos
            },
            { error -> mostrarTexto.text = "Error!!"
                error.printStackTrace()
            }) {
            override fun getHeaders(): Map<String, String>? {
                val headers = HashMap<String, String>()
                headers.put("X-Request-Id", "4ae9268e-65cd-4416-83bb-8ae521eaf438")
                return headers
            }
        }
        queue.add(jsonRQ)
    }
    fun botonRegresar(view: View){
        onBackPressed();
    }
}