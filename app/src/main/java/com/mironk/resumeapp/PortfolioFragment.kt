package com.mironk.resumeapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_portfolio.*
import kotlinx.android.synthetic.main.portfolio_items.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [PortfolioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PortfolioFragment : Fragment() {

    companion object{
       const val INTENT_PARCELABLE = "OBJECT_INTENT"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val portfolioView = inflater.inflate(R.layout.fragment_portfolio, container, false)

        val portfolioList = generatePortfolioList(2)



        val recyclerView = portfolioView.findViewById<RecyclerView>(R.id.rv_recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(portfolioView?.context)
        recyclerView.adapter = PortfolioAdapter(this,portfolioList){
            val intent = Intent( context, PhotoDetailActivity::class.java)
            intent.putExtra(INTENT_PARCELABLE, it)
            startActivity(intent)
        }

        return portfolioView





    }



    private fun generatePortfolioList(size: Int): List<PortfolioList> {
        val list = ArrayList<PortfolioList>()

        for (i in 0 until size) {

            val title = when (i % 2) {
                0 -> getString(R.string.resume_title)
                1 -> getString(R.string.tasktimer_title)
                else -> ""
            }

            val description = when (i % 2) {
                0 -> getString(R.string.resume_Description)
                1 -> getString(R.string.tasktimer_desc)
                else -> ""
            }

            val drawable = when (i % 2) {
                0 -> R.drawable.calculator_app
                1 -> R.drawable.task_timer
                else -> 0
            }

            val item = PortfolioList(title, description, drawable)
            list += item

        }


        return list



    }

    class PortfolioAdapter(val context: PortfolioFragment,
                           private val portfolioList: List<PortfolioList>,
                           private val listener: (PortfolioList) -> Unit) :
        RecyclerView.Adapter<PortfolioAdapter.PortfolioViewHolder>() {


        class PortfolioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val title = itemView.findViewById<TextView>(R.id.appTitle)
            private val desc = itemView.findViewById<TextView>(R.id.appDesc)
            private val image = itemView.findViewById<ImageView>(R.id.appPhoto)

             fun bindItems(portfolioList: PortfolioList, listener: (PortfolioList) -> Unit) {

                 title.text = portfolioList.title
                 desc.text = portfolioList.description
                 image.setImageResource(portfolioList.imageSrc)
                 itemView.setOnClickListener { listener(portfolioList) }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.portfolio_items, parent, false)

            return PortfolioViewHolder(view)
        }

        override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
            holder.bindItems(portfolioList[position], listener)
        }

        override fun getItemCount(): Int = portfolioList.size

    }




}