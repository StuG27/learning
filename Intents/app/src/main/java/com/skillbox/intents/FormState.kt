package com.skillbox.intents

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FormState(
        var valid: Boolean,
        var message: String
): Parcelable