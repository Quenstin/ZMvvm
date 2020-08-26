package com.kotlin.zmvvm.ui.activitys

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.SparseArray
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.colorChooser
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.base.view.BaseActivity
import com.kotlin.zmvvm.common.state.UserInfo
import com.kotlin.zmvvm.common.state.callback.LoginSuccessState
import com.kotlin.zmvvm.common.utils.ChangeThemeEvent
import com.kotlin.zmvvm.common.utils.ColorUtil
import com.kotlin.zmvvm.common.utils.Constant
import com.kotlin.zmvvm.common.utils.SPreference
import com.kotlin.zmvvm.interface_.LoginSuccessListener
import com.kotlin.zmvvm.ui.home.view.HomeFragment
import com.kotlin.zmvvm.ui.wechat.view.WeChatFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_drawer_header.view.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), LoginSuccessListener {

    private var mUserName: String by SPreference(Constant.USERNAME_KEY, "未登录")

    private var isNightMode: Boolean by SPreference(Constant.NIGHT_MODE, false)

    private var mUserId: String by SPreference(Constant.USERID_KEY, "**")

    private val mFragmentSparseArray = SparseArray<Fragment>()

    private var mLastIndex = 1

    private lateinit var mHeadView: View

    private var mCurrentFragment: Fragment? = null

    private var mLastFragment: Fragment? = null


    override fun initView() {
        initToobar()
        initDrawerLayout()
        initFabButton()
        initColor()
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_home -> {
                    fab_add.visibility=View.VISIBLE
                    setToolBarTitle(toolbar,"首页")
                    switchFragment(Constant.HOME)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_wechat->{
                    fab_add.visibility=View.GONE
                    setToolBarTitle(toolbar,"公众号")
                    switchFragment(Constant.WECHAT)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_system->{
                    fab_add.visibility=View.GONE
                    setToolBarTitle(toolbar,"体系")
                    switchFragment(Constant.SYSTEM)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_navigation->{
                    fab_add.visibility=View.GONE
                    setToolBarTitle(toolbar,"导航")
                    switchFragment(Constant.NAVIGATION)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_project->{
                    fab_add.visibility=View.GONE
                    setToolBarTitle(toolbar,"项目")
                    switchFragment(Constant.PROJECT)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false

            }

        }

    }

    private fun switchFragment(index: Int) {
        val fragmentManager=supportFragmentManager
        val transaction=fragmentManager.beginTransaction()
        mCurrentFragment=fragmentManager.findFragmentByTag(index.toString())
        mLastFragment=fragmentManager.findFragmentByTag(mLastIndex.toString())

        if (index != mLastIndex) {
            if (mLastFragment != null) {
                transaction.hide(mLastFragment!!)
            }
            if (mCurrentFragment == null) {
                mCurrentFragment = getFragment(index)
                transaction.add(R.id.content, mCurrentFragment!!, index.toString())
            } else {
                transaction.show(mCurrentFragment!!)
            }
        }

        // 如果位置相同或者新启动的应用
        if (index == mLastIndex) {
            if (mCurrentFragment == null) {
                mCurrentFragment = getFragment(index)
                transaction.add(R.id.content, mCurrentFragment!!, index.toString())
            }
        }
        transaction.commit()
        mLastIndex = index


    }

    private fun getFragment(index: Int): Fragment? {
        var fragment: Fragment? = mFragmentSparseArray.get(index)
        if (fragment == null) {
            when (index) {
                Constant.HOME -> fragment = HomeFragment.getInstance()
                Constant.WECHAT -> fragment = WeChatFragment.getInstance()
            }
            mFragmentSparseArray.put(index, fragment)
        }
        return fragment!!

    }

    private fun initColor() {
        toolbar.setBackgroundColor(ColorUtil.getColor(this))
        mHeadView.setBackgroundColor(ColorUtil.getColor(this))
        bottom_navigation.itemIconTintList = ColorUtil.getColorStateList(this)
        bottom_navigation.itemTextColor = ColorUtil.getColorStateList(this)
        bottom_navigation.setBackgroundColor(ContextCompat.getColor(this, R.color.white_bg))
        fab_add.backgroundTintList = ColorUtil.getOneColorStateList(this)
    }

    private fun initFabButton() {
        fab_add.setOnClickListener {
            //数据置顶
//            mCurrentFragment!!.mRvArticle!!.smoothScrollToPosition(0)
            val objectAnimatorX =
                ObjectAnimator.ofFloat(fab_add, "scaleX", 1.0f, 1.2f, 0.0f)
            objectAnimatorX.interpolator = AccelerateDecelerateInterpolator()
            val objectAnimatorY =
                ObjectAnimator.ofFloat(fab_add, "scaleY", 1.0f, 1.2f, 0.0f)
            objectAnimatorY.interpolator = AccelerateDecelerateInterpolator()
            val animatorSet = AnimatorSet()
            animatorSet.playTogether(objectAnimatorX, objectAnimatorY)
            animatorSet.duration = 1000
            animatorSet.start()
        }
    }

    /**
     * 设置侧滑menu
     */
    private fun initDrawerLayout() {
        LoginSuccessState.addListeners(this)
        mHeadView = navigation_draw.getHeaderView(0)
        mHeadView.me_name.text = mUserName
        mHeadView.me_info.text = "当前账户$mUserId"
        mHeadView.me_image.setCircleName(mUserName)

        mHeadView.setOnClickListener {
            UserInfo.instance.login(this)
        }


        navigation_draw.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_menu_rank -> {
                    UserInfo.instance.startRankActivity(this)
                }
                R.id.nav_menu_square -> {
//                    startActivity<SquareActivity>()
                }
                R.id.nav_menu_collect -> {
                    UserInfo.instance.startCollectActivity(this)
                }
                R.id.nav_menu_share -> {
                    UserInfo.instance.startShareActivity(this)
                }
                R.id.nav_menu_question -> {
//                    startActivity<QuestionArticleListActivity>()
                }
                R.id.nav_menu_todo -> {
                    UserInfo.instance.startTodoActivity(this)
                }

                R.id.nav_menu_theme -> {
                    if (!isNightMode) {
                        MaterialDialog(this).show {
                            title(R.string.theme_color)
                            cornerRadius(16.0f)
                            colorChooser(
                                ColorUtil.ACCENT_COLORS,
                                initialSelection = ColorUtil.getColor(this@MainActivity),
                                subColors = ColorUtil.PRIMARY_COLORS_SUB
                            ) { _, color ->
                                ColorUtil.setColor(color)
                                ChangeThemeEvent().post()
                            }
                            positiveButton(R.string.done)
                            negativeButton(R.string.cancel)
                        }
                    } else {
                        toast("当前模式无法更换主体")
                    }
                    false
                }

                R.id.nav_menu_setting -> {
//                    startActivity<SettingActivity>()
                }
                R.id.nav_menu_logout -> {
                    UserInfo.instance.logoutSuccess()
                }
            }
            drawer_main.closeDrawers()
            true
        }

    }

    /**
     * 设置toobar
     */
    private fun initToobar() {
        setToolBarTitle(toolbar, "首页")

        val tooger =
            ActionBarDrawerToggle(this, drawer_main, toolbar, R.string.app_name, R.string.app_name)
        drawer_main.addDrawerListener(tooger)
        tooger.syncState()
    }

    override fun showCreateReveal() = false

    override fun initData() {
    }


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun loginSuccess(userName: String, userId: String, collectArticleIds: List<Int>?) {

    }


}