package com.example.networkingwithretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.*
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity() {


    //emulating a repository
    private val nowPlaying = mutableListOf(505192,442062,300681,517814,470044,426563,463684,505192,426563,424,568709,491854,398173,445629)


    private val movies = ArrayList<MovieItem>()
    private lateinit var job: Job


    //add your own key

    private val apiKey =""

    private val retrofitService by lazy {
        RetrofitService.create(resources.getString(R.string.base_url))
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.movie_list)
        val adapter = MovieListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.setMovies(movies)
        job =  CoroutineScope(Dispatchers.IO).launch {

            for (i: Int in nowPlaying) {

                delay(500)
                fetchMovieById(i)

                withContext(Dispatchers.Main){
                    adapter.notifyDataSetChanged()

                }

            }
        }
    }


    private suspend fun fetchMovieById(id:Int){
            val request =   retrofitService.getMovieForId( id, apiKey)
            try {
                  val movie =   request.await()

                movie.let{movies.add(it)}

                Log.d("retrofit_demo","movie "+movie.toString())
                // Do something with the response.body()
            } catch (e: HttpException) {
                Log.d("retrofit_demo","exception:"+e.toString())
            } catch (e: Throwable) {
                Log.d("retrofit_demo",e.toString())
            }




    }


    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()

    }


    inner class MovieListAdapter():
        RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(){
        private var movies = emptyList<MovieItem>()

        internal fun setMovies(movies: List<MovieItem>) {
            this.movies = movies
            notifyDataSetChanged()
        }

        override fun getItemCount(): Int {

            return movies.size
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {


            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)
            return MovieViewHolder(v)
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {


            //holder.bindItems(movieList[position])

            Glide.with(this@MainActivity).load(resources.getString(R.string.picture_base_url)+movies[position].poster_path).apply( RequestOptions().override(128, 128)).into(holder.view.findViewById(R.id.poster))

            holder.view.findViewById<TextView>(R.id.title).text=movies[position].title

            holder.view.findViewById<TextView>(R.id.rating).text=movies[position].vote_average.toString()


            holder.itemView.setOnClickListener(){


                // interact with the item

            }

        }


        inner class MovieViewHolder(val view: View): RecyclerView.ViewHolder(view), View.OnClickListener{
            override fun onClick(view: View?){

                Log.d("movies2019","list tap ")
                if (view != null) {


                }


            }


        }
    }

}



