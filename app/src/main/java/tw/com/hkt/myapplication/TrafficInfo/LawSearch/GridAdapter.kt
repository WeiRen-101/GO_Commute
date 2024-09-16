//package tw.com.hkt.myapplication.TrafficInfo.LawSearch
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.TextView
//
//class GridAdapter(context: Context, private val data: Array<String>) :
//    ArrayAdapter<String>(context, 0, data) {
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var view = convertView
//        if (view == null) {
//            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
//        }
//
//        val textView = view!!.findViewById<TextView>(android.R.id.text1)
//        textView.text = data[position]
//
//        return view
//    }
//}
