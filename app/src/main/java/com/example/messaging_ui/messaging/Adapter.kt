import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.messaging_ui.R
import com.example.messaging_ui.utils.Utils
import kotlinx.android.synthetic.main.incoming_message.view.*

data class Message(val fromId: String,
                   val toId: String,
                   val senderName: String,
                   val receiverName:String,
                   val message: String,
                   val productId:String,
                   val time:String,
                   val fromDeviceId:String?)

class MyViewHolder(v: View): RecyclerView.ViewHolder(v)

class Adapter (private val userId:String, private val elements: MutableList<Message>): RecyclerView.Adapter<MyViewHolder>() {
//MutableList<Message> 是 Kotlin 中的一种类型，表示一个可变的列表（List）或数组，其中包含的元素的类型是 Message
//RecyclerView.Adapter<MyViewHolder>() 是 Kotlin 中的一种类型，表示一个可变的列表（List）或数组，其中包含的元素的类型是 MyViewHolder
    private val viewTypeMessageSent = 1
    private val viewTypeMessageReceived = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//onCreateViewHolder方法在创建新的ViewHolder时被调用。根据子项视图类型创建不同的ViewHolder。
// 在这里，根据viewType的值来判断是发送的消息还是接收的消息，并根据不同的情况使用不同的布局文件创建ViewHolder
        val layoutInflater: LayoutInflater
        var cellForRow: View? = null

        if( viewType == viewTypeMessageSent ) {
            layoutInflater = LayoutInflater.from(parent.context)
            cellForRow =
                layoutInflater.inflate(R.layout.outgoing_message, parent, false)
        }
        else if ( viewType == viewTypeMessageReceived ){
            layoutInflater = LayoutInflater.from(parent.context)
            cellForRow =
                layoutInflater.inflate(R.layout.incoming_message, parent, false)
        }

        return MyViewHolder(cellForRow!!)

    }

    override fun getItemCount(): Int {
        return elements.size
    }

    override fun getItemViewType(position: Int): Int {
//getItemViewType方法根据子项的位置(position)返回子项的视图类型。
// 通过比较消息的发送者ID(fromId)是否等于当前用户的ID(userId)，确定是发送的消息还是接收的消息。
        val message = elements[position]

        return if( message.fromId == userId ) viewTypeMessageSent
        else viewTypeMessageReceived

    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {

        val parsedTime = Utils().parseTimestamp(elements[pos].time)
        //这一行使用Utils类中的parseTimestamp方法来解析消息的时间戳(time)，并将结果保存在parsedTime变量中。

        holder.itemView.text_message_body.text = elements[pos].message
        holder.itemView.text_message_time.text = parsedTime[1]

        if( holder.itemViewType == viewTypeMessageReceived){

            holder.itemView.text_message_name.text = elements[pos].senderName

            val imgView = holder.itemView.image_message_profile

            val mContext = holder.itemView.context

            holder.itemView.setOnClickListener {

                Toast.makeText(mContext, "To Implement", Toast.LENGTH_SHORT).show()

            }
        }
    }
}

