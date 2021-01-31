package ru.lucass.appname

import com.bumptech.glide.request.RequestOptions

class GlideOption {
    companion object {
         val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_avatar_placeholder)
            .fallback(R.drawable.ic_avatar_placeholder)
    }
}