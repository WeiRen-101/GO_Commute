package tw.com.hkt.myapplication.TrafficInfo.LawSearch

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tw.com.hkt.myapplication.R

class MyAdapter(private var itemList: List<Pair<String, String>>, private var searchText: String = "") :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.textView1.text = highlightSearchText(item.first, searchText)
        holder.textView2.text = highlightSearchText(item.second, searchText)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private fun highlightSearchText(text: String, searchText: String): SpannableString {
        val spannableString = SpannableString(text)

        if (searchText.isNotEmpty()) {
            var startIndex = text.lowercase().indexOf(searchText.lowercase())
            while (startIndex != -1) {
                val endIndex = startIndex + searchText.length

                spannableString.setSpan(
                    BackgroundColorSpan(Color.YELLOW),
                    startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                spannableString.setSpan(
                    StyleSpan(android.graphics.Typeface.BOLD),
                    startIndex, endIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                startIndex = text.lowercase().indexOf(searchText.lowercase(), endIndex)
            }
        }

        return spannableString
    }

    fun updateSearchText(newSearchText: String) {
        searchText = newSearchText
        notifyDataSetChanged()
    }
}
