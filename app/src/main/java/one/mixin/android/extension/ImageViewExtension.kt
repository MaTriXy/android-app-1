package one.mixin.android.extension

import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.CropTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import one.mixin.android.util.StringSignature

fun ImageView.loadImage(uri: String?) {
    Glide.with(this).load(uri).into(this)
}

fun ImageView.loadImage(uri: Uri?) {
    Glide.with(this).load(uri).into(this)
}

fun ImageView.loadImage(uri: String?, @DrawableRes holder: Int) {
    Glide.with(this).load(uri).apply(RequestOptions.placeholderOf(holder)).into(this)
}

fun ImageView.loadImage(uri: String?, requestListener: RequestListener<Drawable?>) {
    Glide.with(this).load(uri).listener(requestListener).into(this)
}

fun ImageView.loadImage(uri: String?, width: Int, height: Int) {
    val multi = MultiTransformation(CropTransformation(width, height))
    Glide.with(this).load(uri).apply(RequestOptions.bitmapTransform(multi).dontAnimate()).into(this)
}

fun ImageView.loadGif(uri: String?, requestListener: RequestListener<GifDrawable?>? = null) {
    if (requestListener != null) {
        Glide.with(this).asGif().load(uri).apply(RequestOptions().dontTransform()).listener(requestListener).into(this)
    } else {
        Glide.with(this).asGif().load(uri).apply(RequestOptions().dontTransform()).into(this)
    }
}

fun ImageView.loadImageMark(uri: String?, @DrawableRes holder: Int, mark: Int) {
    Glide.with(this).load(uri).apply(RequestOptions().dontAnimate()
        .placeholder(holder)
        .signature(StringSignature("$uri$mark"))).into(this)
}

fun ImageView.loadVideoMark(uri: String, @DrawableRes holder: Int, mark: Int) {
    Glide.with(this).load(uri).apply(RequestOptions().frame(0)
        .signature(StringSignature("$uri$mark"))
        .centerCrop().placeholder(holder).dontAnimate()).into(this)
}

fun ImageView.loadVideo(uri: String, @DrawableRes holder: Int) {
    Glide.with(this).load(uri).apply(RequestOptions().frame(0)
        .centerCrop().placeholder(holder).dontAnimate()).into(this)
}

fun ImageView.loadSticker(uri: String?, type: String?) {
    uri?.let {
        when (type) {
            "GIF" -> {
                loadGif(uri)
            }
            else -> loadImage(uri)
        }
    }
}

fun ImageView.loadBase64(uri: ByteArray?, width: Int, height: Int, mark: Int) {
    val multi = MultiTransformation(CropTransformation(width, height))
    Glide.with(this).load(uri)
        .apply(RequestOptions().centerCrop()
            .transform(multi).signature(StringSignature("$uri$mark"))
            .dontAnimate()).into(this)
}

fun ImageView.loadCircleImage(uri: String?, @DrawableRes holder: Int? = null) {
    if (uri.isNullOrBlank()) {
        if (holder != null) {
            setImageResource(holder)
        }
    } else if (holder == null) {
        Glide.with(this).load(uri).apply(RequestOptions().circleCrop()).into(this)
    } else {
        Glide.with(this).load(uri).apply(RequestOptions().placeholder(holder).circleCrop()).into(this)
    }
}

fun ImageView.loadRoundImage(uri: String?, radius: Int, @DrawableRes holder: Int? = null) {
    if (uri.isNullOrBlank() && holder != null) {
        setImageResource(holder)
    } else if (holder == null) {
        Glide.with(this).load(uri).apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(radius, 0))).into(this)
    } else {
        Glide.with(this).load(uri).apply(RequestOptions().transform(RoundedCornersTransformation(radius, 0))
            .placeholder(holder))
            .into(this)
    }
}