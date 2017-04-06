package com.kurume_nct.imagefilter.twitter

import android.os.Bundle
import io.reactivex.Single
import twitter4j.ResponseList
import twitter4j.Status

/**
 * Created by gedorinku on 2017/04/06.
 */
interface IStatusProvider {

    fun requestStatuses(): Single<ResponseList<Status>>

    companion object {

        fun create(providerType: ProviderType): IStatusProvider = when (providerType.type) {
            ProviderType.Type.HOME_TIMELINE -> HomeTimelineProvider()
            ProviderType.Type.USER_LIST_TIMELINE -> UserListTimelineProvider(providerType.listId)
            else -> throw NotImplementedError()
        }

        fun create(bundle: Bundle): IStatusProvider {
            val providerType = ProviderType.createFromBundle(bundle)
            return create(providerType)
        }
    }

    class ProviderType private constructor(
            val userId: Long,
            val listId: Long,
            val type: Type
    ) {

        fun writeToBundle(bundle: Bundle) {
            bundle.putLong(EXTRA_LONG_USER_ID, userId)
            bundle.putLong(EXTRA_LONG_LIST_ID, listId)
            bundle.putInt(EXTRA_INT_TYPE, type.id)
        }

        override fun equals(other: Any?): Boolean =
                other is ProviderType &&
                        userId == other.userId &&
                        listId == other.listId &&
                        type.id == other.type.id

        override fun hashCode(): Int {
            var result = userId.hashCode()
            result = 31 * result + listId.hashCode()
            result = 31 * result + type.hashCode()
            return result
        }


        companion object {

            private const val PACKAGE: String = "com.kurume_nct.imagefilter.twitter"

            val EXTRA_LONG_USER_ID: String = PACKAGE + "EXTRA_LONG_USER_ID"

            val EXTRA_LONG_LIST_ID: String = PACKAGE + "EXTRA_LONG_LIST_ID"

            val EXTRA_INT_TYPE: String = PACKAGE + "EXTRA_INT_TYPE"

            val HOME_TIMELINE_TYPE: ProviderType = ProviderType(-1, -1, Type.HOME_TIMELINE)


            fun createUserTimelineType(userId: Long): ProviderType =
                    ProviderType(userId, -1, Type.USER_TIMELINE)

            fun createUserListTimelineType(listId: Long): ProviderType =
                    ProviderType(-1, listId, Type.USER_LIST_TIMELINE)

            fun createFromBundle(bundle: Bundle): ProviderType {
                val userId = bundle.getLong(EXTRA_LONG_USER_ID)
                val listId = bundle.getLong(EXTRA_LONG_LIST_ID)
                val type = Type.values()[bundle.getInt(EXTRA_INT_TYPE)]

                return ProviderType(userId, listId, type)
            }
        }

        enum class Type(val id: Int) {
            HOME_TIMELINE(0),
            USER_TIMELINE(1),
            USER_LIST_TIMELINE(2)
        }
    }
}