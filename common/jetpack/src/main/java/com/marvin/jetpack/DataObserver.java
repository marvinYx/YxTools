package com.marvin.jetpack;

import android.arch.lifecycle.Observer;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.marvin.tools.LogUtils;

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
@FunctionalInterface
public interface DataObserver<T> extends Observer<DataModel<T>> {

    /**
     * 数据改变时 分发数据
     *
     * @param dataModel 数据实体
     */
    @CallSuper
    @Override
    default void onChanged(@Nullable DataModel<T> dataModel) {
        if (dataModel != null && !beTaken(dataModel)) {
            if (dataModel.getStatusCode() == DataModel.SUCCESS) {
                onSuccess(dataModel.getData());
                onComplete(DataModel.SUCCESS);
            } else if (dataModel.getStatusCode() == DataModel.EMPTY) {
                onEmpty(dataModel.getMessage());
                onComplete(DataModel.EMPTY);
            } else if (dataModel.getStatusCode() == DataModel.ERROR) {
                onError(dataModel.getMessage());
                onComplete(DataModel.ERROR);
            }
        }
    }

    /**
     * 拦截消息
     *
     * @param dataModel 数据实体
     * @return true时拦截
     */
    default boolean beTaken(@Nullable DataModel<T> dataModel) {
        return false;
    }


    /**
     * 成功回调
     *
     * @param data 数据
     */
    void onSuccess(T data);


    /**
     * 空数据回调
     *
     * @param msg 空数据信息
     */
    default void onEmpty(String msg) {
        LogUtils.d(msg);
    }

    /**
     * 错误回调
     *
     * @param msg 错误信息
     */
    default void onError(String msg) {
        LogUtils.e(msg);
    }

    /**
     * 完成回调
     *
     * @param status 完成状态
     */
    default void onComplete(@DataModel.Status int status) {
    }
}
