package sen.com.absraction.extentions

import android.content.res.ColorStateList
import android.util.Log
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import sen.com.absraction.R
import java.util.regex.Pattern


fun ImageView.loadFull(imageUri: Any?) {
    val builder =
        if (imageUri is String? && isGIF(imageUri))
            Glide.with(this).asGif().load(imageUri)
        else
            Glide.with(this).load(imageUri)
    builder
        .placeholder(R.drawable.ic_placeholder_food)
        .error(R.drawable.ic_placeholder_food)
        .apply(RequestOptions())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView.load(
    imageUri: Any?,
    @DrawableRes errorRes: Int = R.drawable.ic_placeholder_food
) {
    val builder =
        if (imageUri is String? && isGIF(imageUri))
            Glide.with(this).asGif().load(imageUri)
        else
            Glide.with(this).load(imageUri)
    builder
        .placeholder(R.drawable.ic_placeholder_food)
        .error(errorRes)
        .apply(RequestOptions())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView.loadRoundedCorner(
    imageUri: Any?,
    corner: Int = 0,
    @DrawableRes errorRes: Int = R.drawable.ic_placeholder_food
) {
    val builder =
        if (imageUri is String? && isGIF(imageUri))
            Glide.with(this).asGif().load(imageUri)
        else
            Glide.with(this).load(imageUri)
    builder
        .placeholder(R.drawable.ic_placeholder_food)
        .error(errorRes)
        .apply(RequestOptions())
//        .transform(RoundedCorners(corner.dp))
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView.loadLong(imageUri: Any?, @DrawableRes errorRes: Int = R.drawable.ic_placeholder_food) {
    if (imageUri == null) return
    val builder =
        if (imageUri is String? && isGIF(imageUri))
            Glide.with(this).asGif().load(imageUri)
        else
            Glide.with(this).load(imageUri)
    builder
        .placeholder(R.drawable.ic_placeholder_food)
        .error(errorRes)
        .apply(RequestOptions())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView?.loadCircle(
    imageUri: Any?,
    @DrawableRes errorRes: Int = R.drawable.ic_placeholder_food,
    @DrawableRes placeholderRes: Int = R.drawable.ic_placeholder_food
) {
    if (this == null) return
    val builder =
        if (imageUri is String? && isGIF(imageUri)) Glide.with(this).asGif().load(imageUri)
        else Glide.with(this).load(imageUri)
    builder
        .placeholder(placeholderRes)
        .error(errorRes)
        .apply(RequestOptions())
        .transform(CircleCrop())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}

fun ImageView?.updateTint(@ColorRes colorRes: Int) {
    if (this == null) return
    ImageViewCompat.setImageTintList(
        this,
        ColorStateList.valueOf(ContextCompat.getColor(context, colorRes))
    )
}

fun ImageView?.updateDrawable(@DrawableRes drawableRes: Int) {
    if (this == null) return
    this.setImageDrawable(ContextCompat.getDrawable(context, drawableRes))
}

//fun isGIF(url: String) = Pattern.compile("([^\\s]+(\\.(?i)(gif))$)").matcher(url).matches()
fun isGIF(url: String?) = !url.isNullOrEmpty() && url.contains(".gif")