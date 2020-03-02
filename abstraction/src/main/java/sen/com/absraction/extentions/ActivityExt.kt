package sen.com.absraction.extentions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sen.com.absraction.R
import sen.com.uicomponent.DividerItemDecorator

/**
 * Created by korneliussendy on 01/03/20,
 * at 12.52.
 * My Application
 */

fun AppCompatActivity.startActivity(
    route: String,
    extra: Bundle? = null,
    finish: Boolean = false,
    requestCode: Int? = null
) {
    try {
        val uri = getString(R.string.BASE_SCHEME) + "://" + route
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        if (extra != null) intent.putExtras(extra)
        if (requestCode == null || requestCode == 0)
            startActivity(intent)
        else
            startActivityForResult(intent, requestCode)

        if (finish) finish()
    } catch (e: Exception) {
        Log.e("startActivity", e.message, e.cause)
    }
}

fun AppCompatActivity.startActivity(
    route: String,
    requestCode: Int? = null,
    extra: Bundle? = null
) = startActivity(route, extra, false, requestCode)


fun AppCompatActivity.setupRecyclerView(
    rv: RecyclerView,
    adapter: RecyclerView.Adapter<*>,
    divider: Boolean = false
) {
    val manager = LinearLayoutManager(this)
    rv.layoutManager = manager
    rv.adapter = adapter
    if (divider) {
        rv.addItemDecoration(
            DividerItemDecorator(
                ContextCompat.getDrawable(rv.context, R.drawable.divider)
            )
        )
    }
}