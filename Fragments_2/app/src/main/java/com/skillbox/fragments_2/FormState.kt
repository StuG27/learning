package com.skillbox.fragments_2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FormState(
        var state: BooleanArray,
): Parcelable {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as FormState

                if (!state.contentEquals(other.state)) return false

                return true
        }

        override fun hashCode(): Int {
                return state.contentHashCode()
        }
}