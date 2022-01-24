package com.droppyn.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.droppyn.database.DroppynDatabase
import com.droppyn.database.entity.asDomainModel
import com.droppyn.database.entity.databaseProfileAndSizeToDomain
import com.droppyn.domain.*
import com.droppyn.network.DroppynApi
import com.droppyn.network.dto.*
import com.droppyn.uitl.TestUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class DroppynRepository(private val database: DroppynDatabase) {
    val brands: LiveData<List<Brand>> =
        Transformations.map(database.brandDao.getBrands()){
            it.asDomainModel()
        }

    val shoes: LiveData<List<Shoe>> =
        Transformations.map(database.shoesAndBrandDao.getShoesAndBrands()){
            it.asDomainModel()
        }

    val sizechart: LiveData<List<Size>> = Transformations.map(database.sizeAndBrandDao.getSizeAndBrands()){
            it.asDomainModel()
        }

//    val users: LiveData<List<User>> = Transformations.map(database.userAndSizeDao.getUsersAndSize()){
//        it.asDomainModel()
//    }

    val offers: LiveData<List<Offer>> = Transformations.map(database.offerAndRelationsDao.getOffersAndRelations()){
        it.asDomainModel()
    }

    val myOffers: LiveData<List<Offer>> = Transformations.map(database.offerAndRelationsDao.getMyOffersAndRelations()){
        it.asDomainModel()
    }

    fun getFilteredOffers(shoe: Shoe): LiveData<List<Offer>> = Transformations.map(database.offerAndRelationsDao.getFilteredOffersAndRelations(shoe.id)){
        it.asDomainModel()
    }

    fun getFilteredBySizeOffers(shoe: Shoe, size: Size): LiveData<List<Offer>> = Transformations.map(database.offerAndRelationsDao.getFilteredBySizeOffersAndRelations(shoe.id, size.id)){
        it.asDomainModel()
    }

    suspend fun getProfile() = withContext(Dispatchers.IO){
        database.userAndSizeDao.getProfileAndSize()?.let { databaseProfileAndSizeToDomain(it) }
    }


//    val token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXJpcyIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpc3MiOiJEcm9wcHluIiwiZXhwIjoxNjM2NjIwNzYyfQ.v5fL9KYG0JsUPvVni--T5WzgBLLK04sBUiEEVxaS-k0"


//    val userId = "618b88478665571bbc1a10e9"

    private suspend fun refreshToken() = withContext(Dispatchers.IO){
            try {
                val refreshToken = database.tokenDao.getToken().refreshToken
                val auth = "Bearer $refreshToken"

                val tokensDTO = DroppynApi.retrofitService.refreshToken(auth)

                database.tokenDao.deleteAll()
                database.tokenDao.insert(NetworkAccessTokenContainer(tokensDTO).asDatabaseModel())
                return@withContext true

            } catch (e: Exception){
                Log.i("retrofit",e.message.toString())
                return@withContext false

            }

    }


    suspend fun refreshBrands() {
        withContext(Dispatchers.IO){
            try {

                val token = database.tokenDao.getToken().accessToken
                val auth = "Bearer $token"

                val brands = DroppynApi.retrofitService.getBrandProperties(auth)
                if(brands.isNotEmpty()) {
                    database.brandDao.deleteAll()
//                    brands.forEach { Log.i("retrofit",it.name) }
                }
                database.brandDao.insertAll(*NetworkBrandContainer(brands).asDatabaseModel())
            } catch (throwable: Throwable){
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()

                        if(code == 401){
                            Log.i("retrofit","refreshing Expired Token")

                            if(refreshToken()){
                                Log.i("retrofit","refreshing Offers")
                                refreshBrands()
                            }
                            else
                            {

                                Log.i("retrofit","LOGOUT")
                                //TODO logout
                            }

                        }
                        else{
                            //TODO logout ??
                            Log.i("retrofit","NETWORK problem Offers")
                        }
                    }
                    else -> {
                        Log.i("retrofit",throwable.message.toString())
                    }
                }

            }
        }
    }

    suspend fun refreshSheos(){
        withContext(Dispatchers.IO){
            try {

                val token = database.tokenDao.getToken().accessToken
                val auth = "Bearer $token"

                val shoes = DroppynApi.retrofitService.getShoesProperties(auth)
                if(shoes.isNotEmpty()) {
                    database.shoeDao.deleteAll()
                    shoes.forEach { database.brandDao.insert(toDatabaseBrand(it.brand)) }

                }
                database.shoeDao.insertAll(*NetworkShoeContainer(shoes).asDatabaseModel())
            } catch (throwable: Throwable){
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()

                        if(code == 401){
                            Log.i("retrofit","refreshing Expired Token")

                            if(refreshToken()){
                                Log.i("retrofit","refreshing Offers")
                                refreshSheos()
                            }
                            else
                            {

                                Log.i("retrofit","LOGOUT")
                                //TODO logout
                            }

                        }
                        else{
                            //TODO logout ??
                            Log.i("retrofit","NETWORK problem Offers")
                        }
                    }
                    else -> {
                        Log.i("retrofit",throwable.message.toString())
                    }
                }

            }
        }
    }

    suspend fun refreshSizeChart(){
        withContext(Dispatchers.IO){
            try {

                val token = database.tokenDao.getToken().accessToken
                val auth = "Bearer $token"

                val sizechart = DroppynApi.retrofitService.getSizeChartProperties(auth)
                if(sizechart.isNotEmpty()) {
                    database.sizeDao.deleteAll()
//                    sizechart.forEach { Log.i("retrofit",it.model) }
//                    sizechart.forEach { database.brandDao.insert(toDatabaseBrand(it.)) }

                }
                database.sizeDao.insertAll(*NetworkSizeContainer(sizechart).asDatabaseModel())
            } catch (throwable: Throwable){
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()

                        if(code == 401){
                            Log.i("retrofit","refreshing Expired Token")

                            if(refreshToken()){
                                Log.i("retrofit","refreshing Offers")
                                refreshSizeChart()
                            }
                            else
                            {

                                Log.i("retrofit","LOGOUT")
                                //TODO logout
                            }

                        }
                        else{
                            //TODO logout ??
                            Log.i("retrofit","NETWORK problem Offers")
                        }
                    }
                    else -> {
                        Log.i("retrofit",throwable.message.toString())
                    }
                }

            }
        }
    }

    suspend fun refreshProfile(){
        withContext(Dispatchers.IO){
            try {
                val userId = database.profileDao.getProfile().id

                val token = database.tokenDao.getToken().accessToken
                val auth = "Bearer $token"

                val user = DroppynApi.retrofitService.getUserProperties(auth,userId)
                    database.profileDao.deleteAll()
                    Log.i("retrofit",user.id)

                database.profileDao.insert(NetworkUserContainer(user).asDatabaseProfileModel())
            } catch (throwable: Throwable){
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()

                        if(code == 401){
                            Log.i("retrofit","refreshing Expired Token")

                            if(refreshToken()){
                                Log.i("retrofit","refreshing Offers")
                                refreshProfile()
                            }
                            else
                            {

                                Log.i("retrofit","LOGOUT")
                                //TODO logout
                            }

                        }
                        else{
                            //TODO logout ??
                            Log.i("retrofit","NETWORK problem Offers")
                        }
                    }
                    else -> {
                        Log.i("retrofit",throwable.message.toString())
                    }
                }

            }
        }
    }

    suspend fun refreshOffers(){
        withContext(Dispatchers.IO){
            try {

                val token = database.tokenDao.getToken().accessToken
                val auth = "Bearer $token"

                val offers = DroppynApi.retrofitService.getOffersProperties(auth)
//                if(offers.isNotEmpty()) {
                    database.offerDao.deleteAll()
//                    offers.forEach { Log.i("retrofit",it.shoe.model+" "+it.price.toString()) }
                    offers.forEach { offerDTO ->
                        database.brandDao.insert(toDatabaseBrand(offerDTO.shoe.brand))
                        database.shoeDao.insert(toDatabaseShoe(offerDTO.shoe))
                        database.sizeDao.insert(toDatabaseSize(offerDTO.size))
                        database.userDao.insert(NetworkUserContainer(offerDTO.user).asDatabaseModel())
                    }

//                }
                database.offerDao.insertAll(*NetworkOfferContainer(offers).asDatabaseModel())


            } catch (throwable: Throwable){
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()

                        if(code == 401){
                            Log.i("retrofit","refreshing Expired Token")

                            if(refreshToken()){
                                Log.i("retrofit","refreshing Offers")
                                refreshOffers()
                            }
                            else
                            {

                                Log.i("retrofit","LOGOUT")
                                //TODO logout
                            }

                        }
                        else{
                            //TODO logout ??
                            Log.i("retrofit","NETWORK problem Offers")
                        }
                    }
                    else -> {
                        Log.i("retrofit",throwable.message.toString())
                    }
                }

            }
        }
    }

    suspend fun refreshMyOffers(){
        withContext(Dispatchers.IO){
            try {

                val token = database.tokenDao.getToken().accessToken
                val auth = "Bearer $token"

                val userId = database.profileDao.getProfile().id
                val myOffers = DroppynApi.retrofitService.getMyOffersProperties(auth,userId)
//                if(myOffers.isNotEmpty()) {
                    database.myOfferDao.deleteAll()
//                    myOffers.forEach { Log.i("retrofit",it.shoe.model+" "+it.price.toString()) }

                    myOffers.forEach { offerDTO ->
                        database.brandDao.insert(toDatabaseBrand(offerDTO.shoe.brand))
                        database.shoeDao.insert(toDatabaseShoe(offerDTO.shoe))
                        database.sizeDao.insert(toDatabaseSize(offerDTO.size))
                        database.profileDao.insert(NetworkUserContainer(offerDTO.user).asDatabaseProfileModel())
                    }

//                }
                database.myOfferDao.insertAll(*NetworkMyOfferContainer(myOffers).asDatabaseModel())
            } catch (throwable: Throwable){
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()

                        if(code == 401){
                            Log.i("retrofit","refreshing Expired Token")

                            if(refreshToken()){
                                Log.i("retrofit","refreshing Offers")
                                refreshMyOffers()
                            }
                            else
                            {

                                Log.i("retrofit","LOGOUT")
                                //TODO logout
                            }

                        }
                        else{
                            //TODO logout ??
                            Log.i("retrofit","NETWORK problem Offers")
                        }
                    }
                    else -> {
                        Log.i("retrofit",throwable.message.toString())
                    }
                }

            }
        }


    }


    suspend fun createMyOffer(myOffer: Offer, navListener: () -> Unit ){
        withContext(Dispatchers.IO){
            try {
//                database.myOfferDao.insert(offertoDatabaseMyOffer(myOffer))
                val token = database.tokenDao.getToken().accessToken
                val auth = "Bearer $token"

                val userId = database.profileDao.getProfile().id


                val offer = DroppynApi.retrofitService.createMyOffer(
                    auth = auth,
                    idShoe = myOffer.shoe.id,
                    idUser = userId,
                    idSize = myOffer.size.id,
                    myOfferDTO = offertoDTOMyOffer(myOffer)
                )
                database.myOfferDao.insert(myOfferDTOtoDatabaseModel(offer))

            }catch (throwable: Throwable){
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()

                        if(code == 401){
                            Log.i("retrofit","refreshing Expired Token")

                            if(refreshToken()){
                                Log.i("retrofit","refreshing Offers")
                                createMyOffer(myOffer, navListener)
                            }
                            else
                            {

                                Log.i("retrofit","LOGOUT")
                                //TODO logout
                            }

                        }
                        else{
                            //TODO logout ??
                            Log.i("retrofit","NETWORK problem Offers")
                        }
                    }
                    else -> {
                        Log.i("retrofit",throwable.message.toString())
                    }
                }

            }

        }
        navListener()
    }

    suspend fun updateMyOffer(myOffer: Offer, navListener: () -> Unit ){
        withContext(Dispatchers.IO){
            try {
                val token = database.tokenDao.getToken().accessToken
                val auth = "Bearer $token"

                database.myOfferDao.insert(offertoDatabaseMyOffer(myOffer))

                val offer = DroppynApi.retrofitService.updateMyOffer(
                        auth = auth,
                        idShoe = myOffer.shoe.id,
                        idUser = myOffer.user.id,
                        idSize = myOffer.size.id,
                        myOfferDTO = offertoDTOMyOffer(myOffer)
                )
                database.myOfferDao.insert(myOfferDTOtoDatabaseModel(offer))

            }catch (throwable: Throwable){
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()

                        if(code == 401){
                            Log.i("retrofit","refreshing Expired Token")

                            if(refreshToken()){
                                Log.i("retrofit","refreshing Offers")
                                updateMyOffer(myOffer, navListener)
                            }
                            else
                            {

                                Log.i("retrofit","LOGOUT")
                                //TODO logout
                            }

                        }
                        else{
                            //TODO logout ??
                            Log.i("retrofit","NETWORK problem Offers")
                        }
                    }
                    else -> {
                        Log.i("retrofit",throwable.message.toString())
                    }
                }

            }

        }

//        navListener()
    }


    suspend fun deleteMyOffer(myOffer: Offer){
        withContext(Dispatchers.IO){
            try {
                val token = database.tokenDao.getToken().accessToken
                val auth = "Bearer $token"

                DroppynApi.retrofitService.deleteMyOffer(
                        auth = auth,
                        myOffer.id,
                        myOffer.user.id)

                database.myOfferDao.deleteById(myOffer.id)

            }catch (throwable: Throwable){
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()

                        if(code == 401){
                            Log.i("retrofit","refreshing Expired Token")

                            if(refreshToken()){
                                Log.i("retrofit","refreshing Offers")
                                deleteMyOffer(myOffer)
                            }
                            else
                            {

                                Log.i("retrofit","LOGOUT")
                                //TODO logout
                            }

                        }
                        else{
                            //TODO logout ??
                            Log.i("retrofit","NETWORK problem Offers")
                        }
                    }
                    else -> {
                        Log.i("retrofit",throwable.message.toString())
                    }
                }

            }

        }
    }


    suspend fun updateProfile(profile: ProfileUpdateDTO){
        withContext(Dispatchers.IO){
            try {
                val token = database.tokenDao.getToken().accessToken
                val auth = "Bearer $token"

                val user = DroppynApi.retrofitService.updateProfile(
                    auth = auth,
                    profile = profile)

                database.profileDao.deleteAll()
                Log.i("retrofit",user.id)

                database.profileDao.insert(NetworkUserContainer(user).asDatabaseProfileModel())

                //refreshProfile()

            }catch (throwable: Throwable){
                when (throwable) {
                    is HttpException -> {
                        val code = throwable.code()

                        if(code == 401){
                            Log.i("retrofit","refreshing Expired Token")

                            if(refreshToken()){
                                Log.i("retrofit","refreshing Offers")
                                updateProfile(profile)
                            }
                            else
                            {

                                Log.i("retrofit","LOGOUT")
                                //TODO logout
                            }

                        }
                        else{
                            //TODO logout ??
                            Log.i("retrofit","NETWORK problem Offers")
                        }
                    }
                    else -> {
                        Log.i("retrofit",throwable.message.toString())
                    }
                }

            }

        }
    }

    suspend fun cleanDatabase(){
        withContext(Dispatchers.IO){
            try {

                database.tokenDao.deleteAll()
                database.myOfferDao.deleteAll()
                database.offerDao.deleteAll()
                database.profileDao.deleteAll()
                database.userDao.deleteAll()

                database.shoeDao.deleteAll()
                database.sizeDao.deleteAll()
                database.brandDao.deleteAll()


            }catch (exception: Exception){
                exception.message?.let { Log.i("retrofit", it) }

            }
        }
    }


    // TODO delete it's just for testing
    suspend fun addTestDataToDatabase(){
        withContext(Dispatchers.IO){
//            database.sizeDao.insert(TestUtil.createDatabaseSize())
//            database.shoeDao.insert(TestUtil.createDatabaseShoe())
            database.profileDao.insert(TestUtil.createDatabseUser())
//            database.offerDao.insert(TestUtil.createDatabaseOffer())
//            database.myOfferDao.insert(TestUtil.createDatabaseMyOffer())

            database.tokenDao.insert(TestUtil.addToken())

        }
    }


}