package com.example.skilliassignment.modal

import android.graphics.Bitmap
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class PhotoResponse {


    private var isPhoto: Boolean? = false

    @SerializedName("bitmap")
    @Expose
    private var photo: String? = null

    @SerializedName("comment")
    @Expose
    private var comment: String? = null

    @SerializedName("picture")
    @Expose
    private var picture: String? = null

    @SerializedName("_id")
    @Expose
    private var id: String? = null

    @SerializedName("publishedAt")
    @Expose
    private var publishedAt: String? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    fun getComment(): String? {
        return comment
    }

    fun setComment(comment: String?) {
        this.comment = comment
    }

    fun getPicture(): String? {
        return picture
    }

    fun setPicture(picture: String?) {
        this.picture = picture
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getPublishedAt(): String? {
        return publishedAt
    }

    fun setPublishedAt(publishedAt: String?) {
        this.publishedAt = publishedAt
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getIsPhoto(): Boolean? {
        return isPhoto
    }

    fun setIsPhoto(isPhoto: Boolean?) {
        this.isPhoto = isPhoto
    }

    fun getPhoto(): String? {
        return photo
    }

    fun setPhoto(photo: String?) {
        this.photo = photo
    }

}