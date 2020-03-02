package sen.com.uicomponent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.slider_fragment.*
import sen.com.uicomponent.utils.ViewUtils.visibleOrGone

class SliderFragment(
    private val title: String?,
    private val description: String?,
    private val imageUrl: String? = null,
    @DrawableRes private val resImage: Int = 0
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.slider_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSliderTitle.text = title ?: ""
        visibleOrGone(!title.isNullOrEmpty(), tvSliderTitle)

        tvSliderDescription.text = description ?: ""
        visibleOrGone(!description.isNullOrEmpty(), tvSliderDescription)

        if (imageUrl != null) {
            Glide.with(this.requireContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(ivSliderImage)
        } else {
            Glide.with(this.requireContext())
                .load(resImage)
                .placeholder(R.drawable.ic_placeholder)
                .into(ivSliderImage)
        }
    }
}