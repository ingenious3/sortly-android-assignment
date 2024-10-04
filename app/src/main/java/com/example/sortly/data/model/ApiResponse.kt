package com.example.sortly.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ApiResponse (
    @SerializedName("data")
    val dataJson: List<Items>?,
    @SerializedName("meta")
    val meta: Meta?
)

data class Meta(
    @SerializedName("next_page_url")
    val nextPageUrl: String?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
)

@Parcelize
data class Items(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("label_url")
    val labelUrl: String?,
    @SerializedName("label_url_extra")
    val labelUrlExtra: String?,
    @SerializedName("label_url_extra_type")
    val labelUrlExtraType: String?,
    @SerializedName("label_url_type")
    val labelUrlType: String?,
    @SerializedName("measured_quantity")
    val measuredQuantity: MeasuredQuantity?,
    @SerializedName("min_quantity")
    val minQuantity: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("notes")
    val notes: String?,
    @SerializedName("parent_id")
    val parentId: Int??,
    @SerializedName("price")
    val price: String?,
    @SerializedName("quantity")
    val quantity: String?,
    @SerializedName("sid")
    val sid: String?,
    @SerializedName("tag_names")
    val tagNames: List<String>?,
    @SerializedName("tags")
    val tags: List<Tag>?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
): Parcelable

@Parcelize
data class MeasuredQuantity(
    @SerializedName("name")
    val name: String?,
    @SerializedName("pretty_unit")
    val prettyUnit: String?,
    @SerializedName("scale")
    val scale: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("value")
    val value: String?
): Parcelable

@Parcelize
data class Tag(
    @SerializedName("name")
    val name: String?
): Parcelable