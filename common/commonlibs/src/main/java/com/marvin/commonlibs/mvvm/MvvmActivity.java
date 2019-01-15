package com.marvin.commonlibs.mvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.marvin.commonlibs.base.BaseActivity;
import com.marvin.jetpack.DataObserver;
import com.marvin.jetpack.DataViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

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
 * Created by marvin on 2018/12/9
 */
public abstract class MvvmActivity<T extends DataViewModel> extends BaseActivity {

    private T mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mainViewModel = ViewModelProviders.of(this).get(VMType());
        super.onCreate(savedInstanceState);
    }

    protected T VM() {
        return mainViewModel;
    }

    protected <C> void observe(String name, DataObserver<C> observer) {
        VM().observe(name, this, observer);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    protected Class<T> VMType() {
        Class<? extends MvvmActivity> aClass = this.getClass();
        Type aType = aClass.getGenericSuperclass();
        Type[] types = null;
        if (aType instanceof ParameterizedType) {
            types = ((ParameterizedType) aType).getActualTypeArguments();
        }
        if (types != null) {
            return (Class<T>) types[0];
        } else {
            throw new NullPointerException("you must set the Generic Types!");
        }
    }

}
