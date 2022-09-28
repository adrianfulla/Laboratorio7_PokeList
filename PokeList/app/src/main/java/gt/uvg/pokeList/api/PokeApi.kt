package gt.uvg.pokeList.api

import gt.uvg.pokeList.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET


interface PokeApi {

    @GET("/pokemon?limit=20")
    fun getData() : Call<PokemonResponse>

}