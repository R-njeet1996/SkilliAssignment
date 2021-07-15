package com.app.view

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.appcompat.widget.AppCompatEditText
import com.app.manager.FontManager
import com.example.skilliassignment.R


/* CustomEditText class used to change typeface of the text.
 * */
class CustomEditText : AppCompatEditText {

    internal var onKeyPreImeListener: OnKeyPreImeListener? = null
    private var mContext: Context? = null
    private var typeface: String? = null

    constructor(context: Context, typeface: String) : super(context) {
        this.mContext = context
        this.typeface = typeface
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        this.mContext = context
        val a = context.obtainStyledAttributes(attrs, R.styleable.TextView)

        typeface = a.getString(R.styleable.TextView_ctypeface)
        setCTypeFace(typeface)
    }

    override fun setInputType(type: Int) {
        super.setInputType(type)
        setCTypeFace(typeface)
    }

    fun setCTypeFace(tf: String?) {
        typeface = tf
        if (tf != null) {
            FontManager.setTypeFace(mContext!!, this, tf)
        }
    }

    fun setOnKeyPreImeListener(onKeyPreImeListener: OnKeyPreImeListener) {
        this.onKeyPreImeListener = onKeyPreImeListener
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
            if (onKeyPreImeListener != null)
                onKeyPreImeListener!!.onBackPressed()
            return false
        }
        return super.dispatchKeyEvent(event)
    }

    interface OnKeyPreImeListener {
        fun onBackPressed()
    }
}


