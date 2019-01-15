package com.marvin.commonlibs.base;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * ┏┓　   ┏┓
 * ┏┛┻━━━━━┛┻━┓
 * ┃　　　　   ┃
 * ┃　━　━　   ┃
 * ████━████   ┃
 * ┃　　　　   ┃
 * ┃　 ┻　    ┃
 * ┗━┓      ┏━┛
 * 　┃      ┃
 * 　┃ 0BUG ┗━━━┓
 * 　┃0Error     ┣┓
 * 　┃0Warning   ┏┛
 * 　┗┓┓┏━┳┓┏┛ ━
 * 　　┃┫┫ ┃┫┫
 * 　　┗┻┛ ┗┻┛
 * Created by marvin on 2018/11/15
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    private boolean hasContentView = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (hasContentView) {
            setContentView(LayoutId());
        }
        beforeInit(savedInstanceState);
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    public void beforeInit(Bundle savedInstanceState) {
    }

    protected void hasContentView(boolean hasView) {
        hasContentView = hasView;
    }

    protected abstract int LayoutId();

    protected abstract void initData(Bundle savedInstanceState);

    protected abstract void initView(Bundle savedInstanceState);

    protected boolean checkPermission(@NonNull String... perms) {
        return EasyPermissions.hasPermissions(this, perms);
    }

    protected void requestPermission(@NonNull String tips, @RequestCode int requestCode, @NonNull String... perms) {
        EasyPermissions.requestPermissions(this, tips, requestCode, perms);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    public static final int READ_EXTERNAL_STORAGE = 1;
    public static final int WRITE_EXTERNAL_STORAGE = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE
    })
    public @interface RequestCode {

    }
}
