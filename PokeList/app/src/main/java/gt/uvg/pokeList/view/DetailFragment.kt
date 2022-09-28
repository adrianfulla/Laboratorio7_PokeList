package gt.uvg.pokelist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import gt.uvg.pokeList.api.RetrofitInstance
import gt.uvg.pokeList.model.PokemonResponse
import gt.uvg.pokelist.databinding.FragmentDetailBinding
import gt.uvg.pokelist.repository.PokemonRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {
    private val PokemonID = "pokemonId"
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var pokemonI: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{pokemonI = it.getInt(PokemonID)}
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
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
                    val pokemonList = PokemonRepository().getPokemonList(response.body()!!)
                    val pokemon = pokemonList.find{it.id == pokemonI}
                    binding.textView.text= "Front"
                    Picasso.get().load(pokemon!!.imageUrlFront).into(binding.imageView2)
                    binding.textView2.text= "Back"
                    Picasso.get().load(pokemon!!.imageUrlBack).into(binding.imageView3)
                    binding.textView3.text= "Front Shiny"
                    Picasso.get().load(pokemon!!.imageUrlShinnyFront).into(binding.imageView4)
                    binding.textView4.text= "Back Shiny"
                    Picasso.get().load(pokemon!!.imageUrlShinnyBack).into(binding.imageView5)
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(requireContext(),"Error al cargar base de datos", Toast.LENGTH_LONG).show()
            }
        })


    }
}