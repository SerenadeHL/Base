package me.serenadehl.base.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.*
import me.serenadehl.base.extensions.getStatusBarHeight
import me.serenadehl.base.extensions.gone
import me.serenadehl.base.extensions.visible
import me.serenadehl.base.utils.app.AppManager

abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mRootView: ViewGroup
    protected lateinit var mStatusBarView: View
    private lateinit var mContentView: ViewGroup
    private lateinit var mContentParent: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //取消ActionBar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        mRootView = LayoutInflater.from(this).inflate(layout(), null) as ViewGroup
        setContentView(mRootView)
        //记录当前Activity
        AppManager.instance.addActivity(this)
        onActivityCreated(savedInstanceState)
    }

    override fun onDestroy() {
        //移除当前Activity
        AppManager.instance.removeActivity(this)
        super.onDestroy()
    }

    /**
     * 设置布局
     * @return 布局id
     */
    abstract fun layout(): Int

    abstract fun onActivityCreated(savedInstanceState: Bundle?)

    /**
     * 设置StatusBar透明
     *
     * @param darkFont 是否使用深色状态栏字体
     */
    protected fun setStatusBarTranslucent(darkFont: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 全透明实现
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            if (darkFont)
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            else
                window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4 全透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param color 颜色
     * @param darkFont 是否使用深色状态栏字体
     */
    protected fun setStatusBarColor(color: Int, darkFont: Boolean = false) {
        setStatusBarTranslucent(darkFont)
        mStatusBarView.setBackgroundColor(color)
    }

    /**
     * 替换状态栏
     *
     * @param color 颜色
     * @param darkFont 是否使用深色状态栏字体
     */
    protected fun setupStatusBar(color: Int, darkFont: Boolean = false) {
        findContentView()
        createStatusBarView(mContentParent)
        setStatusBarColor(color, darkFont)
        showStatusBar()
    }

    /**
     * 找到父布局
     */
    private fun findContentView() {
        if (::mContentParent.isInitialized) return

        mContentParent = findViewById<View>(android.R.id.content) as ViewGroup
        //如果是DrawerLayout,让内部第一个布局设置padding
        mContentView = if (mRootView is DrawerLayout)
            mContentView.getChildAt(0) as ViewGroup
        else
            mRootView
    }

    /**
     * 显示状态栏
     */
    protected fun showStatusBar() {
        if (::mStatusBarView.isInitialized) {
            mStatusBarView.visible()
            val params = mContentView.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(0, getStatusBarHeight(), 0, 0)
        }
    }

    /**
     * 隐藏状态栏
     */
    protected fun hideStatusBar() {
        if (::mStatusBarView.isInitialized) {
            mStatusBarView.gone()
            val params = mContentView.layoutParams as ViewGroup.MarginLayoutParams
            params.setMargins(0, 0, 0, 0)
        }
    }

    /**
     * 创建状态栏View
     */
    private fun createStatusBarView(contentLayout: ViewGroup) {
        mStatusBarView = View(this)
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
        contentLayout.addView(mStatusBarView, 0, lp)
    }
}
