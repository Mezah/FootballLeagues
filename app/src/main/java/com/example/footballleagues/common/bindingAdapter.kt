package com.example.footballleagues.common

import android.graphics.drawable.PictureDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.target.Target
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import com.example.footballleagues.R
import java.io.IOException
import java.io.InputStream


@BindingAdapter("android:bindImage")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    GlideApp.with(imageView.context)
        .`as`(PictureDrawable::class.java)
        .error(R.drawable.ic_baseline_error_24px)
        .listener(SvgSoftwareLayerSetter())
        .load(imageUrl)
        .apply(RequestOptions().error(com.example.footballleagues.R.drawable.ic_baseline_error_24px))
        .into(imageView)
}

class SvgDecoder : ResourceDecoder<InputStream, SVG> {

    override fun handles(source: InputStream, options: Options): Boolean {
        return true
    }

    @Throws(IOException::class)
    override fun decode(
        source: InputStream, width: Int, height: Int,
        options: Options
    ): Resource<SVG> {
        try {
            val svg = SVG.getFromInputStream(source)
            return SimpleResource(svg)
        } catch (ex: SVGParseException) {
            throw IOException("Cannot load SVG from stream", ex)
        }

    }
}

class SvgDrawableTranscoder : ResourceTranscoder<SVG, PictureDrawable> {
    override fun transcode(
        toTranscode: Resource<SVG>,
        options: Options
    ): Resource<PictureDrawable>? {
        val svg = toTranscode.get()
        val picture = svg.renderToPicture()
        val drawable = PictureDrawable(picture)
        return SimpleResource(drawable)
    }
}

class SvgSoftwareLayerSetter : RequestListener<PictureDrawable> {

    override fun onLoadFailed(
        e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<PictureDrawable>?, isFirstResource: Boolean
    ): Boolean {
        val view = (target as ImageViewTarget<*>).view
        view.setLayerType(ImageView.LAYER_TYPE_NONE, null)
        return false
    }

    override fun onResourceReady(
        resource: PictureDrawable, model: Any, target: Target<PictureDrawable>, dataSource: DataSource, isFirstResource: Boolean
    ): Boolean {
        val view = (target as ImageViewTarget<*>).view
        view.setLayerType(ImageView.LAYER_TYPE_SOFTWARE, null)
        return false
    }
}