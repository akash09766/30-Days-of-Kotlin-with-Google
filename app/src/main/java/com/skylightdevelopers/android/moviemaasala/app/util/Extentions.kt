package com.skylightdevelopers.android.moviemaasala.app.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.skylightdevelopers.android.moviemaasala.R
import com.skylightdevelopers.android.moviemaasala.core.ui.base.BaseFragment

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(url: String?) {
    val option = RequestOptions()
        .placeholder(R.drawable.ic_local_movies_black)
        .error(R.mipmap.ic_launcher_round)

    GlideApp.with(context)
        .setDefaultRequestOptions(option)
        .load( MConstants.IMAGE_URL_BASE_URL_TYPE_HEADER.plus(url))
        /*.listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                Log.e("ImageView.loadImage", "GlideException : ${e?.message}")
                return true
            }


            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                Log.d("ImageView.loadImage", "isFirstResource : ${isFirstResource}")
                return false
            }
        })*/
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun _loadImage(view: ImageView, url: String?) {
    view.loadImage(url)
}


fun BaseFragment.showOkaySnackBar(view: View, msg: String) {
    val snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_INDEFINITE)
    snackbar.setAction(MConstants.SNACK_BAR_OKAY_BTN_TEXT) {
        snackbar.dismiss()
    }
    snackbar.show()
}

fun BaseFragment.showLongSnackBar(view: View, msg: String) =
    Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()

fun BaseFragment.showShortSnackBar(view: View, msg: String) =
    Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show()

fun BaseFragment.showLongToast(msg: String) =
    Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()

fun BaseFragment.showShortToast(msg: String) =
    Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()