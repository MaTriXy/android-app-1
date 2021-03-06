package one.mixin.android.api.service

import com.google.gson.JsonObject
import io.reactivex.Observable
import one.mixin.android.api.MixinResponse
import one.mixin.android.api.request.AccountRequest
import one.mixin.android.api.request.AccountUpdateRequest
import one.mixin.android.api.request.PinRequest
import one.mixin.android.api.request.SessionRequest
import one.mixin.android.api.request.VerificationRequest
import one.mixin.android.api.response.VerificationResponse
import one.mixin.android.vo.Account
import one.mixin.android.api.request.PinToken
import one.mixin.android.vo.Sticker
import one.mixin.android.vo.StickerAlbum
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountService {

    @POST("verifications")
    fun verification(@Body request: VerificationRequest): Observable<MixinResponse<VerificationResponse>>

    @POST("verifications/{id}")
    fun create(@Path("id") id: String, @Body request: AccountRequest): Observable<MixinResponse<Account>>

    @POST("verifications/{id}")
    fun changePhone(@Path("id") id: String, @Body request: AccountRequest): Call<MixinResponse<Account>>

    @POST("me")
    fun update(@Body request: AccountUpdateRequest): Observable<MixinResponse<Account>>

    @POST("me/preferences")
    fun preferences(@Body request: AccountUpdateRequest): Observable<MixinResponse<Account>>

    @GET("me")
    fun getMe(): Call<MixinResponse<Account>>

    @POST("logout")
    fun logout(): Observable<MixinResponse<Unit>>

    @GET("codes/{id}")
    fun code(@Path("id") id: String): Observable<MixinResponse<JsonObject>>

    @POST("invitations/{code}")
    fun invitations(@Path("code") code: String): Observable<MixinResponse<Account>>

    @POST("pin/update")
    fun updatePin(@Body request: PinRequest): Call<MixinResponse<Account>>

    @POST("pin/verify")
    fun verifyPin(@Body request: PinRequest): Call<MixinResponse<Account>>

    @GET("pin/token")
    fun getPinToken(): Observable<MixinResponse<PinToken>>

    @POST("session")
    fun updateSession(@Body request: SessionRequest): Observable<MixinResponse<Account>>

    @GET("stickers/albums")
    fun getStickerAlbums(): Call<MixinResponse<List<StickerAlbum>>>

    @GET("stickers/albums/{id}")
    fun getStickers(@Path("id") id: String): Call<MixinResponse<List<Sticker>>>
}