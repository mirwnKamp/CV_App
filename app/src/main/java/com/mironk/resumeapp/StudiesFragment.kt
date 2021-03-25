package com.mironk.resumeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class StudiesFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val studiesView = inflater.inflate(R.layout.fragment_studies, container, false)
        val studiesList = generateStudiesList(2)

        /**
         * If you want more items in the list
         * you change the size you want
         * and go to generateStudiesList
         *  **/

        val recyclerView = studiesView.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(studiesView?.context)
        recyclerView.adapter = StudiesAdapter(studiesList)


        return studiesView


    }




    private fun generateStudiesList(size: Int): List<StudiesList> {
        val list = ArrayList<StudiesList>()

        /**
         * Change the When size as many as you
         * have in the generatedStudiesList size
         * and add more lines with the strings you want.
         * **/

        for (i in 0 until size) {

            val title = when (i % 2) {
                0 -> getString(R.string.courses)
                1 -> getString(R.string.college)
                else -> ""
            }

            val description = when (i % 2) {
                0 -> getString(R.string.courses_cert)
                1 -> getString(R.string.college_desc)
                else -> ""
            }

            val item = StudiesList(title, description)
            list += item

        }


        return list

    }


    class StudiesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView1: TextView = itemView.findViewById(R.id.cv_title)
        var textView2: TextView = itemView.findViewById(R.id.cv_desc)
    }

    class StudiesAdapter(private var studiesList: List<StudiesList>) :
        RecyclerView.Adapter<StudiesViewHolder>() {

        override fun getItemCount() = studiesList.size

        override fun onBindViewHolder(holder: StudiesViewHolder, position: Int) {
            val currentItems = studiesList[position]

            holder.textView1.text = currentItems.title
            holder.textView2.text = currentItems.desc
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudiesViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.studies_items,
                parent,
                false
            )

            return StudiesViewHolder(itemView)
        }

    }


}
