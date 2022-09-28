package gt.uvg.pokelist.repository
import gt.uvg.pokeList.model.PokemonResponse
import gt.uvg.pokelist.model.Pokemon

class PokemonRepository {

    // Obtain pokemon list from https://pokeapi.co/
    fun getPokemonList(p: PokemonResponse): List<Pokemon> {
        val arr : MutableList<Pokemon>? = null
        for(i in p.results){
            arr!!.add(Pokemon(p.results.indexOf(i)+1,i.name))
        }
        return arr!!.toList()
    }
}