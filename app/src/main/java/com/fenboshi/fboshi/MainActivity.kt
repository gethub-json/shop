package com.fenboshi.fboshi

import android.animation.ObjectAnimator
import android.content.Context
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import cn.leancloud.AVObject
import cn.leancloud.AVQuery
import com.fenboshi.fboshi.bean.UserBean
import com.fenboshi.fboshi.viewmodel.UserViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation.*
import io.reactivex.Observer as Observer

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var nav_host_fragment:FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(false)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        nav_host_fragment=findViewById(R.id.nav_host_fragment)
        bnv_menu.setupWithNavController(navController)
        var userViewModel = ViewModelProviders.of(this).get<UserViewModel>()


        val nameObserver = object : androidx.lifecycle.Observer<UserBean> {
            override fun onChanged(t: UserBean?) {

                //Log.d("UserBean","event"+ (t?.mobile ?:"1140965679@qq.com"))
                //Log.d("UserBean","event"+ (t?.nickName ?:"幽你一默"))
            }


        }
        userViewModel.getUser().observe(this, nameObserver)
        //获取数据
        val query = AVQuery<AVObject>("_User")
        query.getInBackground("5d88755f7b968a008a346254")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<AVObject> {
                override fun onSubscribe(disposable: Disposable) {
                }
              override fun onNext(todo: AVObject) {
                    // todo 就是 objectId 为 582570f38ac247004f39c24b 的 Todo 实例
                    val name = todo.getString("username")
                    val email = todo.getString("email")
                    // int priority    = todo.getInt("priority");
                    var userBean = UserBean()
                    userBean.nickName = name
                    userBean.mobile = email
                    userViewModel.setUser(userBean)
                }

                override fun onError(throwable: Throwable) {

                }
                override fun onComplete() {

                }
            })
        /**
         * 动画执行的 属性值数组
         */
        var animatorProperty: FloatArray? = null
        /**
         * 第一排图 距离屏幕底部的距离
         */
        var top = 0
        /**
         * 第二排图 距离屏幕底部的距离
         */
        var bottom = 0f

        if (animatorProperty == null) {
            bottom = dip2pxt(this, 210f)
            //最后一个0 事view向下偏移的距离，第三个参数是到达原view的高度
            animatorProperty = floatArrayOf(bottom.toFloat(), 60f, -50f, -50f, 0f)
        }
        var isOpen: Boolean = false
        //底部弹框动画操作
        rl_click?.setOnClickListener(View.OnClickListener {
            //开启动画
            if (!isOpen) {
                iv_fabu_copy.visibility = View.VISIBLE
                _startAnimation(iv_fabu_copy, 500, animatorProperty)
                isOpen = true
            } else {

                //关闭动画
                isOpen = false
                _closeAnimation(iv_fabu_copy, 500, 310f)
                iv_fabu_copy.postDelayed(
                    Runnable
                    {
                        iv_fabu_copy.visibility = View.GONE
                    },
                    500
                )
            }

        })
        iv_fabu_copy.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "点击了新增", Toast.LENGTH_SHORT).show()
        })

        navigation_tab1.setOnClickListener {
            setNum(bo1 = true, bo2 = false, bo3 = false, bo4 = false);
        }
        navigation_tab2.setOnClickListener {
            setNum(bo1 = false, bo2 = true, bo3 = false, bo4 = false);
        }

        navigation_tab3.setOnClickListener {
            setNum(bo1 = false, bo2 = false, bo3 = true, bo4 = false);
        }

        navigation_tab4.setOnClickListener {
            setNum(bo1 = false, bo2 = false, bo3 = false, bo4 = true);
        }


    }
    private  fun initOn(){}

    private fun setNum(bo1: Boolean, bo2: Boolean, bo3: Boolean, bo4: Boolean ) {
        if (null != navigation_tab1) {
            navigation_tab1.isChecked = bo1
            navigation_tab2.isChecked = bo2
            navigation_tab3.isChecked = bo3
            navigation_tab4.isChecked = bo4
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


    /**
     * 启动动画
     *
     * @param view     view
     * @param duration 执行时长
     * @param distance 执行的轨迹数组
     */
    private fun _startAnimation(view: View, duration: Int, distance: FloatArray) {
        val anim = ObjectAnimator.ofFloat(view, "translationY", *distance)
        anim.duration = duration.toLong()
        anim.start()
    }

    /**
     * 关闭时的动画
     *
     * @param view     mView
     * @param duration 动画执行时长
     * @param next     平移量
     */
    private fun _closeAnimation(view: View, duration: Int, next: Float) {
        val anim = ObjectAnimator.ofFloat(view, "translationY", 0f, dip2pxt(this, 310f))
        anim.duration = duration.toLong()
        anim.start()
    }


    /**
     * dp转化为px
     *
     * @param context  context
     * @param dipValue dp value
     * @return 转换之后的px值
     */
    fun dip2pxt(context: Context, dipValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

}
