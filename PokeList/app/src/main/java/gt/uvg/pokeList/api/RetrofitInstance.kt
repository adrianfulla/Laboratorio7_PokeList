package gt.uvg.pokeList.api

import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitInstance {

    companion object {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        data class UserEntity(
            @field:Json(name = "id") val id: String,
            @field:Json(name = "name") val name: String
        )

        val api by lazy{
            retrofit.create(PokeApi::class.java)
        }
    }
}