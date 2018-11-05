package me.serenadehl.base.base.mvpbase

interface IBaseView

interface IBasePresenter {
    fun attach(view: IBaseView)
    fun detach()
}

interface IBaseModel
