package com.benpike.ben_pike_final

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.FirebaseFirestore
import android.view.*
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query

//import com.firebase.ui.auth.AuthUI
//import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

        var db = FirebaseFirestore.getInstance()

        private var adapter: PlaceAdapter? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            recyclerView.setLayoutManager(LinearLayoutManager(this))

            val query = db.collection("places").orderBy("name", Query.Direction.ASCENDING)

            val options = FirestoreRecyclerOptions.Builder<Place>().setQuery(query, Place::class.java).build()
            adapter = PlaceAdapter(options)

            recyclerView.adapter = adapter
        }

        override fun onStart() {
            super.onStart()
            adapter!!.startListening()
        }

        override fun onStop() {
            super.onStop()
            adapter!!.stopListening()
        }

        private inner class PlaceViewHolder internal constructor(private val view: View) :
            RecyclerView.ViewHolder(view) {
        }

        private inner class PlaceAdapter internal constructor(options: FirestoreRecyclerOptions<Place>) :
            FirestoreRecyclerAdapter<Place,
                    PlaceViewHolder>(options) {

            override fun onBindViewHolder(p0: PlaceViewHolder, p1: Int, p2: Place) {
                p0.itemView.findViewById<TextView>(R.id.textViewName).text = p2.placeName
                p0.itemView.findViewById<TextView>(R.id.textViewURL).text = p2.url.toString()
                p0.itemView.findViewById<TextView>(R.id.buttonUrl).setOnClickListener {
                    val url = p2.url.toString()
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    startActivity(i)
                }
                p0.itemView.findViewById<TextView>(R.id.textViewLat).text = p2.placeLat.toString()
                p0.itemView.findViewById<TextView>(R.id.textViewLong).text = p2.placeLong.toString()
                    p0.itemView.findViewById<TextView>(R.id.buttonMaps).setOnClickListener {
                    val i = Intent(applicationContext, MapsActivity::class.java)
                    startActivity(i)
                }

            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
                // when creating, instantiate the item_artist.xml template (only happens once)
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
                return PlaceViewHolder(view)
            }
        }
    }