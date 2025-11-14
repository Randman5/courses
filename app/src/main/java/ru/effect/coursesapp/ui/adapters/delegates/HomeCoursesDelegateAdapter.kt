package ru.effect.coursesapp.ui.adapters.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.effect.coursesapp.R
import ru.effect.coursesapp.domain.models.CourseModel
import ru.effect.coursesapp.ui.adapters.DelegateAdapter
import ru.effect.coursesapp.ui.utils.DateUtils

class HomeCoursesDelegateAdapter(
    val addToFavorite: ((userModel: CourseModel) -> Unit)? = null,
    val deleteFromFavorite: ((userModel: CourseModel) -> Unit)? = null
) : DelegateAdapter<CourseModel> {

    override fun isForViewType(items: List<*>, position: Int): Boolean {
        return items[position] is CourseModel
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_item, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(item: CourseModel, holder: RecyclerView.ViewHolder) {
        (holder as CourseViewHolder).bind(item)
    }

    inner class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.title)
        private val description = view.findViewById<TextView>(R.id.description)
        private val price = view.findViewById<TextView>(R.id.pay_value)
        private val rate = view.findViewById<TextView>(R.id.rating)
        private val date = view.findViewById<TextView>(R.id.date)
        private val likeIcon = view.findViewById<ImageView>(R.id.favoriteButton)
        private val likeBtn = view.findViewById<FrameLayout>(R.id.favoriteWrapper)

        fun bind(item: CourseModel) {
            title.text = item.title
            description.text = item.text
            price.text = "${item.price} ₽"
            rate.text = item.rate
            date.text = DateUtils.formatToRussian(item.publishDate)
            likeIcon.setImageResource(getFavoriteIcon(item.hasLike))
            likeBtn.setOnClickListener {
                item.hasLike = !item.hasLike
                likeIcon.setImageResource(getFavoriteIcon(item.hasLike))
                if (item.hasLike) {
                    addToFavorite?.invoke(item)
                } else {
                    deleteFromFavorite?.invoke(item)
                }
            }
        }

        private fun getFavoriteIcon(hasLike: Boolean): Int {
            return if (hasLike) {
                R.drawable.bookmark_fill
            } else {
                R.drawable.bookmark
            }
        }
    }
}