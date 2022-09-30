package gt.uvg.pokelist.api

import gt.uvg.pokelist.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET


interface PokeApi {

    @GET("/pokemon?limit=20")
    fun getData() : Call<PokemonResponse>

}