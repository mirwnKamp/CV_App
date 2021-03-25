package com.mironk.resumeapp

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val homeView =  inflater.inflate(R.layout.fragment_home, container, false)
        val homeList = generateHomeList(4)

//        (activity as MainActivity?)!!.fragmentMethod()

        val recyclerView = homeView.findViewById<RecyclerView>(R.id.home_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(homeView?.context)
        recyclerView.adapter = HomeAdapter(homeList)

        return homeView


    }

    private fun generateHomeList(size: Int): List<HomeList> {
        val list = ArrayList<HomeList>()



        for(i in 0 until size) {

            val title = when (i % 4) {
                0 -> getString(R.string.profile)
                1 -> getString(R.string.soft_skills)
                2 -> getString(R.string.lang_skills)
                3 -> getString(R.string.tech_skills)
                else -> ""
            }

            val description = when (i % 4) {
                0 -> getString(R.string.profile_desc)
                1 -> getString(R.string.soft_skills_desc)
                2 -> getString(R.string.lang_skills_desc)
                3 -> getString(R.string.tech_skills_desc)
                else -> ""
            }

            val item = HomeList(title, description)
            list += item

        }

        return list

    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textTitle: TextView = itemView.findViewById(R.id.titleHome)
        var textDesc:  TextView = itemView.findViewById(R.id.descHome)

    }

    class HomeAdapter(private val homeList: List<HomeList>) : RecyclerView.Adapter<HomeViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_items, parent,false)

            return HomeViewHolder(itemView)
        }


        override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
            val currentItems = homeList[position]

            holder.textTitle.text = currentItems.title
            holder.textDesc.text = currentItems.description

        }

        override fun getItemCount() = homeList.size
    }
}