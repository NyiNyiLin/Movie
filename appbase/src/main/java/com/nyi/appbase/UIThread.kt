package com.nyi.appbase

import android.os.Looper
import com.nyi.domainn.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UIThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler
        get() = {
            val asyncMainThreadScheduler = AndroidSchedulers.from(Looper.getMainLooper(), true)
            RxAndroidPlugins.setInitMainThreadSchedulerHandler {
                asyncMainThreadScheduler
            }
            AndroidSchedulers.mainThread()
        }.invoke()
}