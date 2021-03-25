package com.mironk.resumeapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PortfolioList(val title: String, val description: String, val imageSrc: Int) : Parcelable
