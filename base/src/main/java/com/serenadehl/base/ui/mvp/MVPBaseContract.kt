package com.serenadehl.base.ui.mvp

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-09-12 01:43:08
 */
interface IBaseView

interface IBasePresenter {
    fun attach(view: IBaseView)
    fun detach()
}

interface IBaseModel