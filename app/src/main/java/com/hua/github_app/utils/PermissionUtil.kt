package com.hua.github_app.utils

import android.Manifest
import androidx.fragment.app.FragmentActivity
import com.permissionx.guolindev.PermissionX
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created on 2022/8/12.
 *
 * @author hua
 */
object PermissionUtil {

    suspend fun checkStoragePermission(activity: FragmentActivity): Boolean {
        return suspendCoroutine { continuation ->
            PermissionX.init(activity).permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request { allGranted, _, _ ->
                    continuation.resume(allGranted)
                }
        }
    }

}