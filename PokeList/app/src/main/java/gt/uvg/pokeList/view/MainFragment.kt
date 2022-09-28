package gt.uvg.pokelist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gt.uvg.pokeList.api.RetrofitInstance
import gt.uvg.pokeList.model.PokemonResponse
import gt.uvg.pokelist.databinding.FragmentMainBinding
import gt.uvg.pokelist.repository.PokemonRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment: Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RetrofitInstance.api.getData().enqueue(object: Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val pokeLista = response.body()!!

                    val pokemonList = PokemonRepository().getPokemonList(pokeLista)
                    recyclerView = binding.recyclerView
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = PokemonListAdapter(pokemonList)
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(requireContext(),"Error al cargar base de datos", Toast.LENGTH_LONG).show()
            }


        })
    }
}