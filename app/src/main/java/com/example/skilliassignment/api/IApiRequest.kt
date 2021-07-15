package com.app.api


import com.digitalincome.revolution.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface IApiRequest {

   /* @POST("accounts/email_login/")
    fun signIn(@Body loginRequest: LoginRequest) : Observable<BaseResponse<LoginResponse>>*/


    @POST("Account/IsValidUser")
    fun checkValidUser(@Header("mobileNumber") id:String,@Header("macAddress") macAddress:String): Call<ValidUserResponse>

    @GET("Account/GetDistributorDetails/{id}")
    fun getUserDetail(@Path("id") id:String): Call<DetailResponse>

    @GET("Account/SendOTP/{id}")
    fun sendOtp(@Path("id") id:String):Call<ErrorModel>

    @GET("Account/ValidateOTP")
    fun createPin(@Header("eUserId") userId:String,@Header("otp") otp:String,@Header("pin") pin:String,@Header("macAddress") macAddress:String,@Header("mobileNumber") mobileNumber:String,@Header("phoneDetails") phoneDetails:String):Call<ErrorModel>

    @POST("Account/Login")
    fun login(@Header("eUserId") userId:String,@Header("mobileNumber") mobileNumber:String,@Header("pin") pin:String):Call<LoginResponse>

    @POST("Account/AutoUserRegistration")
  fun register(@Body registrationRequest: RegistrationRequest):Call<RegisterResponse>

    @GET("Account/GetDistributorByCode/{id}")
    fun checkIntroducer(@Path("id") id:String):Call<IntroducerResponse>

    @POST("Account/UserRegistration")
      fun manualRegister(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Header("eUserType") eUserType:String,@Body manualRegisterRequest: ManualRegisterRequest):Call<IntroducerResponse>

    @GET("Recharge/GetOperator/{id}")
    fun getOperator(@Path("id") id:String):Call<OperatorResponse>

    @GET("Recharge/GetOperator/1/true")
    fun getOperator2():Call<OperatorResponse>

    @GET("Recharge/GetCircle")
    fun getCircel():Call<OperatorResponse>

    @POST("Recharge/Recharge")
    fun payment(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Body rechargeRequest: RechargeRequest):Call<IntroducerResponse>

  @POST("Recharge/RechargeHistory")
    fun rechargeHistory(@Header("eUserId") userId:String,@Header("eBranchCode")eBranchCode:String,@Body rechargeHistoryRequest: RechargeHistoryRequest):Call<RechargeHistoryResponse>

    @POST("Account/ContactDetails")
    fun uploadContact(@Header("eUserId") userId:String,@Header("eBranchCode")eBranchCode:String,@Body contactListRequest: ContactListRequest):Call<UploadContactResponse>

    @POST("Ledger/Settlement")
    fun settlement(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Header("eUserType") eUserType:String,@Body settelmentRequest: SettelmentRequest):Call<IntroducerResponse>

    @Multipart
    @POST("Account/TicketDetails")
    fun raiseTicket(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Part image: MultipartBody.Part, @Part("Subject") Subject : RequestBody, @Part("Message") Message : RequestBody):Call<UploadContactResponse>


    @Multipart
    @POST("Account/TicketDetails")
    fun raiseTicket2(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String, @Part("Subject") Subject : RequestBody, @Part("Message") Message : RequestBody):Call<UploadContactResponse>

    @Multipart
    @POST("Account/UserUpdate")
    fun updateProfile(@Header("eUserId") userId:String, @Part image2: MultipartBody.Part?, @Part image3: MultipartBody.Part?, @Part image: MultipartBody.Part?, @Part("Name") Name: RequestBody, @Part("Email") Email: RequestBody, @Part("Address") Address: RequestBody, @Part("BankName") BankName: RequestBody, @Part("AccountNo") AccountNo: RequestBody, @Part("Ifsccode") Ifsccode: RequestBody, @Part("BranchId") BranchId: RequestBody, @Part("pan") pan: RequestBody, @Part("aadhar") aadhar: RequestBody):Call<IntroducerResponse>

    @GET("Account/TicketList/{id}")
    fun ticketHistory(@Path("id") id:String):Call<TicketHistoryResponse>


    @GET("Recharge/GetStatus/true")
    fun getStatus():Call<StatusResponse>

    @GET("Account/TicketDetail/{id}")
    fun ticketDetail(@Path("id") id:String):Call<TicketDetailResponse>

    @GET("Account/GetSlider")
    fun getImages():Call<ImageResponse>

    @GET("Home/GetAccountDetails/{id}")
    fun fetchAccountDetail(@Path("id") id:String):Call<AccountDetailResponse>

    @GET("Recharge/RechargeActive/{id}")
    fun fetchRechargeActive(@Path("id") id:String):Call<RechargeActiveResponse>

    @GET("Recharge/RechargeActivationCharge")
    fun fetchRechargeMessage():Call<WidthrawalRequestResponse>

    @POST("Recharge/RechargeActivation/{id}")
    fun RechargeActivation(@Path("id") id:String):Call<RechargeActiveResponse>

    @POST("Payment/BusinessReport")
    fun bussinessReport(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Body bussinessReportRequest: BussinessReportRequest):Call<BussinessReportResponse>

    @GET("Home/GetQRCode/{id}")
   fun generateQr(@Path("id") id:String):Call<GenerateQrResponse>

    @POST("Home/SendQRCode")
    fun scanPay(@Header("Code") code:String,@Header("Amount") amount:String):Call<ScanAndPayResponse>

    @POST("Ledger/LedgerHistory")
    fun accoutLedger(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Header("eUserType") eUserType:String,@Body accountLedgerRequest: AccountLedgerRequest):Call<AccountLedgerResponse>

    @POST("Payment/WithdrawalRequest")
    fun widthrawalApi(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String, @Body widthrawalRequest: WidthrawalRequest):Call<WidthrawalRequestResponse>

    @POST("Ledger/FundTransfer")
    fun fundTransferApi(@Header("eUserType") eUserType:String,@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String, @Body fundTransferRequest: FundTransferRequest):Call<WidthrawalRequestResponse>

    @POST("Payment/WalletRequest")
    fun walletApi(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Body walletRequest: WalletRequest):Call<WidthrawalRequestResponse>


    @GET("Account/GetDistributorPin/{id}")
    fun generatePin(@Path("id") id:String):Call<ResetPinResponse>

    @GET("Payment/WithdrawalRequestHistory")
    fun getWidthrawalHistory(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String):Call<WidthrawalHistoryResponse>

    @GET("Payment/WalletRequestHistory")
    fun getWalletHistory(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String):Call<WalletHistoryResponse>

    @GET("Product/GetProductSuggesion/{id}")
    fun getProductList(@Path("id") id:String):Call<ProductListResponse>

    @POST("Product/SaveLicOrder")
    fun bookLic(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Header("eUserType") eUserType:String,@Body licRequest: LicRequest):Call<LicResponse>

    @GET("Account/GetState")
    fun stateApi():Call<StateResponse>

    @GET("Account/GetDistrict")
    fun districtApi(@Header("eStateId") stateId: String):Call<DistrictResponse>

    @POST("Product/SaveBikeOrder")
   fun  bookNewBike(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Header("eUserType") eUserType:String,@Body bikeRequest: BikeRequest):Call<LicResponse>

    @GET("Product/GetProductList")
   fun showProductList():Call<ProductListResponse>

    @POST("Product/SaveProductOrder")
   fun confirmOrder(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Header("eUserType") eUserType:String,@Body confirmOrderRequest: ConfirmOrderRequest):Call<LicResponse>

   @GET("Account/GetGroupList/{id}")
   fun groupList(@Path("id") id:String):Call<GroupResponse>

    @POST("Payment/P2PTransfer")
   fun p2pRequest(@Header("eUserId") userId:String,@Header("eBranchCode") eBranchCode:String,@Header("eUserType") eUserType:String,@Body p2PRequest: P2PRequest):Call<LicResponse>

    @GET("Account/SendOTPToMobile/{id}/2")
    fun sendOtp2(@Path("id") id:String,@Header("eUserId") userId:String,@Header("amount") amount:String):Call<LicResponse>

    @GET("Ledger/GetTransactionHistory")
    fun transcationList(@Header("eUserId") userId:String):Call<TransactionHistoryResponse>

    @GET("Product/GetProductOrderList")
    fun orderHistoryList(@Header("eUserId") userId:String):Call<OrderHistoryResponse>

}