package com.marvin.jetpack;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.marvin.tools.CheckUtils;

import java.util.HashMap;
import java.util.function.Function;

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
 * Created by marvin on 2018/12/19
 * <p>
 * ViewModel 负责页面数据的获取、整理、过滤
 * 判断所获取数据的状态 success/empty/error
 * 内部有LiveData的Hash集合，作为当前页面的数据中心
 * 未避免方法长度过长，可以适当抽取共有函数 进行封装
 */
public class DataViewModel extends AndroidViewModel {

    private HashMap<String, DataLiveData> dataCenter;
    private static int DEFAULT_INITIAL_CAPACITY = 32;

    public DataViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressWarnings("unchecked")
    public <T> void observe(@NonNull String name, @NonNull LifecycleOwner owner, @NonNull DataObserver<T> observer) {
        CheckUtils.requireNonNull(name, "name must be Created!");
        CheckUtils.requireNonNull(observer, "DataObservers must be Created!");
        dataCenter = dataCenter != null ? dataCenter : new HashMap<>(DEFAULT_INITIAL_CAPACITY);
        DataLiveData<T> existing = dataCenter.get(name);
        DataLiveData<T> dataLiveData = existing != null ? existing : new DataLiveData<>();
        dataLiveData.observe(owner, observer);
        if (!dataCenter.containsKey(name)) {
            dataCenter.put(name, dataLiveData);
        }
    }

    public void addStatusListener(@NonNull String name, @NonNull DataLiveData.StatusListener listener) {
        liveData(name).addStatusListener(listener);
    }

    @SuppressWarnings("unchecked")
    private <T> DataLiveData<T> liveData(@NonNull String name) {
        CheckUtils.requireNonNull(name, "name must be Created!");

        if (dataCenter != null && dataCenter.containsKey(name)) {
            return dataCenter.get(name);
        }
        DataLiveData<T> dataLiveData = new DataLiveData<>();
        dataCenter = dataCenter != null ? dataCenter : new HashMap<>(DEFAULT_INITIAL_CAPACITY);
        dataCenter.put(name, dataLiveData);
        return dataLiveData;
    }

    protected boolean containKey(@NonNull String name) {
        if (dataCenter == null) {
            return false;
        }
        return dataCenter.containsKey(name);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected <T> void addSource(@NonNull String name, @NonNull LiveData<T> tempLiveData, @NonNull Function<T, DataModel<T>> function) {
        DataLiveData<T> dataLiveData = liveData(name);
        dataLiveData.addSource(tempLiveData, t -> dataLiveData.setValueWithFunc(t, function));
    }

    protected <T> void success(@NonNull String name, @Nullable T data) {
        DataLiveData<T> liveData = liveData(name);
        CheckUtils.requireNonNull(liveData, "this liveData not be observed");
        liveData.success(data);
    }

    protected void error(@NonNull String name, @NonNull String msg) {
        DataLiveData liveData = liveData(name);
        CheckUtils.requireNonNull(liveData, "this liveData not be observed");
        liveData.error(msg);
    }

    protected void empty(@NonNull String name, @NonNull String msg) {
        DataLiveData liveData = liveData(name);
        CheckUtils.requireNonNull(liveData, "this liveData not be observed");
        liveData.empty(msg);
    }

}
